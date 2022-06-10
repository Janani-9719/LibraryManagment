package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.StudentBO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.observer.Observers;
import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.StudentService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class StudentServiceImpl extends UnicastRemoteObject implements StudentService {

    private StudentBO studentBO;
    private static ArrayList<Observers>allStudentObservers = new ArrayList<>();
    private static Reservations<StudentService>studentResrvations= new Reservations();

    public  StudentServiceImpl()throws Exception{
        studentBO=(StudentBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.STUDENT);
    }
    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {

        notyfyAllObservers();
        return studentBO.saveStudent(studentDTO);
    }

    @Override
    public List<StudentDTO> getAll() throws Exception {
        return studentBO.getAll();
    }

    @Override
    public StudentDTO searchStudent(int regId) throws Exception {
        return studentBO.searchStudent(regId);
    }

    @Override
    public StudentDTO searchStudent(String studentName) throws Exception {
        return studentBO.searchStudent(studentName);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {

        if(studentResrvations.reserve(studentDTO.getSid(),this,true)){
            boolean result= studentBO.updateStudent(studentDTO);
            notyfyAllObservers();
            if(studentResrvations.checkStatus(studentDTO.getSid(),this)){
                studentResrvations.release(studentDTO,this);

            }
            return result;

        }else {
            return false;
        }


    }

    @Override
    public boolean removeStudent(int regId) throws Exception {
        return studentBO.removeStudent(regId);
    }

    @Override
    public List<StudentDTO> getDetails(String name) throws Exception {
        return  studentBO.getDetails(name);
    }

    @Override
    public boolean register(Observer observer) throws Exception {
        allStudentObservers.add((Observers) observer);
        return true;
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        allStudentObservers.remove(observer);
        return true;
    }

    @Override
    public void notyfyAllObservers() throws Exception {
        for(Observers allStudentObservers : allStudentObservers){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                allStudentObservers.update();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
        }

    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return studentResrvations.reserve(id,this,true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return studentResrvations.release(id,this);
    }
}
