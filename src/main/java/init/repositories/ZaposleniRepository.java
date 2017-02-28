package init.repositories;

import init.dtos.ResponseWithMessageSuccess;
import init.dtos.ZaposleniDTO;
import init.modelFromDB.*;
import init.repositories.models.KorisnikRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

/**
 * Created by Svetozar Stojkovic on 2/24/2017.
 */
@Repository
public class ZaposleniRepository {

        public ZaposleniRepository(){}


        public ResponseWithMessageSuccess addZaposlenog(ZaposleniDTO zaposlen,int z){
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            org.hibernate.Session session = sessionFactory.openSession();
            session.beginTransaction();

            RadnikEntity radnik = new RadnikEntity();
            radnik.setRadnikEmail(zaposlen.email);
            radnik.setIdRestorana(z);
            radnik.setKonfekcijskiBroj(zaposlen.konfenkcijskiBroj);
            radnik.setVelicinaObuce(zaposlen.velicinaObuce);

            KorisnikEntity korisnik = new KorisnikEntity();
            korisnik.setEmail(zaposlen.email);
            korisnik.setIme(zaposlen.ime);
            korisnik.setPrezime(zaposlen.prezime);
            korisnik.setLozinka(zaposlen.pass);

            session.save(korisnik);
            session.save(radnik);

            if(zaposlen.selectedJob.equals("Kuvar")){
                KuvarEntity kuvar = new KuvarEntity();
                kuvar.setKuvarEmail(zaposlen.email);
                kuvar.setIdTipaJela(0);
                session.save(kuvar);
            }
            else if(zaposlen.selectedJob.equals("Sanker")){
                SankerEntity sanker = new SankerEntity();
                sanker.setSankerEmail(zaposlen.email);
                session.save(sanker);
            }
            else
            {
                KonobarEntity konobar = new KonobarEntity();
                konobar.setKonobarEmail(zaposlen.email);
                session.save(konobar);
            }

            ResponseWithMessageSuccess message = new ResponseWithMessageSuccess();
            message.Message = "Uspeh";
            message.Success = true;



            try{
                 session.flush();
            }
            catch(Exception e){

                message.Message = "Nije uspelo";
                message.Success = false;

            }finally {
                session.close();
            }

           return message;

        }

}
