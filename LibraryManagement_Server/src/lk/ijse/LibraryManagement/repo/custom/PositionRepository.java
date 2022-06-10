package lk.ijse.LibraryManagement.repo.custom;

import lk.ijse.LibraryManagement.entity.Position;
import lk.ijse.LibraryManagement.repo.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, String> {

    public boolean removePosition(int posId)throws Exception;

    public List<Position> searchPositionByRack(int rackNo)throws Exception;

    public Position searchPositionByThreeId(int rackNo, int columnNo, int rowNo)throws Exception;

    public Position searchPositionByPosID(int posId)throws Exception;
}
