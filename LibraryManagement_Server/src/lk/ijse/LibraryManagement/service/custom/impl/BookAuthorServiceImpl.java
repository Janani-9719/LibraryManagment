package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.BookAuthorBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookAuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.observer.Observers;

import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.BookAuthorService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observer;

public class BookAuthorServiceImpl extends UnicastRemoteObject implements BookAuthorService {

    private BookAuthorBO bookAuthorBO;
    private static ArrayList<Observers>allBookAuthorObservers= new ArrayList<>();
    private static Reservations<BookAuthorService> bookAuthorReservations=new Reservations();

    public BookAuthorServiceImpl() throws Exception{
        bookAuthorBO=(BookAuthorBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.AUTHORBOOK);
    }
    @Override
    public boolean updateAuthorForBook(AuthorDTO authorDTO, int bid) throws Exception {
        return bookAuthorBO.updateAuthorForBook(authorDTO,bid);
    }

    @Override
    public boolean updateBookForAuthor(BookDTO bookDTO, PositionDTO positionDTO, int aid) throws Exception {
        return bookAuthorBO.updateBookForAuthor(bookDTO,positionDTO,aid);
    }

    @Override
    public AuthorDTO getAuthorForBook(int bid) throws Exception {
        return bookAuthorBO.getAuthorForBook(bid);
    }

    @Override
    public BookDTO getBookForAuthor(int aid) throws Exception {
     return bookAuthorBO.getBookForAuthor(aid);
    }

    @Override
    public boolean saveBookWithAuthor(BookDTO baDto, AuthorDTO authorDto) throws Exception {
        notyfyAllObservers();
        return bookAuthorBO.saveBookWithAuthor(baDto,authorDto);
    }

    @Override
    public boolean register(Observer observer) throws Exception {
        allBookAuthorObservers.add((Observers) observer);
        return true;
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        allBookAuthorObservers.remove(observer);
        return true;
    }

    @Override
    public void notyfyAllObservers() throws Exception {
        for(Observers allBookAuthorObsever:allBookAuthorObservers){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                allBookAuthorObsever.update();
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
        return bookAuthorReservations.reserve(id,this,true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return  bookAuthorReservations.release(id,this);

    }
}
