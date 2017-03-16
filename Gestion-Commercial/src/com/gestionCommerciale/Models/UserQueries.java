package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class UserQueries {

    public void SaveOrUpdate(User user) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(User user) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<User> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<User> list = new ArrayList<>();
        list = session.createQuery("from User").list();

        return list;
    }

}
