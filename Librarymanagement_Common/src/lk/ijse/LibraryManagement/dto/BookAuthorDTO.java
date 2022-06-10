package lk.ijse.LibraryManagement.dto;

import java.io.Serializable;

public class BookAuthorDTO implements Serializable {

    private int bookAuthorId;
    private int bid;
    private int authorId;

    public BookAuthorDTO(){

    }

    public BookAuthorDTO(int bookAuthorId, int bid, int authorId) {
        this.bookAuthorId = bookAuthorId;
        this.bid = bid;
        this.authorId = authorId;
    }

    public int getBookAuthorId() {
        return bookAuthorId;
    }

    public void setBookAuthorId(int bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BookAuthorDTO{" +
                "bookAuthorId=" + bookAuthorId +
                ", bid=" + bid +
                ", authorId=" + authorId +
                '}';
    }
}
