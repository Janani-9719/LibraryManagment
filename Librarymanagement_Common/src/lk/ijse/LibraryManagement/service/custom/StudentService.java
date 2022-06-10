package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

import java.util.List;

public interface StudentService extends SuperService,Subject,Reservation {

    public boolean saveStudent(StudentDTO studentDTO)throws Exception;

    public List<StudentDTO> getAll()throws Exception;

    public StudentDTO searchStudent(int regId)throws Exception;

    public StudentDTO searchStudent(String studentName)throws Exception;

    public boolean updateStudent(StudentDTO studentDTO)throws Exception;

    public boolean removeStudent(int regId)throws Exception;

    public List<StudentDTO> getDetails(String name)throws Exception;
}
