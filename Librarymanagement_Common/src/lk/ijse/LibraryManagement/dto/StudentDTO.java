package lk.ijse.LibraryManagement.dto;

import javafx.scene.control.DatePicker;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private int sid;
    private int RegId;
    private String name;
    private String address;
    private String bday;
    private String grade;
    private String photo;

    public StudentDTO(){

    }

    public StudentDTO(int sid, int regId, String name, String address, String bday, String grade, String photo) {
        this.sid = sid;
        RegId = regId;
        this.name = name;
        this.address = address;
        this.bday = bday;
        this.grade = grade;
        this.photo = photo;
    }

    public StudentDTO(int sid, int regId, String name) {
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getRegId() {
        return RegId;
    }

    public void setRegId(int regId) {
        RegId = regId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sid=" + sid +
                ", RegId=" + RegId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", bday='" + bday + '\'' +
                ", grade='" + grade + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
