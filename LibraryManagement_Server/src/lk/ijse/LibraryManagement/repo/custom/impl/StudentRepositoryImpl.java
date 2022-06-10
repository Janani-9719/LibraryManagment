package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Student;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.StudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositoryImpl extends CrudRepositoryImpl<Student,String> implements StudentRepository {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public Student searchStudent(int regId) throws Exception {
        Query query = session.createQuery("From Student where regId=:regId");
        query.setParameter("regId", regId);
        List<Student> students = query.list();
        if (students.isEmpty()) {
            return null;
        } else {
            return students.get(0);
        }
    }

    @Override
    public Student searchStudent(String studentName) throws Exception {
        Query query = session.createQuery(" From Student where name = : name ");
        System.out.println("abc");
        query.setParameter("name", studentName);
        List<Student> students = query.list();
        if (students.isEmpty()) {
            return null;
        } else {
            return students.get(0);
        }
    }

    @Override
    public boolean removeStudent(int regId) throws Exception {
        Query query = session.createQuery(" delete from Student where regId=:regId ");
        query.setParameter("regId", regId);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Student> getDetails(String name) throws Exception {
        return session.createQuery(" from Student where name = ' " + name + " ' ", Student.class).list();
    }
}
