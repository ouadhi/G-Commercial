package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Ble;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class BleQueries {
    public static boolean SaveOrUpdate(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(ble);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    public static boolean archive(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            ble.setDeleted(true);
            session.beginTransaction();
            session.update(ble);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(ble);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Ble> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble where deleted='"+false+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Ble> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble where deleted='"+true+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Ble> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static Ble getBleById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Ble d;
        try {
            d = (Ble) session.createQuery("from Ble where id_Ble='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    public static Ble getBleByCode(String code) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Ble d;
        try {
            d = (Ble) session.createQuery("from Ble where code_ble='" +code+"'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }


}
