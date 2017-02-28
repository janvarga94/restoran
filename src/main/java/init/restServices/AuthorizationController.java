package init.restServices;

import init.Main;
import init.dtos.LoginDto;
import init.dtos.LoginKorisnikResponseDto;
import init.dtos.RegisterDto;
import init.dtos.ResponseDto;
import init.modelFromDB.*;
import init.repositories.KorisnikRepository;
import init.repositories.models.KorisnikRepo;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;


/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is for accessing
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private KorisnikRepository repository;

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public LoginKorisnikResponseDto login(@RequestBody LoginDto acc, HttpSession httpSession){
        if(acc == null || acc.email == null || acc.password == null)
            return null;
        KorisnikRepo korisnik = repository.findOne(acc.email);
        if( korisnik == null || korisnik.email == null || korisnik.password == null)
            return  null;

        if( korisnik.password.equals(acc.password)){
            LoginKorisnikResponseDto loginKorisnikResponseDto = new LoginKorisnikResponseDto();
            loginKorisnikResponseDto.email = korisnik.email;
            loginKorisnikResponseDto.ime = korisnik.ime;
            loginKorisnikResponseDto.prezime = korisnik.prezime;
            loginKorisnikResponseDto.uloga = korisnik.uloga;
            loginKorisnikResponseDto.Euloga = korisnik.Euloga;

            httpSession.setAttribute("korisnik", loginKorisnikResponseDto);

            return loginKorisnikResponseDto;
        }

        return null;
    }

    @RequestMapping(path="/logout", method = RequestMethod.GET)
    public boolean logout(HttpSession httpSession){
        httpSession.setAttribute("korisnik",null);
        return true;
    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public ResponseDto register(@RequestBody RegisterDto acc){

        if(acc.email == null || acc.email == "" || acc.password==null || acc.password == "" || acc.ime == null || acc.ime == "" || acc.prezime == null || acc.prezime == ""){
            ResponseDto dto = new ResponseDto();
            dto.setSuccess(false);
            dto.setDescription("polja nesmeju biti prazna");
            return dto;
        }

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        KorisnikEntity k = new KorisnikEntity();
        k.setEmail(acc.email);
        k.setLozinka(acc.password);
        k.setIme(acc.ime);
        k.setPrezime(acc.prezime);

        GostEntity g = new GostEntity();
        g.setAktiviran(Byte.MIN_VALUE);
        g.setGostEmail(acc.email);

        TokenEntity t = new TokenEntity();
        t.setEmail(acc.email);
        t.setTokenString(UUID.randomUUID().toString());

        session.save(k);
        session.save(g);
        session.save(t);

        try {
            session.getTransaction().commit();

        }catch(Exception e){
            ResponseDto dto = new ResponseDto();
            dto.setSuccess(false);
            dto.setDescription("Email already registred");
            return dto;
        }

        try {
            sendMail(acc.email, t.getTokenString());
        }catch(Exception e){
            ResponseDto dto = new ResponseDto();
            dto.setSuccess(false);
            dto.setDescription("We cant send email sorry");
            return dto;
        }


        ResponseDto dto = new ResponseDto();
        dto.setSuccess(true);
        dto.setObject(t.getTokenString());
        return dto;
    }

    @RequestMapping(path="/activateAccount", method = RequestMethod.GET)
    public ResponseDto activateAccount(String tokenString){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        org.hibernate.Query query = session.createQuery("select g from TokenEntity as g where g.tokenString=:tokenString")
                .setParameter("tokenString",tokenString);

        TokenEntity t = (TokenEntity) query.uniqueResult();

        if(t != null){

            GostEntity gost = (GostEntity) session.get(GostEntity.class, t.getEmail());

            gost.setAktiviran(Byte.MAX_VALUE);
            session.save(gost);
            session.getTransaction().commit();
            ResponseDto dto = new ResponseDto();
            dto.setSuccess(true);
            return dto;
        }else{
            ResponseDto dto = new ResponseDto();
            dto.setSuccess(false);
            dto.setDescription("gost nije registrovan");
            return dto;
        }

    }

    private void sendMail(String email, String token){
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("isastefansvetozarjan@gmail.com", "isastefansvetozarjan123");
            }
        });

// Construct the message
        String to = email;
        String from = "isastefansvetozarjan@gmail.com";
        String subject = "Acktiviraj acc";
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
    //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            msg.setText("Aktivacioni link: " + Main.frontendUrl + "/activateAccount/" + URLEncoder.encode(token, "UTF-8") );

            // Send the message.
            Transport.send(msg);
        } catch (MessagingException e) {
            // Error.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
