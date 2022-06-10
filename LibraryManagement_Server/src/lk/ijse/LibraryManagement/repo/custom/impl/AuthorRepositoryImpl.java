package lk.ijse.LibraryManagement.repo.custom.impl;


import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.AuthorRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorRepositoryImpl extends CrudRepositoryImpl<Author,String> implements AuthorRepository {

   private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }
    @Override


    public Author searchAuthor(int aid) throws Exception {

        Query query=session.createQuery("From Author where authorId=:authorId");
        query.setParameter("authorId", aid);
        List<Author> authors=query.list();
        if(authors.isEmpty()){
            return null;
        }else{
            return authors.get(0);
        }
    }

    @Override
    public Author searchAuthor(String name) throws Exception {
        Query query=session.createQuery("From Author where authorName=:authorName");
        query.setParameter("authorName", name);
        List<Author> authors=query.list();
        if(authors.isEmpty()){
            return null;
        }else{
            return authors.get(0);
        }
    }

    @Override
    public boolean removeAuthor(int aid) throws Exception {

        Query query=session.createQuery("delete from Author where authorId=:authorId");
        query.setParameter("authorId", aid);
        return query.executeUpdate()>0;
    }


}
