package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

import java.util.List;

public interface BookService extends SuperService,Subject,Reservation {

    public boolean saveBook(BookDTO bookDTO, PositionDTO positionDTO, AuthorDTO authorDTO)throws Exception;

    public List<BookDTO> getAll()throws Exception;

    public BookDTO searchBook(int bid)throws Exception;

    public BookDTO searchBook(String bookName)throws Exception;

    public boolean updateBook(BookDTO bookDTO,PositionDTO positionDTO)throws Exception;

    public boolean removeBook(int bid)throws Exception;

    public List<BookDTO> getDetails(String name)throws Exception;

}

