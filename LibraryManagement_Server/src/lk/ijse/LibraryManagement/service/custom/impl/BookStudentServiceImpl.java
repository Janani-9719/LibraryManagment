package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.BookStudentBO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.BookStudentDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.observer.Observers;
import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.BookStudentService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class BookStudentServiceImpl extends UnicastRemoteObject implements BookStudentService {

    private BookStudentBO bookStudentBO;
    private static ArrayList<Observers>allBookStudentObservers=new ArrayList<>();
    private static Reservations<BookStudentService>bookStudentReservations=new Reservations();

    public BookStudentServiceImpl()throws Exception{
        bookStudentBO=(BookStudentBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.STUDENTBOOK);

    }

    @Override
    public boolean saveBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO) throws Exception {
       notyfyAllObservers();
        return bookStudentBO.saveBookStudent(bookStudentDTO,bookDTO,studentDTO,positionDTO);

    }

    @Override
    public boolean updateBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO) throws Exception {
        return bookStudentBO.updateBookStudent(bookStudentDTO,bookDTO,studentDTO,positionDTO);
    }

    @Override
    public boolean removeBookStudent(int bid) throws Exception {
        return bookStudentBO.removeBookStudent(bid);
    }

    @Override
    public List<BookStudentDTO> getAll() throws Exception {
        return bookStudentBO.getAll();
    }

    @Override
    public List<BookStudentDTO> searchBookStudentByStudent(int sid) throws Exception {
        return bookStudentBO.searchBookStudentByStudent(sid);
    }

    @Override
    public List<BookStudentDTO> searchBookStudentByBook(int bid) throws Exception {
        return bookStudentBO.searchBookStudentByBook(bid);
    }

    @Override
    public BookStudentDTO getLastReservationDetails(int stid) throws Exception {
        return bookStudentBO.getLastReservationDetails(stid);
    }

    @Override
    public boolean register(Observer observer) throws Exception {
        return allBookStudentObservers.add((Observers) observer);
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        return allBookStudentObservers.remove(observer);
    }

    @Override
    public void notyfyAllObservers() throws Exception {
        for(Observers allBookstudentObserver : allBookStudentObservers){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                allBookstudentObserver.update();
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
        return bookStudentReservations.reserve(id,this,true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return bookStudentReservations.release(id,this);
    }
}
