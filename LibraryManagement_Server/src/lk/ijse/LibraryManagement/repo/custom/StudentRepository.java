package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.entity.Student;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {

    public Student searchStudent(int regId)throws Exception;

    public Student searchStudent(String studentName)throws Exception;


    public boolean removeStudent(int regId)throws Exception;

    public List<Student> getDetails(String name)throws Exception;

}
