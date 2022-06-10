package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.entity.Damage;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface DamageRepository extends CrudRepository<Damage , String> {

    public boolean removeDamageBooks(int dmgId)throws Exception;

    public Damage searchDamageBooks(int dmgId)throws Exception;

    public Damage searchDamageBooks(String dmgBookName)throws Exception;

    public List<Damage> getDetails(String name)throws Exception;

}
