package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Damage;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.DamageRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DamageRepositoryImpl extends CrudRepositoryImpl<Damage,String> implements DamageRepository {

    private Session session;
    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public boolean removeDamageBooks(int dmgId) throws Exception {
        Query query = session.createQuery("delete from Damage where damageId=:damageId");
        query.setParameter("damageId", dmgId);
        return query.executeUpdate() > 0;
    }

    @Override
    public Damage searchDamageBooks(int dmgId) throws Exception {
        Query query = session.createQuery("From Damage where damageId=:damageId");
        query.setParameter("damageId", dmgId);
        List<Damage> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

    @Override
    public Damage searchDamageBooks(String dmgBookName) throws Exception {
        return null;
    }

    @Override
    public List<Damage> getDetails(String name) throws Exception {
        return session.createQuery("from Damage where", Damage.class).list();
    }
}
