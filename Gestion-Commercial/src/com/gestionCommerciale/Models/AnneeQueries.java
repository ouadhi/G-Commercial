package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Annee;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author CHERABRAB
 */
public class AnneeQueries {

    public static void SaveOrUpdate(Annee annee) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(annee);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Annee annee) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(annee);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Annee> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Annee> list = new ArrayList<>();
        list = session.createQuery("from Annee").list();

        return list;
    }

    public Annee getAnnee(String id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Annee d;
        try {
            //Requete HQL pour selectioné tout les client:
            d = (Annee) session.createQuery("from Annee where id_annee='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public void select(int oldYear, int newYear) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            //Requete HQL pour selectioné tout les client:
            session.createQuery("update Annee where id_annee='" + oldYear + "' set is_selected='" + false + "'").uniqueResult();
            session.createQuery("update Annee where id_annee='" + newYear + "' set is_selected='" + true + "'").uniqueResult();
        } finally {
            session.close();
        }

    }

    public static Annee getSelected() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Annee d;
        try {
            //checkSelected();
            //Requete HQL pour selectioné tout les client:
            d = (Annee) session.createQuery("from Annee where selected=" + true).uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public static void checkSelected() {

        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            List<Annee> list = new ArrayList<>();
            list = session.createQuery("from Annee where selected=" + true).list();
            if (list.size() > 1) {
                Criteria criteria = session.createCriteria(Annee.class).setProjection(Projections.max("idAnnee"));
                int maxAnnee = (Integer) criteria.uniqueResult();
                System.out.println("------" + maxAnnee);
                session.createQuery("update Annee set selected='" + true + "' where id_annee='" + maxAnnee + "'").uniqueResult();

            }
        } finally {
            session.close();
        }

    }

}
