package lk.ijse.LibraryManagement.Proxy;

import lk.ijse.LibraryManagement.service.ServiceFactory;
import lk.ijse.LibraryManagement.service.SuperService;
import lk.ijse.LibraryManagement.service.custom.*;

import java.rmi.Naming;

public class ProxyHandler implements ServiceFactory {


    private static ProxyHandler proxyHandler;
    private AuthorService authorService;
    private BookAuthorService bookAuthorService;
    private BookService bookService;
    private BookStudentService bookStudentService;
    private DamageService damageService;
    private PositionService positionService;
    private StudentService studentService;


    public ProxyHandler() throws Exception{
        ServiceFactory serviceFactory=(ServiceFactory)Naming.lookup("rmi://localhost:5050/GDSE");

        authorService= serviceFactory.getService(ServiceTypes.AUTHOR);
        bookAuthorService=serviceFactory.getService(ServiceTypes.AUTHORBOOK);
        bookService=serviceFactory.getService(ServiceTypes.BOOK);
        bookStudentService=serviceFactory.getService(ServiceTypes.STUDENTBOOK);
        damageService=serviceFactory.getService(ServiceTypes.DAMAGE);
        positionService=serviceFactory.getService(ServiceTypes.POSITION);
        System.out.println("position");
        studentService=serviceFactory.getService(ServiceTypes.STUDENT);
    }

    public static ProxyHandler getInstance()throws Exception{
        if(proxyHandler == null){
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }
    @Override
    public <T extends SuperService> T getService(ServiceTypes types) throws Exception {
        switch (types) {
            case AUTHOR:
                return (T) authorService;
            case AUTHORBOOK:
                return (T) bookAuthorService;
            case BOOK:
                return (T) bookService;
            case STUDENTBOOK:
                return (T) bookStudentService;
            case DAMAGE:
                return (T) damageService;
            case POSITION:
                return (T) positionService;
            case STUDENT:
                return (T) studentService;

                default:
                    return null;
        }
    }

}
