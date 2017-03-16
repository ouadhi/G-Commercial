package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.ChauffeurDock;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class ChauffeurDockQueries {
    public void saveOrUpdate(ChauffeurDock chauffeurDock) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(chauffeurDock);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    

    public void delete(ChauffeurDock chauffeurDock ) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(chauffeurDock);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    //la liste des chauffeurs
    public List<ChauffeurDock> list(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<ChauffeurDock> list= new ArrayList<>();
        //Requete HQL pour selectioné tout les Dock:
        list= session.createQuery("from ChauffeurDock").list();
       
        return list;
    }
}
