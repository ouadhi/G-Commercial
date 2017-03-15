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

    public void SaveOrUpdateCamion(Camion camion) {
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

    public List<Camion> camionsList() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Camion> camionsList = new ArrayList<>();
        camionsList = session.createQuery("from Camion").list();

        return camionsList;
    }

}
