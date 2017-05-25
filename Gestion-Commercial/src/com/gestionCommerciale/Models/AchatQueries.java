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
public class AchatQueries {

    public void SaveOrUpdate(Achat achat) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(achat);
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
    
    public List<Achat> list2(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Achat> list= new ArrayList<>();
        list= session.createQuery("from Achat").list();       
        return list;
    }
    
    public List<Achat> listSelectedYear(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Achat> list= new ArrayList<>();
        System.err.println(AnneeQueries.getSelected().getIdAnnee()+"aaa");
        list= session.createQuery("from Achat where id_annee='"+AnneeQueries.getSelected().getIdAnnee()+"'").list();       
        return list;
    }
}
