package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.DamageBO;
import lk.ijse.LibraryManagement.dto.DamageDTO;
import lk.ijse.LibraryManagement.entity.Damage;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.DamageRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DamageBOImpl implements DamageBO {

    private DamageRepository damageRepository;

    public DamageBOImpl() {

        damageRepository=(DamageRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.DAMAGE);
    }

    @Override
    public boolean saveDamageBooks(DamageDTO damageDTO) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            //session.beginTransaction();
            damageRepository.setSession(session);
            //boolean isSave=false;
            session.getTransaction().begin();
            Damage damage=new Damage(damageDTO.getBid());
            damageRepository.save(damage);
                session.getTransaction().commit();

            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDamageBooks(DamageDTO damageDTO) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            damageRepository.setSession(session);
            boolean isUpdate=false;
            Damage damage=new Damage(damageDTO.getDamageId(),damageDTO.getBid());
            if(damageRepository.update(damage)){
                session.getTransaction().commit();
                session.close();
                isUpdate=true;
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isUpdate;
        }
    }

    @Override
    public boolean removeDamageBooks(int dmgId) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()) {
            //session.beginTransaction();
            damageRepository.setSession(session);
            session.getTransaction().begin();
            //boolean isRemove=false;
            damageRepository.removeDamageBooks(dmgId);
            session.getTransaction().commit();
            session.close();;
            return true;
        }
    }

    @Override
    public DamageDTO searchDamageBooks(int dmgId) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            damageRepository.setSession(session);
            Damage damage=damageRepository.searchDamageBooks(dmgId);
            if(damage!=null){
                DamageDTO damageDTO=new DamageDTO(damage.getDamageId(),damage.getBid());
                session.close();
                return damageDTO;
            }
            session.close();
            return null;
        }
    }

    @Override
    public DamageDTO searchDamageBooks(String dmgBookName) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            damageRepository.setSession(session);
            Damage damage=damageRepository.searchDamageBooks(dmgBookName);
            if(damage!=null){
                DamageDTO damageDTO=new DamageDTO(damage.getDamageId(),damage.getBid());
                session.close();
                return damageDTO;
            }
            session.close();
            return null;
        }
    }

    @Override
    public List<DamageDTO> getAll() throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            damageRepository.setSession(session);
            List<Damage> list=damageRepository.getAll();
            List<DamageDTO> damages=new ArrayList<>();
            if(list!=null){
                for (Damage damage : list) {
                    damages.add(new DamageDTO(damage.getDamageId(),damage.getBid()));
                }
            }
            session.close();
            return damages;
        }
    }

    @Override
    public List<DamageDTO> getDetails(String name) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            damageRepository.setSession(session);
            List<Damage> list=damageRepository.getDetails(name);
            List<DamageDTO> damages=new ArrayList<>();
            if(list!=null){
                for (Damage damage : list) {
                    damages.add(new DamageDTO(damage.getDamageId(),damage.getBid()));
                }
            }
            session.close();
            return damages;
        }
    }

}

