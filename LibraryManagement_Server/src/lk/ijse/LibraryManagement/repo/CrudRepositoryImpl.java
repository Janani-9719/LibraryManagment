package lk.ijse.LibraryManagement.repo;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudRepositoryImpl<T, ID extends Serializable> implements CrudRepository<T,ID> {

    private Session session;
    private Class<T> entity;

    public CrudRepositoryImpl(){
        entity=(Class<T>)((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];

    }
    @Override
    public boolean save(T enty) throws Exception {
        session.save(enty);

        return false;
    }

    @Override
    public void delete(T enty) throws Exception {
        session.delete(enty);

    }

    @Override
    public boolean update(T enty) throws Exception {
        session.update(enty);

        return false;
    }

    @Override
    public T search(ID id) throws Exception {
        return session.get(entity,id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery(" from " + entity.getName()).list();
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;

    }
}
