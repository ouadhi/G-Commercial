package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class CamionQueries {
        public static boolean SaveOrUpdate(Camion camion) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(camion);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public static boolean archive(Camion camion) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            camion.setDeleted(true);
            session.beginTransaction();
            session.update(camion);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Camion camion) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(camion);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Camion> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion where deleted='" + false + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion where deleted='" + true + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static Camion getCamionById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Camion d;
        try {
            d = (Camion) session.createQuery("from Camion where id_camion='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public static Camion getCamionByCode(String codeCamion) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Camion d;
        try {
            d = (Camion) session.createQuery("from Camion where nomCamion='" + codeCamion + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    public static Camion getCamionByMatricule(String matricule) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Camion d;
        try {
            d = (Camion) session.createQuery("from Camion where matricule='" + matricule + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }


    
    
/*
    public void SaveOrUpdate(Camion camion) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(camion);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Camion camion) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(camion);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Camion> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Camion> camionsList = new ArrayList<>();
        camionsList = session.createQuery("from Camion").list();

        return camionsList;
    }
     public Camion getCamion(String matricule){
        Camion camion=null;
        List<Camion> listOfCamion=list();

        for (int i = 0; i < listOfCamion.size(); i++) {
            if (matricule.equals(listOfCamion.get(i).getMatricule())) {
                camion=listOfCamion.get(i);
            }
        }
        return camion;
    }
*/
}
