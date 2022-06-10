package lk.ijse.LibraryManagement.business.custom;

import lk.ijse.LibraryManagement.business.SuperBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;

public interface BookAuthorBO extends SuperBO {

    public boolean updateAuthorForBook(AuthorDTO authorDTO,int bid)throws Exception;

    public boolean updateBookForAuthor(BookDTO bookDTO, PositionDTO positionDTO, int aid)throws Exception;

    public AuthorDTO getAuthorForBook(int bid)throws Exception;

    public BookDTO getBookForAuthor(int aid)throws Exception;

    public boolean saveBookWithAuthor(BookDTO bookDTO,AuthorDTO authorDTO) throws Exception;

}
