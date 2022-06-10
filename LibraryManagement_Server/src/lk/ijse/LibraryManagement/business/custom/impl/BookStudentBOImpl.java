package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.BookStudentBO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.BookStudentDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.entity.*;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.BookAuthorRepository;
import lk.ijse.LibraryManagement.repo.custom.PositionRepository;
import lk.ijse.LibraryManagement.repo.custom.StudentBookRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BookStudentBOImpl implements BookStudentBO {

    private StudentBookRepository studentBookRepository;
    private BookAuthorRepository bookAuthorRepository;

    private PositionRepository positionRepository;



    public BookStudentBOImpl() {

        studentBookRepository=(StudentBookRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.STUDENTBOOK);

        bookAuthorRepository=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.AUTHORBOOK);

        positionRepository=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.POSITION);
    }

    @Override
    public boolean saveBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            //session.beginTransaction();
            studentBookRepository.setSession(session);
            BookStudent bookStudent=new BookStudent();
            Book book = new Book();
            book.setBid(bookDTO.getBid());
            bookStudent.setBook(book);
            Student student = new Student();
            student.setSid(studentDTO.getSid());
            bookStudent.setStudent(student);

            bookStudent.setBookStudentId(bookStudentDTO.getBookStudentId());

            bookStudent.setBorrowDate(bookStudentDTO.getBorrowDate());

            bookStudent.setReturnDate(bookStudentDTO.getReturnDate());

            bookStudent.setNumber(bookStudentDTO.getNumber());

            studentBookRepository.save(bookStudent);
            //boolean isSave=false;
            session.getTransaction().begin();
            studentBookRepository.save(bookStudent);

                session.getTransaction().commit();
                session.close();
                return true;

        }
    }

    @Override
    public boolean updateBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            session.beginTransaction();
            studentBookRepository.setSession(session);
            BookStudent bookStudent=new BookStudent();
            Book book = new Book();
            book.setBid(bookDTO.getBid());
            bookStudent.setBook(book);
            Student student = new Student();
            student.setSid(studentDTO.getSid());
            bookStudent.setStudent(student);

            bookStudent.setBookStudentId(bookStudentDTO.getBookStudentId());

            bookStudent.setBorrowDate(bookStudentDTO.getBorrowDate());

            bookStudent.setReturnDate(bookStudentDTO.getReturnDate());

            bookStudent.setNumber(bookStudentDTO.getNumber());
            boolean isUpdate=false;
            if(studentBookRepository.update(bookStudent)){
                isUpdate=true;
                session.getTransaction().commit();
                session.close();
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isUpdate;
        }
    }

    @Override
    public boolean removeBookStudent(int bid) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            session.beginTransaction();
            studentBookRepository.setSession(session);
            boolean isRemove=false;
            if(studentBookRepository.removeBookStudent(bid)){
                isRemove=true;
                session.getTransaction().commit();
                session.close();
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isRemove;
        }
    }

    @Override
    public List<BookStudentDTO> getAll() throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);

            List<BookStudent> list=studentBookRepository.getAll();
            List<BookStudentDTO> books=new ArrayList<>();
            if(list!=null){
                for (BookStudent bookStudent : list) {
                    books.add(new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber(),new BookDTO(bookStudent.getBook().getCategory(),bookStudent.getBook().getMachiningNo(),bookStudent.getBook().getBookName(),bookStudent.getBook().getPublisher(),bookStudent.getBook().getPosition().getPosId() ),new StudentDTO(bookStudent.getStudent().getSid(), bookStudent.getStudent().getRegId(), bookStudent.getStudent().getName())));
                }
            }
            return books;
        }
    }

    @Override
    public List<BookStudentDTO> searchBookStudentByStudent(int sid) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);

            List<BookStudent> list=studentBookRepository.searchBookStudentByStudent(sid);
            List<BookStudentDTO> books=new ArrayList<>();
            if(list!=null){
                for (BookStudent bookStudent : list) {
                    books.add(new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber()));
                }
            }
            return books;
        }
    }

    @Override
    public List<BookStudentDTO> searchBookStudentByBook(int bid) throws Exception {



        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);

            List<BookStudent> list=studentBookRepository.searchBookStudentByBook(bid);
            List<BookStudentDTO> books=new ArrayList<>();
            if(list!=null){
                for (BookStudent bookStudent : list) {
                    books.add(new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber()));
                }
            }
            return books;
        }
    }

    @Override
    public BookStudentDTO getLastReservationDetails(int stId) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);
            BookStudent bookStudent=studentBookRepository.getLastReservationDetails(stId);
            if(bookStudent!=null){
                session.close();
                Book book = bookStudent.getBook();

                BookStudentDTO bookStudentDTO = new BookStudentDTO(bookStudent.getBookStudentId(), bookStudent.getBook().getBid(), bookStudent.getStudent().getSid(), bookStudent.getBorrowDate(), bookStudent.getReturnDate(), bookStudent.getNumber(), new BookDTO(bookStudent.getBook().getCategory(), bookStudent.getBook().getMachiningNo(), bookStudent.getBook().getBookName(), bookStudent.getBook().getPublisher(), bookStudent.getBook().getPosition().getPosId()));

                BookDTO bookDTO = new BookDTO();
                bookDTO.setBid(book.getBid());


                bookDTO.setPosId(book.getPosition().getPosId());
                bookStudentDTO.setBookDto(bookDTO);

                return bookStudentDTO;

            }
            return null;
        }
    }

    @Override
    public BookStudentDTO getLastRes(int sId) throws Exception {
        return null;
    }
}
