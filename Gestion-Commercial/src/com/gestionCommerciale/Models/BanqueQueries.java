
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

    public List<String> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Banque> banqueList = null;
        List<String> banqueNom= new ArrayList<>();
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque").list();
            for(int i=0; i<banqueList.size();i++){
                banqueNom.add(banqueList.get(i).getNom());
            }
        } finally {
            session.close();
        }

        return banqueNom;
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
