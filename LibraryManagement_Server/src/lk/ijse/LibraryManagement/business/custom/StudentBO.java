package lk.ijse.LibraryManagement.business.custom;

import lk.ijse.LibraryManagement.business.SuperBO;
import lk.ijse.LibraryManagement.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    public boolean saveStudent(StudentDTO studentDTO)throws Exception;

    public List<StudentDTO> getAll()throws Exception;

    public StudentDTO searchStudent(int regId)throws Exception;

    public StudentDTO searchStudent(String studentName)throws Exception;

    public boolean updateStudent(StudentDTO studentDTO)throws Exception;

    public boolean removeStudent(int regId)throws Exception;

    public List<StudentDTO> getDetails(String name)throws Exception;

}
