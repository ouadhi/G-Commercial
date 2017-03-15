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

    public void SaveOrUpdateDock(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(dock);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void deleteProduct(Dock dock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(dock);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Dock> docksList() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Dock> docksList = new ArrayList<>();
        docksList = session.createQuery("from Dock").list();

        return docksList;
    }

}
