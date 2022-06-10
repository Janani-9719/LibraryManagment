package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.AuthorBO;
import lk.ijse.LibraryManagement.dto.AuthorDTO;
import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.AuthorRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AuthorBOImpl implements AuthorBO {

    private AuthorRepository authorRepository;

    public AuthorBOImpl() {
        authorRepository = (AuthorRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.AUTHOR);
    }

    @Override
    public boolean saveAuthor(AuthorDTO authorDTO) throws Exception {
        Author author = new Author(authorDTO.getAuthorName());

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //session.beginTransaction();
            authorRepository.setSession(session);
            session.getTransaction().begin();
            authorRepository.save(author);
            session.getTransaction().commit();

            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }

    }



    @Override
    public List<AuthorDTO> getAll() throws Exception {

        List<AuthorDTO>list1 = new ArrayList<>();
        Session session= HibernateUtil.getSessionFactory().openSession();
        authorRepository.setSession(session);
        session.getTransaction().begin();
        List<Author> list= authorRepository.getAll();
        session.getTransaction().commit();
        for (Author a1: list){
            list1.add(new AuthorDTO(a1.getAuthorId(),a1.getAuthorName()));
        }
        return list1;


        }


    @Override
    public boolean update(AuthorDTO authorDTO) throws Exception {

        Author author=new Author(authorDTO.getAuthorId(),authorDTO.getAuthorName());
        boolean isUpdate=false;

        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            authorRepository.setSession(session);
            if(authorRepository.update(author)){
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
    public AuthorDTO searchAuthor(int aid) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            authorRepository.setSession(session);
            Author author=authorRepository.searchAuthor(aid);
            session.close();
            if(author!=null){
                return new AuthorDTO(author.getAuthorId(),author.getAuthorName());
            }else{
                return null;
            }
        }
    }

    @Override
    public AuthorDTO searchAuthor(String name) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            authorRepository.setSession(session);
            Author author=authorRepository.searchAuthor(name);
            session.close();
            if(author!=null){
                return new AuthorDTO(author.getAuthorId(),author.getAuthorName());
            }else{
                return null;
            }
        }
    }

    @Override
    public boolean removeAuthor(int aid) throws Exception {

        boolean isRemove=false;

        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            authorRepository.setSession(session);
            if(authorRepository.removeAuthor(aid)){
                session.getTransaction().commit();
                isRemove=true;
                session.close();
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isRemove;
        }
    }

    }

