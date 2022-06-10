package lk.ijse.LibraryManagement.observer;

import java.rmi.Remote;
import java.util.Observer;

public interface Subject extends Remote {

    public boolean register (Observer observer)throws Exception;
    public boolean unregister (Observer observer)throws Exception;
    public void notyfyAllObservers()throws Exception;


}
