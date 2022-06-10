package lk.ijse.LibraryManagement.dto;

import java.io.Serializable;

public class DamageDTO implements Serializable {

    private int damageId;
    private int bid;

    public DamageDTO(int damageId, int bid) {
        this.damageId = damageId;
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
        return "DamageDTO{" +
                "damageId=" + damageId +
                ", bid=" + bid +
                '}';
    }
}
