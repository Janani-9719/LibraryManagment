package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.BookRepository;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class BookRepositoryImpl  extends CrudRepositoryImpl<Book,String>implements BookRepository {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public Book searchBook(int bid) throws Exception {
        Query query = session.createQuery("From Book where bid ='" + bid + "'");
        List<Book> books = query.list();

        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

    @Override
    public Book searchBook(String bookName) throws Exception {
        Query query = session.createQuery(" From Book where bookName=:name ");
        query.setParameter("name", bookName);
        List<Book> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

    @Override
    public boolean removeBook(int bid) throws Exception {

        Connection connection = ((SessionImpl) session).connection();

        Statement statement = connection.createStatement();

        int i = statement.executeUpdate("delete from bookauthor where bid='" + bid + "'");

        if(i>0){
            i = statement.executeUpdate("delete  from book where bid='" + bid + "'");


        }

        return i>0;

    }

    @Override
    public List<Book> getDetails(String name) throws Exception {
        return session.createQuery("from Book where bookName='" + name + "'", Book.class).list();
    }
}
