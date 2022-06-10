package lk.ijse.LibraryManagement.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Position {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int posId;

    private double dvNo;
    private int rackNo;
    private int rowNo;
    private int colNo;

    public Position() {
    }

    public Position(int posId, double dvNo, int rackNo, int rowNo, int colNo) {
        this.posId = posId;
        this.dvNo = dvNo;
        this.rackNo = rackNo;
        this.rowNo = rowNo;
        this.colNo = colNo;
    }

    public Position(double dvNo, int rackNo, int rowNo, int colNo) {

        this.dvNo = dvNo;
        this.rackNo = rackNo;
        this.rowNo = rowNo;
        this.colNo = colNo;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public double getDvNo() {
        return dvNo;
    }

    public void setDvNo(double dvNo) {
        this.dvNo = dvNo;
    }

    public int getRackNo() {
        return rackNo;
    }

    public void setRackNo(int rackNo) {
        this.rackNo = rackNo;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getColNo() {
        return colNo;
    }

    public void setColNo(int colNo) {
        this.colNo = colNo;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posId=" + posId +
                ", dvNo=" + dvNo +
                ", rackNo=" + rackNo +
                ", rowNo=" + rowNo +
                ", colNo=" + colNo +
                '}';
    }
}
