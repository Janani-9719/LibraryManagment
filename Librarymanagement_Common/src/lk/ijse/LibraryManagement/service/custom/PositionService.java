package lk.ijse.LibraryManagement.service.custom;

import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.observer.Subject;
import lk.ijse.LibraryManagement.reservation.Reservation;
import lk.ijse.LibraryManagement.service.SuperService;

import java.util.List;

public interface PositionService extends SuperService,Subject,Reservation {

    public boolean savePosition(PositionDTO positionDTO)throws Exception;

    public boolean updatePosition(PositionDTO positionDTO)throws Exception;

    public boolean removePosition(int posId)throws Exception;

    public List<PositionDTO> getAll()throws Exception;

    public List<PositionDTO> searchPositionByRack(int rackNo)throws Exception;

    public PositionDTO searchPositionByThreeId(int rackNo, int columnNo, int rowNo)throws Exception;

    public PositionDTO searchPositionByPosID(int posId)throws Exception;

}
