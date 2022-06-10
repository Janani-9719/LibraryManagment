package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,String> {


    public Author searchAuthor(int aid)throws Exception;

    public Author searchAuthor(String name)throws Exception;

    public boolean removeAuthor(int aid)throws Exception;




}
