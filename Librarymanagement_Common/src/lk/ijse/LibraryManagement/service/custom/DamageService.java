package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.DamageDTO;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

import javax.security.auth.Subject;
import java.util.List;

public interface DamageService extends SuperService, lk.ijse.LibraryManagement.observer.Subject,Reservation {

    public boolean saveDamageBooks(DamageDTO damageDTO)throws Exception;

    public boolean updateDamageBooks(DamageDTO damageDTO)throws Exception;

    public boolean removeDamageBooks(int dmgId)throws Exception;

    public DamageDTO searchDamageBooks(int dmgId)throws Exception;

    public DamageDTO searchDamageBooks(String dmgBookName)throws Exception;

    public List<DamageDTO> getAll()throws Exception;

    public List<DamageDTO> getDetails(String name)throws Exception;
}
