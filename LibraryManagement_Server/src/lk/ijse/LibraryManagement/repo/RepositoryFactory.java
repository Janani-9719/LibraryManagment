package lk.ijse.LibraryManagement.repo;

import lk.ijse.LibraryManagement.repo.custom.impl.*;

public class RepositoryFactory {

    private static RepositoryFactory repositoryFactory;

    private RepositoryFactory() {
    }

    public enum RepositoryTypes{
        POSITION,BOOK,STUDENT,STUDENTBOOK,AUTHOR,AUTHORBOOK,DAMAGE
    }

    public static RepositoryFactory getInstance(){

        if(repositoryFactory==null){
            repositoryFactory=new RepositoryFactory();
        }
        return repositoryFactory;
    }

    public <T>T getRepository(RepositoryTypes repositoryTypes){

        switch(repositoryTypes){

            case BOOK:
                return (T) new BookRepositoryImpl();
            case AUTHOR:
                return (T) new AuthorRepositoryImpl();
            case AUTHORBOOK:
                return (T) new BookAuthorRepositoryImpl();
            case DAMAGE:
                return (T) new DamageRepositoryImpl();
            case POSITION:
                return (T) new PositionRepositoryImpl();
            case STUDENT:
                return (T) new StudentRepositoryImpl();
            case STUDENTBOOK:
                return (T) new StudentBookRepositoryImpl();
            default:
                return null;
        }
    }


}
