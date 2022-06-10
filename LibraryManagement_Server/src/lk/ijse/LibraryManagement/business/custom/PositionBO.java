package lk.ijse.LibraryManagement.business.custom;

import lk.ijse.LibraryManagement.business.SuperBO;
import lk.ijse.LibraryManagement.dto.PositionDTO;

import java.util.List;

public interface PositionBO extends SuperBO {

    public boolean savePosition(PositionDTO positionDTO)throws Exception;

    public boolean updatePosition(PositionDTO positionDTO)throws Exception;

    public boolean removePosition(int posId)throws Exception;

    public List<PositionDTO> getAll()throws Exception;

    public List<PositionDTO> searchPositionByRack(int rackNo)throws Exception;

    public PositionDTO searchPositionByThreeId(int rackNo, int columnNo, int rowNo)throws Exception;

    public PositionDTO searchPositionByPosID(int posId)throws Exception;

}
