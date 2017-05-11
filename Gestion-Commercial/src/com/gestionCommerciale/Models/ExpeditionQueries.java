package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.Achat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class ExpeditionQueries {

    public void SaveOrUpdate(Achat expedition, Ble ble, Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            expedition.setBle(ble);
            expedition.setChauffeur(chauffeur);
            session.saveOrUpdate(expedition);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    public void delete(Achat expedition) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(expedition);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    public List<Achat> list(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Achat> list= new ArrayList<>();
        list= session.createQuery("from Expedition").list();       
        return list;
    }

}
