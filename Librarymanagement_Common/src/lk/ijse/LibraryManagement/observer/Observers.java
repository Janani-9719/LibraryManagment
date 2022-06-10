package lk.ijse.LibraryManagement.observer;

import java.rmi.Remote;

public interface Observers extends Remote {
    public void update()throws Exception;



}
