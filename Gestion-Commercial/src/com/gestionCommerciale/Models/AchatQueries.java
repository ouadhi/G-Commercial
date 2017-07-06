package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.HibernateSchema.Facture;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author CHERABRAB
 */
public class AchatQueries {

    public static boolean archive(Achat achat) {

        Session session = SessionsGenerator.getFactory().openSession();
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

        Session session = SessionsGenerator.getFactory().openSession();
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

    public static Achat getAchatByCode(String code) {

        Session session = SessionsGenerator.getFactory().openSession();

        Achat d;

        try {
            d = (Achat) session.createQuery("from Achat where code_achat='" + code + "'").uniqueResult();
        } finally {
            session.close();
        }

        return d;
    }

    public static Achat getAchatById(int id) {

        Session session = SessionsGenerator.getFactory().openSession();
        Achat d;
        try {
            d = (Achat) session.createQuery("from Achat where id_Achat='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public static List<Achat> list() {

        Session session = SessionsGenerator.getFactory().openSession();
        List<Achat> list = new ArrayList<>();
        try {
            // list = session.createQuery("from Achat where deleted='" + false +
            // "' AND id_annee='" + AnneeQueries.getSelected().getIdAnnee() +
            // "'").list();
            list = session.createQuery("from Achat where deleted='" + false + "' AND id_annee='"
                    + AnneeQueries.getSelected().getIdAnnee() + "' ORDER BY id_achat DESC").list();

        } finally {
            session.close();
        }
        return list;
    }
	public static List<Achat> getAchatsByDate(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sd = df.format(d);

		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Achat> list = new ArrayList<>();
		try {
			list = session.createQuery("from Achat where date= '" + sd + "'").list();
		} finally {
			session.close();
		}
		return list;
	}

    public static double totaleAchat() {
        List<Achat> list = new ArrayList<>();
        list = list();
        double totale = 0;
        for (Achat achat : list) {
            totale+=achat.getQuantiteFour();
        }
        return totale;
    }
    public static double totaleAchatByDate(Date d) {
        List<Achat> list = new ArrayList<>();
        list = getAchatsByDate(d);
        double totale = 0;
        for (Achat achat : list) {
            totale+=achat.getQuantiteFour();
        }
        return totale;
    }

    public static List<Achat> listAll() {

        Session session = SessionsGenerator.getFactory().openSession();
        List<Achat> list = new ArrayList<>();
        try {
            list = session.createQuery(
                    "from Achat where id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'ORDER BY id_achat DESC")
                    .list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Achat> listArchived() {
        Session session = SessionsGenerator.getFactory().openSession();
        List<Achat> list = new ArrayList<>();
        try {
            list = session.createQuery("from Achat where deleted=true AND id_annee='"
                    + AnneeQueries.getSelected().getIdAnnee() + "' ORDER BY id_achat DESC").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Achat> listRecherche(String key) {

        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();

        List<Achat> list = new ArrayList<>();

        try {
            list = session.createQuery(
                    "from Achat where dock.nom Like '" + key + "%' and deleted='" + false + "'  ORDER BY id_achat DESC")
                    .list();
        } finally {
            session.close();
        }

        return list;

    }

    @SuppressWarnings("finally")
    public static boolean SaveOrUpdate(Achat achat) {
        Session session = SessionsGenerator.getFactory().openSession();
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
}
