package lk.ijse.LibraryManagement.server;

import lk.ijse.LibraryManagement.resources.HibernateUtil;
import lk.ijse.LibraryManagement.service.impl.ServiceFactoryImpl;
import org.hibernate.Session;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main  {
    public static void main(String[] args)throws Exception{
        Registry registry=LocateRegistry.createRegistry(5050);
        registry.bind("GDSE",ServiceFactoryImpl.getInsatance());
        HibernateUtil.getSessionFactory().isOpen();

        System.out.println("Server is Started...!");
    }
}
