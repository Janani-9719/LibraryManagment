package lk.ijse.LibraryManagement.service.custom.impl;

import lk.ijse.LibraryManagement.business.BusinessFactory;
import lk.ijse.LibraryManagement.business.custom.PositionBO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.observer.Observers;
import lk.ijse.LibraryManagement.reservations.Reservations;
import lk.ijse.LibraryManagement.service.custom.PositionService;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class PositionServiceImpl extends UnicastRemoteObject implements PositionService {

   private PositionBO positionBO;
   private static ArrayList<Observers>allPositionObservers= new ArrayList<>();
   private static Reservations<PositionService> positionreservations= new Reservations();

   public PositionServiceImpl()throws Exception{
       positionBO=(PositionBO)BusinessFactory.getInstance().getBo(BusinessFactory.BOTypes.POSITION);
   }
    @Override
    public boolean savePosition(PositionDTO positionDTO) throws Exception {
       notyfyAllObservers();
        return positionBO.savePosition(positionDTO);
    }

    @Override
    public boolean updatePosition(PositionDTO positionDTO) throws Exception {
       if(positionreservations.reserve(positionDTO.getPosId(),this,true)){
           boolean result=positionBO.updatePosition(positionDTO);
           notyfyAllObservers();
           if(positionreservations.checkStatus(positionDTO.getPosId(),this)){
               positionreservations.release(positionDTO.getPosId(),this);
           }
           return result;
       }else {
           return false;
       }
    }

    @Override
    public boolean removePosition(int posId) throws Exception {
        return positionBO.removePosition(posId);
    }

    @Override
    public List<PositionDTO> getAll() throws Exception {
        return positionBO.getAll();
    }

    @Override
    public List<PositionDTO> searchPositionByRack(int rackNo) throws Exception {
        return positionBO.searchPositionByRack(rackNo);
    }

    @Override
    public PositionDTO searchPositionByThreeId(int rackNo, int columnNo, int rowNo) throws Exception {
        return positionBO.searchPositionByThreeId(rackNo,columnNo,rowNo);
    }

    @Override
    public PositionDTO searchPositionByPosID(int posId) throws Exception {
        return positionBO.searchPositionByPosID(posId);
    }

    @Override
    public boolean register(Observer observer) throws Exception {
       allPositionObservers.add((Observers) observer);
       return true;
    }

    @Override
    public boolean unregister(Observer observer) throws Exception {
        allPositionObservers.remove(observer);
        return true;
    }

    @Override
    public void notyfyAllObservers() throws Exception {
 for(Observers allPositionObserver: allPositionObservers){
     new Thread(
             new Runnable() {
                 @Override
                 public void run() {
                     try {
                         allPositionObserver.update();
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
        return positionreservations.reserve(id,this,true);

    }

    @Override
    public boolean release(Object id) throws Exception {
        return positionreservations.release(id,this);
    }
}
