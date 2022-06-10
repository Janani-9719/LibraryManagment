package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.BookBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.observer.Observers;
import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.BookService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class BookServiceImpl extends UnicastRemoteObject implements BookService {

    private BookBO bookBO;
    private static ArrayList<Observers>allBookObservers= new ArrayList<>();
    private static Reservations<BookService>bookReservations=new Reservations();

    public BookServiceImpl()throws Exception{
        bookBO=(BookBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.BOOK);
    }
    @Override
    public boolean saveBook(BookDTO bookDTO, PositionDTO positionDTO, AuthorDTO authorDTO) throws Exception {
       notyfyAllObservers();
        return bookBO.saveBook(bookDTO,positionDTO,authorDTO);
    }

    @Override
    public List<BookDTO> getAll() throws Exception {
        return bookBO.getAll();
    }

    @Override
    public BookDTO searchBook(int bid) throws Exception {
        return bookBO.searchBook(bid);
    }

    @Override
    public BookDTO searchBook(String bookName) throws Exception {
        return bookBO.searchBook(bookName);
    }

    @Override
    public boolean updateBook(BookDTO bookDTO, PositionDTO positionDTO) throws Exception {
        return bookBO.updateBook(bookDTO,positionDTO);
    }

    @Override
    public boolean removeBook(int bid) throws Exception {
        return bookBO.removeBook(bid);
    }

    @Override
    public List<BookDTO> getDetails(String name) throws Exception {
        return bookBO.getDetails(name);
    }

    @Override
    public boolean register(Observer observer) throws Exception {
        allBookObservers.add((Observers) observer);
        return true;
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        allBookObservers.remove(observer);
        return true;
    }

    @Override
    public void notyfyAllObservers() throws Exception {
        for(Observers allBookObserver:allBookObservers){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                allBookObserver.update();
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
        return bookReservations.reserve(id,this,true);

    }

    @Override
    public boolean release(Object id) throws Exception {
        return bookReservations.release(id,this);
    }
}
