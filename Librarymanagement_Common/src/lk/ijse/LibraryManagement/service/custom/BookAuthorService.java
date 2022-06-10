package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

public interface BookAuthorService  extends SuperService,Subject,Reservation {

    public boolean updateAuthorForBook(AuthorDTO authorDTO, int bid)throws Exception;

    public boolean updateBookForAuthor(BookDTO bookDTO, PositionDTO positionDTO, int aid)throws Exception;

    public AuthorDTO getAuthorForBook(int bid)throws Exception;

    public BookDTO getBookForAuthor(int aid)throws Exception;

    public boolean saveBookWithAuthor(BookDTO baDto,AuthorDTO authorDto) throws Exception ;

}
