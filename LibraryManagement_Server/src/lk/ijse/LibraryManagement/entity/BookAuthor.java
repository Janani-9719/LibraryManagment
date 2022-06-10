package lk.ijse.LibraryManagement.entity;

import javax.persistence.*;

@Entity
public class BookAuthor {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookAuthorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bid")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="aid")
    private Author author;

    public BookAuthor() {
    }

    public BookAuthor(int bookAuthorId, Book book, Author author) {
        this.bookAuthorId = bookAuthorId;
        this.book = book;
        this.author = author;
    }

    public BookAuthor(Book book, Author author) {

        this.book = book;
        this.author = author;
    }

    public int getBookAuthorId() {
        return bookAuthorId;
    }

    public void setBookAuthorId(int bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "bookAuthorId=" + bookAuthorId +
                ", book=" + book +
                ", author=" + author +
                '}';
    }
}
