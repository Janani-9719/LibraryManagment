package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.StudentBO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.entity.Student;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.StudentRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentRepository studentRepository;

    public StudentBOImpl() {

        studentRepository=(StudentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.STUDENT);
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        Student student=new Student(studentDTO.getRegId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getBday(),studentDTO.getGrade(),studentDTO.getPhoto());
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            studentRepository.setSession(session);

            session.getTransaction().begin();

            studentRepository.save(student);

            session.getTransaction().commit();

            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<StudentDTO> getAll() throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentRepository.setSession(session);
            List<Student> list=studentRepository.getAll();
            List<StudentDTO> students=new ArrayList<>();
            if(list!=null){

                for (Student student : list) {
                    students.add(new StudentDTO(student.getSid(),student.getRegId(),student.getName(),student.getAddress(),student.getBday(),student.getGrade(),student.getPhoto()));
                }
            }

            session.close();
            return students;
        }
    }

    @Override
    public StudentDTO searchStudent(int regId) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentRepository.setSession(session);
            Student student=studentRepository.searchStudent(regId);
            if(student!=null){
                session.close();
                return new StudentDTO(student.getSid(),student.getRegId(),student.getName(),student.getAddress(),student.getBday(),student.getGrade(),student.getPhoto());
            }else{
                session.close();
                return null;
            }
        }

    }

    @Override
    public StudentDTO searchStudent(String studentName) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentRepository.setSession(session);
            Student student=studentRepository.searchStudent(studentName);
            if(student!=null){
                session.close();
                return new StudentDTO(student.getSid(),student.getRegId(),student.getName(),student.getAddress(),student.getBday(),student.getGrade(),student.getPhoto());
            }else{
                session.close();
                return null;
            }
        }

    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {

        Student student=new Student(studentDTO.getSid(),studentDTO.getRegId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getBday(),studentDTO.getGrade(),studentDTO.getPhoto());
        //boolean isUpdate=false;
        try(Session session=HibernateUtil.getSessionFactory().openSession()) {
           // session.beginTransaction();
            studentRepository.setSession(session);
            session.getTransaction().begin();
            studentRepository.update(student);


           session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean removeStudent(int regId) throws Exception {

        //boolean isRemove=false;
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            studentRepository.setSession(session);
            //session.beginTransaction();
            session.getTransaction().begin();

            studentRepository.removeStudent(regId);
                //isRemove=true;

                session.getTransaction().commit();
                session.close();
                return true;


            }
             //return isRemove;



    }

    @Override
    public List<StudentDTO> getDetails(String name) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentRepository.setSession(session);
            List<Student> list=studentRepository.getDetails(name);
            List<StudentDTO> students=new ArrayList<>();
            if(list!=null){

                for (Student student : list) {
                    students.add(new StudentDTO(student.getSid(),student.getRegId(),student.getName(),student.getAddress(),student.getBday(),student.getGrade(),student.getPhoto()));
                }
            }

            session.close();
            return students;
        }
    }
}
