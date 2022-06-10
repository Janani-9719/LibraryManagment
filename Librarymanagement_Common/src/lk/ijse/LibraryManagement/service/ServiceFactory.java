package lk.ijse.LibraryManagement.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {

    public enum ServiceTypes{

        POSITION,BOOK,STUDENT,STUDENTBOOK,AUTHOR,AUTHORBOOK,DAMAGE
    }

    public <T extends SuperService>T getService(ServiceTypes types)throws Exception;

}
