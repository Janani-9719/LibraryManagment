package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.entity.BookAuthor;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.BookAuthorRepository;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorRepositoryImpl extends CrudRepositoryImpl<BookAuthor,String>implements BookAuthorRepository {
    private Session session;


    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public boolean updateAuthorForBook(Author author, int bid) throws Exception {

        Connection connection = ((SessionImpl) session).connection();

        Statement statement = connection.createStatement();

        int i = statement.executeUpdate("update bookauthor set aid='" + author.getAuthorId() + "' where bid='" + bid + "' ");

        return i>0;
    }

    @Override
    public boolean updateBookForAuthor(Book book, int aid) throws Exception {
        Query query = session.createQuery("update BookAuthor set bid=:bid where aid=:aid");
        query.setParameter("aid", aid);
        query.setParameter("bid", book);

        return query.executeUpdate() > 0;
    }

    @Override
    public Author getAuthorForBook(int bid) throws Exception {

        Connection connection = ((SessionImpl) session).connection();

        PreparedStatement preparedStatement = connection.prepareStatement("select authorId, authorName from book b,author r ,bookauthor c where  b.bid='" + bid + "' and c.bid='" + bid + "' and r.authorId=c.aid");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){

            System.out.println("Hello"+resultSet.getString(2));
            return  new Author(resultSet.getInt(1),resultSet.getString(2));
        }

        return null;

    }

    @Override
    public Book getBookForAuthor(int aid) throws Exception {

        Query query = session.createQuery("select ba.bid From BookAuthor ba where ba.aid=:aid");
        query.setParameter("aid", aid);
        List<Book> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }
}
