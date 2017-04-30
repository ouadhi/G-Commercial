package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Dock;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class DockQueries {

    public void SaveOrUpdate(Dock dock) {
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

    public void delete(Dock dock) {
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

    public List<Dock> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Dock> docksList = new ArrayList<>();
        docksList = session.createQuery("from Dock").list();

        return docksList;
    }
    public Dock getDock(String id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Dock d;
        try {
            //Requete HQL pour selectioné tout les client:
            d = (Dock) session.createQuery("from Dock where id='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    public Dock getDockByNameAndWilaya(String name, String wilaya) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Dock d;
        try {
            //Requete HQL pour selectioné tout les client:
            d = (Dock) session.createQuery("from Dock where nom='" +name  + "' and wilaya='"+wilaya+"'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

}
