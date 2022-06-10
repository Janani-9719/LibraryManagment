package lk.ijse.LibraryManagement.service.impl;

import lk.ijse.LibraryManagement.business.custom.BookStudentBO;
import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.SuperService;
import lk.ijse.LibraryManagement.service.custom.BookStudentService;
import lk.ijse.LibraryManagement.service.custom.impl.*;

import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    public ServiceFactoryImpl() throws Exception{

    }

    private static ServiceFactoryImpl serviceFactory;

    public static ServiceFactoryImpl getInsatance() throws Exception{

        if(serviceFactory==null){
            serviceFactory= new ServiceFactoryImpl();
        }
        return serviceFactory;

    }
    @Override
    public <T extends SuperService> T getService(ServiceTypes types) throws Exception {
        switch (types){
            case BOOK:
                return (T) new BookServiceImpl();
            case AUTHOR:
                return (T) new AuthorServiceImpl();
            case AUTHORBOOK:
                return (T) new BookAuthorServiceImpl();
            case STUDENT:
                return (T) new StudentServiceImpl();
            case STUDENTBOOK:
                return (T) new BookStudentServiceImpl();
            case POSITION:
                return (T) new PositionServiceImpl();
            case DAMAGE:
                return (T) new DamageServiceImpl();
                default:
                    return null;



        }
    }
}
