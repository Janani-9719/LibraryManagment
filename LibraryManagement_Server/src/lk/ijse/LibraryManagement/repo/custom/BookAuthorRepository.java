package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.entity.BookAuthor;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, String> {

    public boolean updateAuthorForBook(Author author,int bid)throws Exception;

    public boolean updateBookForAuthor(Book book, int aid)throws Exception;

    public Author getAuthorForBook(int bid)throws Exception;

    public Book getBookForAuthor(int aid)throws Exception;

    @Override
    public default List<BookAuthor> getAll() throws Exception {return null;}

    @Override
    public default boolean update(BookAuthor t) throws Exception {return false;}

}
