package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.BookAuthorBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.entity.Position;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.AuthorRepository;
import lk.ijse.LibraryManagement.repo.custom.BookAuthorRepository;
import lk.ijse.LibraryManagement.repo.custom.BookRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.Session;

public class BookAuthorBOImpl implements BookAuthorBO {


    private BookAuthorRepository bookAuthorRepository;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookAuthorBOImpl() {
        bookAuthorRepository = (BookAuthorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.AUTHORBOOK);
    }

    @Override
    public boolean updateAuthorForBook(AuthorDTO authorDTO, int bid) throws Exception {


        Author author = new Author(authorDTO.getAuthorId(), authorDTO.getAuthorName());
        boolean isUpdate = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            bookAuthorRepository.setSession(session);
            if (bookAuthorRepository.updateAuthorForBook(author, bid)) {
                isUpdate = true;
                session.getTransaction().commit();
                session.close();
            } else {
                session.getTransaction().rollback();
                session.close();
            }
            return isUpdate;

        }
    }

    @Override
    public boolean updateBookForAuthor(BookDTO bookDTO, PositionDTO positionDTO, int aid) throws Exception {
        Book book = new Book(bookDTO.getBid(), new Position(positionDTO.getPosId(), positionDTO.getDvNo(), positionDTO.getRackNo(), positionDTO.getRowNo(), positionDTO.getColNo()), bookDTO.getCategory(), bookDTO.getMachiningNo(), bookDTO.getReceiveDate(), bookDTO.getBatchNo(), bookDTO.getBookName(), bookDTO.getPublisher(), bookDTO.getPublishDate(), bookDTO.getPages(), bookDTO.getPrice(), bookDTO.getSuplier(), bookDTO.getRemovedDate(), bookDTO.getOther());
        boolean isUpdate = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            bookAuthorRepository.setSession(session);
            if (bookAuthorRepository.updateBookForAuthor(book, aid)) {
                isUpdate = true;
                session.getTransaction().commit();
                session.close();
            } else {
                session.getTransaction().rollback();
                session.close();
            }
            return isUpdate;

        }
    }

    @Override
    public AuthorDTO getAuthorForBook(int bid) throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookAuthorRepository.setSession(session);
            Author author = bookAuthorRepository.getAuthorForBook(bid);
            if (author == null) {
                session.close();
                return null;
            } else {
                session.close();
                return new AuthorDTO(author.getAuthorId(), author.getAuthorName());
            }
        }
    }

    @Override
    public BookDTO getBookForAuthor(int aid) throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookAuthorRepository.setSession(session);
            Book book = bookAuthorRepository.getBookForAuthor(aid);
            if (book == null) {
                session.close();
                return null;
            } else {
                session.close();
                return new BookDTO(book.getBid(), book.getPosition().getPosId(), book.getCategory(), book.getMachiningNo(), book.getReceiveDate(), book.getBatchNo(), book.getBookName(), book.getPublisher(), book.getPublishDate(), book.getPages(), book.getPrice(), book.getSuplier(), book.getRemovedDate(), book.getOther());
            }
        }

    }

    @Override
    public boolean saveBookWithAuthor(BookDTO bookDTO, AuthorDTO authorDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookAuthorRepository.setSession(session);
            bookRepository.setSession(session);
            authorRepository.setSession(session);
            Book book = new Book(bookDTO.getBid(), new Position(), bookDTO.getCategory(), bookDTO.getMachiningNo(), bookDTO.getReceiveDate(), bookDTO.getBatchNo(), bookDTO.getBookName(), bookDTO.getPublisher(), bookDTO.getPublishDate(), bookDTO.getPages(), bookDTO.getPrice(), bookDTO.getSuplier(), bookDTO.getRemovedDate(), bookDTO.getOther());
            Author author = new Author(authorDTO.getAuthorId(), authorDTO.getAuthorName());

            boolean isAdd =false;
            session.getTransaction().commit();
            return isAdd;
        }

    }
}
