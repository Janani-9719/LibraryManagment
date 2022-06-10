package lk.ijse.LibraryManagement.resources;

import lk.ijse.LibraryManagement.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.io.File;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        StandardServiceRegistry registry= new StandardServiceRegistryBuilder().loadProperties("application.properties").build();


        Metadata meta = new MetadataSources(registry)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Position.class)
                .addAnnotatedClass(Damage.class)
                .addAnnotatedClass(BookStudent.class)
                .addAnnotatedClass(BookAuthor.class)
                .addAnnotatedClass(Author.class)
                .buildMetadata();

        return meta.getSessionFactoryBuilder().build();

        }

        public static SessionFactory getSessionFactory(){
        return sessionFactory;
        }
}
