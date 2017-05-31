package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Dock;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class DockQueries {

    public static boolean SaveOrUpdate(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(dock);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    public static boolean archive(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            dock.setDeleted(true);
            session.beginTransaction();
            session.update(dock);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(dock);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Dock> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock where deleted='"+false+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Dock> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock where deleted='"+true+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Dock> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock").list();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static Dock getDockById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Dock d;
        try {
            d = (Dock) session.createQuery("from Dock where id_dock='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    
    public static Dock getDockByNameAndWilaya(String name, String wilaya) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Dock d;
        try {
            d = (Dock) session.createQuery("from Dock where nom='" +name  + "' and wilaya='"+wilaya+"'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    
    
    public static List<Dock> listrechreche(String key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock where( nom like '"+key+"%' OR wilaya like'"+key+"%' )and deleted='" + false + "' ").list() ;
        } finally {
            session.close();
        }
        return list;
    }
    

}
