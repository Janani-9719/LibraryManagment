package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

    public Book searchBook(int bid)throws Exception;

    public Book searchBook(String bookName)throws Exception;

    public boolean removeBook(int bid)throws Exception;

    public List<Book> getDetails(String name)throws Exception;


}
