package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Achat;
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
    public static boolean SaveOrUpdate(Achat achat) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(achat);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    public static boolean archive(Achat achat) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            achat.setDeleted(true);
            session.beginTransaction();
            session.update(achat);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Achat achat) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(achat);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Achat> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Achat> list = new ArrayList<>();
        try {
            //list = session.createQuery("from Achat where deleted='"+false+"' AND id_annee='2017'").list();
            System.out.println(AnneeQueries.getSelected().getIdAnnee()+"-----------");
            list = session.createQuery("from Achat where deleted='"+false+"' AND id_annee='"+AnneeQueries.getSelected().getIdAnnee()+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Achat> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Achat> list = new ArrayList<>();
        try {
            list = session.createQuery("from Achat where deleted='"+true+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Achat> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Achat> list = new ArrayList<>();
        try {
            list = session.createQuery("from Achat").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static Achat getAchatById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Achat d;
        try {
            d = (Achat) session.createQuery("from Achat where id_Achat='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    public static Achat getAchatByCode(String code) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Achat d;
        try {
            d = (Achat) session.createQuery("from Achat where code_achat='" +code+"'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }


    
    /*
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
    */
}
