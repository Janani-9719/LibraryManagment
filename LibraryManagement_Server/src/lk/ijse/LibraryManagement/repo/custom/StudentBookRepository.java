package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.entity.BookStudent;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface StudentBookRepository extends CrudRepository<BookStudent, String> {

    public boolean removeBookStudent(int bid)throws Exception;

    public List<BookStudent> searchBookStudentByStudent(int sid)throws Exception;

    public List<BookStudent> searchBookStudentByBook(int bid)throws Exception;

    public BookStudent getLastReservationDetails(int stId)throws Exception;

    public BookStudent getLastRes(int sId)throws Exception;

}
