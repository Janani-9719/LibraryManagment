package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.BookStudentDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

import java.util.List;

public interface BookStudentService extends SuperService,Subject,Reservation {

    public boolean saveBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO)throws Exception;

    public boolean updateBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO)throws Exception;

    public boolean removeBookStudent(int bid)throws Exception;

    public List<BookStudentDTO> getAll()throws Exception;

    public List<BookStudentDTO> searchBookStudentByStudent(int sid)throws Exception;

    public List<BookStudentDTO> searchBookStudentByBook(int bid)throws Exception;

    public BookStudentDTO getLastReservationDetails(int stid)throws Exception;

}
