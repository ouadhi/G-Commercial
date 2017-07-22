package com.gestionCommerciale.Models;

import CommercialeControles.Dock.DockCell;
import CommercialeControles.Dock.DockLisController;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.gestionCommerciale.HibernateSchema.Dock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author CHERABRAB
 */
public class DockQueries {
    
    public static boolean archive(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            dock.setDeleted(true);
            session.beginTransaction();
            session.update(dock);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean delete(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(dock);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    
    public static Dock getDockById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Dock d;
        try {
            d = (Dock) session.createQuery("from Dock where id_dock='" + id + "'").uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }
    
    public static Dock getDockByNameAndWilaya(String name, String wilaya) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Dock d;
        try {
            d = (Dock) session.createQuery("from Dock where nom='" + name + "' and wilaya='" + wilaya + "'")
                    .uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }
    
    public static List<Dock> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock where deleted='" + false + "'").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static List<Dock> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static List<Dock> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock where deleted= true ").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static List<Dock> listrechreche(String key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Dock> list = new ArrayList<>();
        try {
            list = session.createQuery("from Dock where( nom like '" + key + "%' OR wilaya like'" + key
                    + "%' )and deleted='" + false + "' ").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static boolean SaveOrUpdate(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(dock);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    
    public static void refreshListe() {
        List<Dock> listDocksDB = DockQueries.list();
        
        List<DockCell> list = new ArrayList<>();
        for (int i = 0; i < listDocksDB.size(); i++) {
            list.add(new DockCell(listDocksDB.get(i)));
        }
        
        ObservableList<DockCell> myObservableList = FXCollections.observableList(list);
        DockLisController.listedockstatic.setItems(myObservableList);
        DockLisController.listedockstatic.setExpanded(true);
        DockLisController.totalstatic.setText(list.size() + "");
    }
    
}
