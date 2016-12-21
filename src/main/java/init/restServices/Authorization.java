package init.restServices;

import init.modelFromDB.RestoranEntity;
import init.services.ServiceRestorani;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;

import java.text.SimpleDateFormat;
import java.util.Collection;


/**
 * Created by Svetozar Stojkovic on 11/28/2016.
 * This class is for accessing
 */
@RestController
@RequestMapping("/auth")
public class Authorization {

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public boolean login(String username, String password){
        return true;
    }

    @RequestMapping(path="/register", method = RequestMethod.GET)
    public boolean register(String email, String password){
       /* SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new Korisnik);*/
        sendMail(email);
        return true;
    }

    private void sendMail(String email){
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            msg.setText("Kliknite na sledeci link: baldkfjsdalfkjsadflkjsadfkjsdalfkjd " + sdf.toString());

            // Send the message.
            Transport.send(msg);
        } catch (MessagingException e) {
            // Error.
        }
    }

}
