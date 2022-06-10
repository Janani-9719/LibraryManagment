package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.BookBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.entity.BookAuthor;
import lk.ijse.LibraryManagement.entity.Position;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.AuthorRepository;
import lk.ijse.LibraryManagement.repo.custom.BookAuthorRepository;
import lk.ijse.LibraryManagement.repo.custom.BookRepository;
import lk.ijse.LibraryManagement.repo.custom.PositionRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    private BookRepository bookRepository;
    private BookAuthorRepository bookAuthorRepository;
    private AuthorRepository authorRepository;
    private PositionRepository positionRepository;

    public BookBOImpl(){
        bookAuthorRepository=(BookAuthorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.AUTHORBOOK);
        bookRepository=(BookRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    }

    @Override
    public boolean saveBook(BookDTO bookDTO, PositionDTO positionDTO, AuthorDTO authorDTO) throws Exception {


        Book book=new Book(bookDTO.getBid(),new Position(positionDTO.getPosId(),positionDTO.getDvNo(),positionDTO.getRackNo(),positionDTO.getRowNo(),positionDTO.getColNo()),bookDTO.getCategory(),bookDTO.getMachiningNo(),bookDTO.getReceiveDate(),bookDTO.getBatchNo(),bookDTO.getBookName(),bookDTO.getPublisher(),bookDTO.getPublishDate(),bookDTO.getPages(),bookDTO.getPrice(),bookDTO.getSuplier(),bookDTO.getRemovedDate(),bookDTO.getOther());
        Author author=new Author(authorDTO.getAuthorId(),authorDTO.getAuthorName());
        try(Session session=HibernateUtil.getSessionFactory().openSession()) {
            //session.beginTransaction();
            bookRepository.setSession(session);
            bookAuthorRepository.setSession(session);
            session.getTransaction().begin();

            bookAuthorRepository.save(new BookAuthor(book, author));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<BookDTO> getAll() throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            bookRepository.setSession(session);
            List<Book> list=bookRepository.getAll();
            List<BookDTO> books=new ArrayList<>();
            if(list!=null){
                for (Book book : list) {
                    books.add(new BookDTO(book.getBid(),book.getPosition().getPosId(),book.getCategory(),book.getMachiningNo(),book.getReceiveDate(),book.getBatchNo(),book.getBookName(),book.getPublisher(),book.getPublishDate(),book.getPages(),book.getPrice(),book.getSuplier(),book.getRemovedDate(),book.getOther()));
                }
            }
            session.close();
            return books;
        }
    }

    @Override
    public BookDTO searchBook(int bid) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            bookRepository.setSession(session);
            Book book=bookRepository.searchBook(bid);
            System.out.println("Yudi2 "+book);
            if(book!=null){
                session.close();
                return new BookDTO(book.getBid(),book.getPosition().getPosId(),book.getCategory(),book.getMachiningNo(),book.getReceiveDate(),book.getBatchNo(),book.getBookName(),book.getPublisher(),book.getPublishDate(),book.getPages(),book.getPrice(),book.getSuplier(),book.getRemovedDate(),book.getOther());
            }
            session.close();
            return null;
        }
    }

    @Override
    public BookDTO searchBook(String bookName) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookRepository.setSession(session);
            Book book = bookRepository.searchBook(bookName);

            if (book != null) {
                session.close();
                return new BookDTO(book.getBid(), book.getPosition().getPosId(), book.getCategory(), book.getMachiningNo(), book.getReceiveDate(), book.getBatchNo(), book.getBookName(), book.getPublisher(), book.getPublishDate(), book.getPages(), book.getPrice(), book.getSuplier(), book.getRemovedDate(), book.getOther());
            }
            session.close();
            return null;
        }
    }

    @Override
    public boolean updateBook(BookDTO bookDTO, PositionDTO positionDTO) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            bookRepository.setSession(session);

            Book book=new Book(bookDTO.getBid(),new Position(positionDTO.getPosId(),positionDTO.getDvNo(),positionDTO.getRackNo(),positionDTO.getRowNo(),positionDTO.getColNo()),bookDTO.getCategory(),bookDTO.getMachiningNo(),bookDTO.getReceiveDate(),bookDTO.getBatchNo(),bookDTO.getBookName(),bookDTO.getPublisher(),bookDTO.getPublishDate(),bookDTO.getPages(),bookDTO.getPrice(),bookDTO.getSuplier(),bookDTO.getRemovedDate(),bookDTO.getOther());

            boolean isUpdate=false;
            if(bookRepository.update(book)){
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
    public boolean removeBook(int bid) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            //session.beginTransaction();
            bookRepository.setSession(session);
            session.getTransaction().begin();
            //boolean isRemove=false;
            bookRepository.removeBook(bid);
                session.getTransaction().commit();
                session.close();
                return true;

        }
    }

    @Override
    public List<BookDTO> getDetails(String name) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            bookRepository.setSession(session);
            List<Book> list=bookRepository.getDetails(name);
            List<BookDTO> books=new ArrayList<>();
            if(list!=null){
                for (Book book : list) {
                    books.add(new BookDTO(book.getBid(),book.getPosition().getPosId(),book.getCategory(),book.getMachiningNo(),book.getReceiveDate(),book.getBatchNo(),book.getBookName(),book.getPublisher(),book.getPublishDate(),book.getPages(),book.getPrice(),book.getSuplier(),book.getRemovedDate(),book.getOther()));
                }
            }
            session.close();
            return books;
        }
    }
}
