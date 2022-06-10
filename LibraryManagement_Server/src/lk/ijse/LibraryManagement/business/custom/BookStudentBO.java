package lk.ijse.LibraryManagement.business.custom;

import lk.ijse.LibraryManagement.business.SuperBO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.BookStudentDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;

import java.util.List;

public interface BookStudentBO extends SuperBO {

    public boolean saveBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO)throws Exception;

    public boolean updateBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO)throws Exception;

    public boolean removeBookStudent(int bid)throws Exception;

    public List<BookStudentDTO> getAll()throws Exception;

    public List<BookStudentDTO> searchBookStudentByStudent(int sid)throws Exception;

    public List<BookStudentDTO> searchBookStudentByBook(int bid)throws Exception;

    public BookStudentDTO getLastReservationDetails(int stId)throws Exception;

    public BookStudentDTO getLastRes(int sId) throws Exception;
}
