package lk.ijse.LibraryManagement.entity;

import javax.persistence.*;


@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bid;

    @ManyToOne(cascade = CascadeType.ALL)
    private Position position;

    private String category;
    private int machiningNo;
    private String receiveDate;
    private int batchNo;
    private String bookName;
    private String publisher;
    private String publishDate;
    private String pages;
    private double price;
    private String suplier;
    private String removedDate;
    private String other;

    public Book() {
    }

    public Book(int bid, Position position, String category, int machiningNo, String receiveDate, int batchNo, String bookName, String publisher, String publishDate, String pages, double price, String suplier, String removedDate, String other) {
        this.bid = bid;
        this.position = position;
        this.category = category;
        this.machiningNo = machiningNo;
        this.receiveDate = receiveDate;
        this.batchNo = batchNo;
        this.bookName = bookName;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.pages = pages;
        this.price = price;
        this.suplier = suplier;
        this.removedDate = removedDate;
        this.other = other;
    }

    public Book(Position position, String category, int machiningNo, String receiveDate, int batchNo, String bookName, String publisher, String publishDate, String pages, double price, String suplier, String removedDate, String other) {

        this.position = position;
        this.category = category;
        this.machiningNo = machiningNo;
        this.receiveDate = receiveDate;
        this.batchNo = batchNo;
        this.bookName = bookName;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.pages = pages;
        this.price = price;
        this.suplier = suplier;
        this.removedDate = removedDate;
        this.other = other;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMachiningNo() {
        return machiningNo;
    }

    public void setMachiningNo(int machiningNo) {
        this.machiningNo = machiningNo;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public int getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(int batchNo) {
        this.batchNo = batchNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSuplier() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier = suplier;
    }

    public String getRemovedDate() {
        return removedDate;
    }

    public void setRemovedDate(String removedDate) {
        this.removedDate = removedDate;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", position=" + position +
                ", category='" + category + '\'' +
                ", machiningNo=" + machiningNo +
                ", receiveDate='" + receiveDate + '\'' +
                ", batchNo=" + batchNo +
                ", bookName='" + bookName + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", pages='" + pages + '\'' +
                ", price=" + price +
                ", suplier='" + suplier + '\'' +
                ", removedDate='" + removedDate + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
