package lk.ijse.LibraryManagement.repo;

import org.hibernate.Session;

public interface SuperRepository {
    public void setSession(Session session)throws Exception;
}
