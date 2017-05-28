package com.gestionCommerciale.Models;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.HibernateSchema.Produit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
/**
 *
 * @author CHERABRAB
 */
public class ProduitQueries {
    
        public static boolean SaveOrUpdate(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(produit);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public static boolean archive(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            produit.setDeleted(true);
            session.beginTransaction();
            session.update(produit);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(produit);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Produit> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit where deleted='" + false + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Produit> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit where deleted='" + true + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Produit> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit").list();
        } finally {
            session.close();
        }
        return list;
    }
    
     public static List<Produit> listRechreche(String nom) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit where (nom like '%"+nom+"%' OR category like '%"+nom+"%') and deleted='" + false + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static Produit getProduitById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Produit d;
        try {
            d = (Produit) session.createQuery("from Produit where id_produit='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    
    /*
    public void SaveOrUpdate(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(produit);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    public void delete(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(produit);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    public List<Produit> list() {
        List<Produit> produitsList = new ArrayList<>();
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            
            produitsList = session.createQuery("from Produit").list();
        } finally {
            session.close();
        }
        return produitsList;
    }
    public Produit getProduit(String id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Produit d;
        try {
            //Requete HQL pour selectioné tout les client:
            d = (Produit) session.createQuery("from Produit where id='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
*/
}
