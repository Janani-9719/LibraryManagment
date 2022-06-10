package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.AuthorBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.observer.Observers;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.AuthorService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class AuthorServiceImpl extends UnicastRemoteObject implements AuthorService {

    private AuthorBO authorBO;
    private static Reservations<AuthorService>authorReservations=new Reservations();
    public static ArrayList<Observers>allAuthorObservers=new ArrayList<>();



    public AuthorServiceImpl() throws Exception{
        authorBO=(AuthorBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.AUTHOR);
    }
    @Override
    public boolean saveAuthor(AuthorDTO authorDTO) throws Exception {

        return authorBO.saveAuthor(authorDTO);
    }

    @Override
    public List<AuthorDTO> getAll() throws Exception {
        return authorBO.getAll();

    }

    @Override
    public boolean update(AuthorDTO authorDTO) throws Exception {
        if(authorReservations.reserve(authorDTO.getAuthorId(),this,true)){
          boolean result=authorBO.update(authorDTO);
          notyfyAllObservers();

          if(authorReservations.checkStatus(authorDTO.getAuthorId(),this)){
              authorReservations.release(authorDTO.getAuthorId(),this);

          }
          return result;
        }else {
            return false;
        }

    }

    @Override
    public AuthorDTO searchAuthor(int aid) throws Exception {
        return authorBO.searchAuthor(aid);
    }

    @Override
    public AuthorDTO searchAuthor(String name) throws Exception {
        return authorBO.searchAuthor(name);
    }

    @Override
    public boolean removeAuthor(int aid) throws Exception {
        return false;
    }


    @Override
    public boolean register(Observer observer) throws Exception {
        allAuthorObservers.add((Observers) observer);
        return true;
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        allAuthorObservers.remove(observer);
        return false;
    }

    @Override
    public void notyfyAllObservers() throws Exception {
   for(Observers allAuthorObserver: allAuthorObservers){
       new Thread(
               new Runnable() {
                   @Override
                   public void run() {
                       try {
                           allAuthorObserver.update();
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
        return authorReservations.reserve(id,this,true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return authorReservations.release(id,this);
    }
}
