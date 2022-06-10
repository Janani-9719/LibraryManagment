package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

import java.util.List;

public interface AuthorService extends SuperService,Subject,Reservation {

    public boolean saveAuthor(AuthorDTO authorDTO)throws Exception;

    public List<AuthorDTO> getAll()throws Exception;

    public boolean update(AuthorDTO authorDTO)throws Exception;

    public AuthorDTO searchAuthor(int aid)throws Exception;

    public AuthorDTO searchAuthor(String name)throws Exception;

    public boolean removeAuthor(int aid)throws Exception;
}
