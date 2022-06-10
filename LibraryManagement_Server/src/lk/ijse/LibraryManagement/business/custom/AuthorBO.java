package lk.ijse.LibraryManagement.business.custom;

import lk.ijse.LibraryManagement.business.SuperBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;

import java.util.List;

public interface AuthorBO extends SuperBO {

    public boolean saveAuthor(AuthorDTO authorDTO)throws Exception;

    public List<AuthorDTO> getAll()throws Exception;

    public boolean update(AuthorDTO authorDTO)throws Exception;

    public AuthorDTO searchAuthor(int aid)throws Exception;

    public AuthorDTO searchAuthor(String name)throws Exception;

    public boolean removeAuthor(int aid)throws Exception;
}
