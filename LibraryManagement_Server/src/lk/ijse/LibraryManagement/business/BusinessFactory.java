package lk.ijse.LibraryManagement.business;

import lk.ijse.LibraryManagement.business.custom.impl.*;

public class BusinessFactory {
    private static BusinessFactory boFactory;

    private BusinessFactory() {

    }

    public enum BOTypes {
        POSITION, BOOK, STUDENT, STUDENTBOOK, AUTHOR, AUTHORBOOK, DAMAGE
    }

    public static BusinessFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BusinessFactory();
        }
        return boFactory;
    }

    public SuperBO getBo(BOTypes boTypes) {

        switch (boTypes) {
            case STUDENTBOOK:
                return new BookStudentBOImpl();
            case BOOK:
                return new BookBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case AUTHOR:
                return new AuthorBOImpl();
            case AUTHORBOOK:
                return new BookAuthorBOImpl();
            case DAMAGE:
                return new DamageBOImpl();
            case POSITION:
                return new PositionBOImpl();
            default:
                return null;
        }

    }
}
