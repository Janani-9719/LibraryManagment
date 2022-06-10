package lk.ijse.LibraryManagement.entity;

import javax.persistence.*;

@Entity
public class BookStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookStudentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bid")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sid")
    private Student student;

    private String borrowDate;
    private String returnDate;
    private int number;

    public BookStudent() {
    }

    public BookStudent(int bookStudentId, Book book, Student student, String borrowDate, String returnDate, int number) {
        this.bookStudentId = bookStudentId;
        this.book = book;
        this.student = student;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.number = number;
    }

    public BookStudent(Book book, Student student, String borrowDate, String returnDate, int number) {

        this.book = book;
        this.student = student;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.number = number;
    }

    public int getBookStudentId() {
        return bookStudentId;
    }

    public void setBookStudentId(int bookStudentId) {
        this.bookStudentId = bookStudentId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BookStudent{" +
                "bookStudentId=" + bookStudentId +
                ", book=" + book +
                ", student=" + student +
                ", borrowDate='" + borrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", number=" + number +
                '}';
    }
}
