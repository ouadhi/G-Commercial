package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Facture;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class FactureQueries {

    public void SaveOrUpdate(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(facture);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(facture);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Facture> list() {
        List<Facture> list = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Facture").list();
        } finally {
            session.close();
        }
        return list;
    }

}
