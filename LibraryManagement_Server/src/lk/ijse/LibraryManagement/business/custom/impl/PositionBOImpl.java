package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.PositionBO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.entity.Position;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.PositionRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PositionBOImpl implements PositionBO {

    private PositionRepository positionRepository;

    public PositionBOImpl() {

        positionRepository=(PositionRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.POSITION);
    }

    @Override
    public boolean savePosition(PositionDTO positionDTO) throws Exception {
        Position position=new Position(positionDTO.getDvNo(),positionDTO.getRackNo(),positionDTO.getRowNo(),positionDTO.getColNo());

        try(Session session=HibernateUtil.getSessionFactory().openSession()) {

            positionRepository.setSession(session);
            session.getTransaction().begin();
            positionRepository.save(position);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePosition(PositionDTO positionDTO) throws Exception {
        Position position=new Position(positionDTO.getPosId(),positionDTO.getDvNo(),positionDTO.getRackNo(),positionDTO.getRowNo(),positionDTO.getColNo());
        boolean isUpdate=false;
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            positionRepository.setSession(session);
            if(positionRepository.update(position)){
                isUpdate=true;
                session.getTransaction().commit();
                session.close();
            }else{

                session.getTransaction().rollback();
                session.close();
            }
            return isUpdate;
        }
    }

    @Override
    public boolean removePosition(int posId) throws Exception {



        boolean isRemove=false;
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            positionRepository.setSession(session);
            if(positionRepository.removePosition(posId)){
                isRemove=true;
                session.getTransaction().commit();
                session.close();
            }else{

                session.getTransaction().rollback();
                session.close();
            }
            return isRemove;
        }
    }

    @Override
    public List<PositionDTO> getAll() throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            positionRepository.setSession(session);
            List<Position> list=positionRepository.getAll();
            List<PositionDTO> positions=new ArrayList<>();
            if(list!=null){

                for (Position position : list) {
                    positions.add(new PositionDTO(position.getPosId(),position.getDvNo(),position.getRackNo(),position.getRowNo(),position.getColNo()));
                }
            }

            session.close();
            return positions;
        }
    }

    @Override
    public List<PositionDTO> searchPositionByRack(int rackNo) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            positionRepository.setSession(session);
            List<Position> list=positionRepository.searchPositionByRack(rackNo);
            List<PositionDTO> positions=new ArrayList<>();
            if(list!=null){

                for (Position position : list) {
                    positions.add(new PositionDTO(position.getPosId(),position.getDvNo(),position.getRackNo(),position.getRowNo(),position.getColNo()));
                }
            }

            session.close();
            return positions;
        }
    }

    @Override
    public PositionDTO searchPositionByThreeId(int rackNo, int columnNo, int rowNo) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            positionRepository.setSession(session);
            Position pos=positionRepository.searchPositionByThreeId(rackNo, columnNo, rowNo);

            if(pos!=null){
                session.close();
                return new PositionDTO(pos.getPosId(), pos.getDvNo(), pos.getRackNo(), pos.getRowNo(), pos.getColNo());
            }else{
                session.close();
                return null;
            }
        }
    }

    @Override
    public PositionDTO searchPositionByPosID(int posId) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            positionRepository.setSession(session);
            Position pos=positionRepository.searchPositionByPosID(posId);

            if(pos!=null){
                session.close();
                return new PositionDTO(pos.getPosId(), pos.getDvNo(), pos.getRackNo(), pos.getRowNo(), pos.getColNo());
            }else{
                session.close();
                return null;
            }
        }

    }
}
