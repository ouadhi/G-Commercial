
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Banque;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;


public class BanqueQueries {

    public void SaveOrUpdate(Banque banque) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(banque);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Banque banque) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(banque);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Banque> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Banque> banqueList = null;
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque").list();
        } finally {
            session.close();
        }

        return banqueList;
    }
    
    
    public List<Banque> listRechreche( String key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Banque> banqueList = null;
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque where (nom like '"+key+"%' OR compte Like '"+key+"%' ) ").list();
        } finally {
            session.close();
        }

        return banqueList;
    }
    
    
    

    

}
