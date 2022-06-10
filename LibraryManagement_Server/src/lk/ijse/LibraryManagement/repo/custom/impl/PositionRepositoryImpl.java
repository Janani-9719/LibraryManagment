package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Position;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.PositionRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PositionRepositoryImpl extends CrudRepositoryImpl<Position,String> implements PositionRepository {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public boolean removePosition(int posId) throws Exception {
        Query query = session.createQuery("delete from Position where posId=:posId");
        query.setParameter("posId", posId);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Position> searchPositionByRack(int rackNo) throws Exception {
        Query query = session.createQuery("From Position where rackNo=:rackNo");
        query.setParameter("rackNo", rackNo);
        List<Position> positions = query.list();
        if (positions.isEmpty()) {
            return null;
        } else {
            return positions;
        }
    }

    @Override
    public Position searchPositionByThreeId(int rackNo, int columnNo, int rowNo) throws Exception {


        System.out.println(rackNo +"colom"+columnNo+"row"+rowNo);
        return session.createQuery("from Position where rackNo='" + rackNo + "' AND colNo='" + columnNo + "' AND rowNo='" + rowNo + "'", Position.class).list().get(0);
    }

    @Override
    public Position searchPositionByPosID(int posId) throws Exception {

        System.out.println(posId+"dwdwd");
        return session.createQuery("from Position where posId='" + posId + "'", Position.class).list().get(0);
    }
}
