package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Produit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class ProduitQueries {

    public void SaveOrUpdateProduit(Produit produit) {
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

    public void deleteProduct(Produit produit) {
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

    public List<Produit> produitsList() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Produit> produitsList = new ArrayList<>();
        produitsList = session.createQuery("from Produit").list();

        return produitsList;
    }

}
