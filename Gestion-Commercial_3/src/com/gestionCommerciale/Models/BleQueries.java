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

    public void SaveOrUpdate(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(ble);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(ble);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Ble> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        list = session.createQuery("from Ble").list();

        return list;
    }
    public Ble getBle(String id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Ble d;
        try {
            //Requete HQL pour selectioné tout les client:
            d = (Ble) session.createQuery("from Ble where id='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

}
