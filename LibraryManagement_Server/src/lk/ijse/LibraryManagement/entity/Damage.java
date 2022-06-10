package lk.ijse.LibraryManagement.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Damage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int damageId;

    private int bid;

    public Damage() {
    }

    public Damage(int damageId, int bid) {
        this.damageId = damageId;
        this.bid = bid;
    }
    public Damage(int bid) {

        this.bid = bid;
    }

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damageId=" + damageId +
                ", bid=" + bid +
                '}';
    }
}
