package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.DamageBO;
import lk.ijse.LibraryManagement.dto.DamageDTO;
import lk.ijse.LibraryManagement.observer.Observers;
import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.DamageService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class DamageServiceImpl extends UnicastRemoteObject implements DamageService {

    private DamageBO damageBO;
    private static ArrayList<Observers>allDamageObservers=new ArrayList<>();
    private static Reservations<DamageService>damageareservations=new Reservations();

    public DamageServiceImpl()throws Exception{
        damageBO=(DamageBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.DAMAGE);
    }
    @Override
    public boolean saveDamageBooks(DamageDTO damageDTO) throws Exception {
        notyfyAllObservers();
        return damageBO.saveDamageBooks(damageDTO);
    }

    @Override
    public boolean updateDamageBooks(DamageDTO damageDTO) throws Exception {
        if(damageareservations.reserve(damageDTO.getDamageId(),this,true)){
            boolean result=damageBO.updateDamageBooks(damageDTO);
            notyfyAllObservers();
            if(damageareservations.checkStatus(damageDTO.getDamageId(),this)){
                damageareservations.release(damageDTO.getDamageId(),this);
            }
            return result;
        }else {
            return false;
        }

    }

    @Override
    public boolean removeDamageBooks(int dmgId) throws Exception {
        return damageBO.removeDamageBooks(dmgId);
    }

    @Override
    public DamageDTO searchDamageBooks(int dmgId) throws Exception {
        return damageBO.searchDamageBooks(dmgId);
    }

    @Override
    public DamageDTO searchDamageBooks(String dmgBookName) throws Exception {
        return damageBO.searchDamageBooks(dmgBookName);
    }

    @Override
    public List<DamageDTO> getAll() throws Exception {
        return damageBO.getAll();
    }

    @Override
    public List<DamageDTO> getDetails(String name) throws Exception {
        return damageBO.getDetails(name);
    }

    @Override
    public boolean register(Observer observer) throws Exception {
        allDamageObservers.add((Observers) observer);
        return true;
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        allDamageObservers.remove(observer);
        return true;
    }

    @Override
    public void notyfyAllObservers() throws Exception {
        for (Observers allDamageObserver : allDamageObservers) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {

                            try {
                                allDamageObserver.update();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
            ).start();

        }

    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return damageareservations.reserve(id,this,true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return damageareservations.release(id,this);
    }
}
