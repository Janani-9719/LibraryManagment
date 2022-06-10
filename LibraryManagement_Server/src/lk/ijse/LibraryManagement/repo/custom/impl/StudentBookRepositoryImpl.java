package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.BookStudent;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.StudentBookRepository;
import lk.ijse.LibraryManagement.repo.custom.StudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentBookRepositoryImpl extends CrudRepositoryImpl<BookStudent,String> implements StudentBookRepository {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public boolean removeBookStudent(int bid) throws Exception {
        Query query = session.createQuery("delete from BookStudent where bid=:bid");
        query.setParameter("bid", bid);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<BookStudent> searchBookStudentByStudent(int sid) throws Exception {
        Query query = session.createQuery("From BookStudent where sid=:sid");
        query.setParameter("sid", sid);
        List<BookStudent> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books;
        }
    }

    @Override
    public List<BookStudent> searchBookStudentByBook(int bid) throws Exception {
        Query query = session.createQuery("From BookStudent where bid=:bid");
        query.setParameter("bid", bid);
        List<BookStudent> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books;
        }
    }

    @Override
    public BookStudent getLastReservationDetails(int stId) throws Exception {
        System.out.println(stId);
        return session.createQuery("From BookStudent where sid='" + stId + "'", BookStudent.class).list().get(0);
    }

    @Override
    public BookStudent getLastRes(int sId) throws Exception {
        return session.createQuery("From Book b,BookStudent bs where b.bid=bs.bid AND bs.sid='" + sId + "'", BookStudent.class).list().get(0);
    }
}
