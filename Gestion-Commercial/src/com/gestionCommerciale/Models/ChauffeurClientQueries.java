package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.ChauffeurClient;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class ChauffeurClientQueries {
    public void saveOrUpdate(ChauffeurClient chauffeurClient) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(chauffeurClient);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    

    public void delete(ChauffeurClient chauffeurClient ) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(chauffeurClient);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    //la liste des chauffeurs
    public List<ChauffeurClient> list(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<ChauffeurClient> list= new ArrayList<>();
        //Requete HQL pour selectioné tout les client:
        list= session.createQuery("from ChauffeurClient").list();
       
        return list;
    }
}
