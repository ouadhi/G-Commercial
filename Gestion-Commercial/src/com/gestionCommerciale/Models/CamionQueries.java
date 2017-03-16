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

}
