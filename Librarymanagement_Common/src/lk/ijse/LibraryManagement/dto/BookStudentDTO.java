package lk.ijse.LibraryManagement.dto;

import java.io.Serializable;

public class BookStudentDTO implements Serializable {

    private int bookStudentId;
    private int bid;
    private int sid;
    private String borrowDate;
    private String returnDate;
    private int number;
    private BookDTO bookDto;
    private StudentDTO studentDto;

    public BookStudentDTO(){

    }

    public BookStudentDTO(int bookStudentId, int bid, int sid, String borrowDate, String returnDate, int number) {
        this.bookStudentId = bookStudentId;
        this.bid = bid;
        this.sid = sid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.number = number;
    }

    public BookStudentDTO(int bookStudentId, int bid, int sid, String borrowDate, String returnDate, int number, BookDTO bookDto, StudentDTO studentDto) {
        this.bookStudentId = bookStudentId;
        this.bid = bid;
        this.sid = sid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.number = number;
        this.bookDto = bookDto;
        this.studentDto = studentDto;
    }

    public BookStudentDTO(int bookStudentId, int bid, int sid, String borrowDate, String returnDate, int number, BookDTO bookDTO) {
    }

    public int getBookStudentId() {
        return bookStudentId;
    }

    public void setBookStudentId(int bookStudentId) {
        this.bookStudentId = bookStudentId;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public BookDTO getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDTO bookDto) {
        this.bookDto = bookDto;
    }

    public StudentDTO getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDTO studentDto) {
        this.studentDto = studentDto;
    }
}
