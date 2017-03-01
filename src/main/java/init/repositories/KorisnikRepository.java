package init.repositories;
import init.dtos.LoginKorisnikResponseDto;
import init.modelFromDB.*;
import init.repositories.models.KorisnikRepo;
import init.repositories.models.KorisnikRepo.Uloga;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static init.Main.sessionFactory;

/**
 * Created by janva on 2/2/2017.
 */
@Repository
public class KorisnikRepository implements  CrudRepository<KorisnikRepo, String> {

    @Override
    public <S extends KorisnikRepo> S save(S entity) {
        return null;
    }

    @Override
    public KorisnikRepo findOne(String primaryKey) {
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        KorisnikEntity k = session.get(KorisnikEntity.class, primaryKey);
        KorisnikRepo kRepo = new KorisnikRepo();

        if(k != null){
            kRepo.email = k.getEmail();
            kRepo.ime = k .getIme();
            kRepo.prezime = k.getPrezime();
            kRepo.password = k.getLozinka();
            kRepo.aktiviran = true;

            GostEntity gostEntity = session.get(GostEntity.class, primaryKey);

            if(gostEntity != null){
                kRepo.setUloga(Uloga.GOST);
                kRepo.aktiviran = true;//gostEntity.getAktiviran() == Byte.MAX_VALUE;
            }else if(session.get(KonobarEntity.class, primaryKey) != null){
                kRepo.setUloga(Uloga.KONOBAR);
            }else   if(session.get(KonobarEntity.class, primaryKey) != null){
                kRepo.setUloga(Uloga.KONOBAR);
            }else   if(session.get(SankerEntity.class, primaryKey) != null){
                kRepo.setUloga(Uloga.SANKER);
            }else    if(session.get(KuvarEntity.class, primaryKey) != null){
                kRepo.setUloga(Uloga.KUVAR);
            }else   if(session.get(MenazerRestoranaEntity.class, primaryKey) != null){
                kRepo.setUloga(Uloga.MENAZER_RESTORANA);
            }else    if(session.get(MenadzerSistemaEntity.class, primaryKey) != null){
                kRepo.setUloga(Uloga.MENAZER_SISTEMA);
            }else{
                return null; //jer nismo nasli ulogu
            }

            session.close();
            return kRepo;
        }else{

            session.close();
            return null;
        }
    }

    @Override
    public Iterable<KorisnikRepo> findAll() {
        org.hibernate.Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<KorisnikEntity> list = session.createNativeQuery("SELECT * FROM korisnik",KorisnikEntity.class).getResultList();
        List<KorisnikRepo> listToReturn = new ArrayList<KorisnikRepo>();
        list.forEach(kor -> {
            KorisnikRepo k = new KorisnikRepo();
            k.password = kor.getLozinka();
            k.email = kor.getEmail();
            k.ime = kor.getIme();
            k.uloga = "Gost";
            k.Euloga = Uloga.GOST;

            listToReturn.add(k);
        });

        session.close();
        return listToReturn;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(KorisnikRepo entity) {

    }

    @Override
    public boolean update(KorisnikRepo entity) {
        return false;
    }

    @Override
    public boolean exists(String primaryKey) {
        return false;
    }
}
