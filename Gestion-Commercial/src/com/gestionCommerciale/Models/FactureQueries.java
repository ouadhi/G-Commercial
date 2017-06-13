package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.HibernateSchema.Produit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class FactureQueries {

    public static boolean SaveOrUpdate(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(facture);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public static boolean insert(Facture facture, Payment payment, List<Facture_Produit> fpsList) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(facture);
            if (payment.getMontant() != 0) {
                session.saveOrUpdate(payment);
            }
            for (Facture_Produit fp : fpsList) {

                Produit p = fp.getProduit();
                p.setQuantite(p.getQuantite() - fp.getQte_fact());
                session.saveOrUpdate(p);

            }
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public static boolean archive(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            facture.setDeleted(true);
            session.beginTransaction();
            session.update(facture);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(facture);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Facture> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Facture> list = new ArrayList<>();
        try {
            list = session.createQuery("from Facture where deleted='" + false + "' AND id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Facture> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Facture> list = new ArrayList<>();
        try {
            list = session.createQuery("from Facture where deleted=true AND id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Facture> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Facture> list = new ArrayList<>();
        try {
            list = session.createQuery("from Facture where id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static Facture getFactureById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Facture d;
        try {
            d = (Facture) session.createQuery("from Facture where id_Facture='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public static List<Facture> getFactureByDates(Date start, Date end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startString = df.format(start);
        String endString = df.format(end);

        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Facture> list = new ArrayList<>();
        try {
            System.err.println(startString+"**"+endString);
            list = session.createQuery("from Facture where date BETWEEN '"+startString+"' AND '"+endString+"'")
                    .list();
        } finally {
            session.close();
        }
        return list;
    }

    //back
    public static List<Facture> getFacturesListByClientId(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Facture> list = new ArrayList<>();
        try {
            list = session.createQuery("from Facture where id_client='" + id + "' AND deleted='" + false + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Facture> listrechreche(String text) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();

        List<Facture> list = new ArrayList<>();
        try {
            //list = session.createQuery("from Facture where deleted='"+false+"' AND id_annee='2017'").list();
            System.out.println(AnneeQueries.getSelected().getIdAnnee() + "-----------");
            //      list = session.createQuery("from Facture where deleted='"+false+"' AND id_annee='"+AnneeQueries.getSelected().getIdAnnee()+"'").list();
            list = session.createQuery("from Facture where deleted='" + false + "' AND (client.name Like '" + text + "%' OR client.prenom Like '" + text + "%'  )").list();

        } finally {
            session.close();
        }
        return list;
    }


    /*
    public void SaveOrUpdate(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(facture);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(facture);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Facture> list() {
        List<Facture> list = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Facture").list();
        } finally {
            session.close();
        }
        return list;
    }

    public Facture getFacture(int id) {
        Facture f = null;
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            f = (Facture) session.createQuery("from Facture where id_facture='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return f;
    }
     */
}
