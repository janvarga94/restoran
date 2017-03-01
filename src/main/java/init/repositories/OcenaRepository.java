package init.repositories;

import init.Main;
import init.model.RestoranOcenaDTO;
import init.modelFromDB.OcenaJelaEntity;
import init.modelFromDB.OcenaRestoranaEntity;
import init.modelFromDB.RestoranEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

import static init.Main.session;

/**
 * Created by Svetozar Stojkovic on 3/1/2017.
 */
@Repository
public class OcenaRepository {


    public Collection<RestoranOcenaDTO> getRestoraniForGost(String email){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Collection<RestoranEntity> kolekc = (Collection<RestoranEntity>) session.createNativeQuery("select * from restoran as r where r.id_restorana = (select jelo_u_porudzbini.id_restorana from porudzbina inner join jelo_u_porudzbini on porudzbina.id_porudzbine=jelo_u_porudzbini.id_porudzbine where porudzbina.gost_email='"+email+"')").list();

        Collection<RestoranOcenaDTO> restOcena = new ArrayList<>();

        for(RestoranEntity restoranEntity : kolekc){
            RestoranOcenaDTO restoranOcenaDTO = new RestoranOcenaDTO(restoranEntity, getOcenaForRestoran(restoranEntity.getIdRestorana()));
            restOcena.add(restoranOcenaDTO);
        }

        return restOcena;
    }

    public boolean addOcenaRestorana(OcenaRestoranaEntity ocenaRestoranaEntity){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(ocenaRestoranaEntity.getGostEmail());

        Query query = session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail=:email and ore.idRestorana=:id");
        query.setParameter("email",ocenaRestoranaEntity.getGostEmail());
        query.setParameter("id",ocenaRestoranaEntity.getIdRestorana());

        //OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail='"+ocenaRestoranaEntity.getGostEmail()+"' and ore.idRestorana="+ocenaRestoranaEntity.getIdRestorana());
        OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) query.uniqueResult();

        if (ocena != null) {
            session.delete(ocena);
        }
        session.save(ocenaRestoranaEntity);
        try {
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public double getOcenaForRestoran(int idRestorana){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Collection<OcenaRestoranaEntity> lista = (Collection<OcenaRestoranaEntity>) session.createQuery("from OcenaRestoranaEntity as ore where ore.idRestorana="+idRestorana).list();

        double ocena = 0;
        for (OcenaRestoranaEntity ore : lista){
            ocena+=ore.getOcena();
        }
        ocena /= lista.size();

        return ocena;
    }



    public boolean addOcenaJela(OcenaJelaEntity ocenaJelaEntity){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(ocenaJelaEntity.getGostEmail());

        Query query = session.createQuery("from OcenaJelaEntity as oje where oje.gostEmail=:email and oje.idRestorana=:id and oje.nazivJela=:nazivJela");
        query.setParameter("email",ocenaJelaEntity.getGostEmail());
        query.setParameter("id",ocenaJelaEntity.getIdRestorana());
        query.setParameter("nazivJela",ocenaJelaEntity.getNazivJela());

        //OcenaRestoranaEntity ocena = (OcenaRestoranaEntity) session.createQuery("from OcenaRestoranaEntity as ore where ore.gostEmail='"+ocenaRestoranaEntity.getGostEmail()+"' and ore.idRestorana="+ocenaRestoranaEntity.getIdRestorana());
        OcenaJelaEntity ocena = (OcenaJelaEntity) query.uniqueResult();

        if (ocena != null) {
            session.delete(ocena);
        }
        session.save(ocenaJelaEntity);
        try {
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}
