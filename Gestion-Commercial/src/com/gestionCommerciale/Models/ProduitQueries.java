package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Produit;

/**
 *
 * @author CHERABRAB
 */
public class ProduitQueries {

    public static boolean archive(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            produit.setDeleted(true);
            session.beginTransaction();
            session.update(produit);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(produit);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static Produit getProduitByCode(String code) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Produit d;
        try {
            d = (Produit) session.createQuery("from Produit where code_produit='" + code + "'").uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }

    public static Produit getProduitById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Produit d;
        try {
            d = (Produit) session.createQuery("from Produit where id_produit='" + id + "'").uniqueResult();
            session.flush();
        }finally {
            session.close();
        }
        return d;
    }

    public static List<Produit> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit where deleted='" + false + "'").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Produit> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Produit> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit where deleted= true").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Produit> listRechreche(String nom) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Produit> list = new ArrayList<>();
        try {
            list = session.createQuery("from Produit where (nom like '%" + nom + "%' OR category like '%" + nom
                    + "%') and deleted='" + false + "'").list();
            session.flush();
        } finally {
            session.close();
        }
        return list;
    }

    public static boolean SaveOrUpdate(Produit produit) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(produit);
            session.getTransaction().commit();
            System.out.println("---------saved product: " + produit.getNom());
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            return true;
        }
    }
}
