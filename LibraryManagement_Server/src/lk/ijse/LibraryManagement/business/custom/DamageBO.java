package lk.ijse.LibraryManagement.business.custom;

import lk.ijse.LibraryManagement.business.SuperBO;
import lk.ijse.LibraryManagement.dto.DamageDTO;

import java.util.List;

public interface DamageBO extends SuperBO {

    public boolean saveDamageBooks(DamageDTO damageDTO)throws Exception;

    public boolean updateDamageBooks(DamageDTO damageDTO)throws Exception;

    public boolean removeDamageBooks(int dmgId)throws Exception;

    public DamageDTO searchDamageBooks(int dmgId)throws Exception;

    public DamageDTO searchDamageBooks(String dmgBookName)throws Exception;

    public List<DamageDTO> getAll()throws Exception;

    public List<DamageDTO> getDetails(String name)throws Exception;

}
