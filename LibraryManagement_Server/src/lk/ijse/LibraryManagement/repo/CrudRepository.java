package lk.ijse.LibraryManagement.repo;

import java.util.List;

public interface CrudRepository <T, ID> extends SuperRepository{

    public boolean save(T enty)throws Exception;
    public void delete(T enty)throws Exception;
    public boolean update(T enty)throws Exception;
    public T search(ID id)throws Exception;
    public List<T> getAll()throws Exception;
}
