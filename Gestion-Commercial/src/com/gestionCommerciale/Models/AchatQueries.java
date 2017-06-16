package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Achat;

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
			List list2 = session.createQuery("from Achat where deleted='" + false + "' AND id_annee='"
					+ AnneeQueries.getSelected().getIdAnnee() + "' ORDER BY id_achat DESC").list();
			list = list2;

		} finally {
			session.close();
		}
		return list;
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

	public static boolean SaveOrUpdate(Achat achat) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
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

	/*
	 * public void SaveOrUpdate(Achat achat) { SessionsGenerator FactoryObject =
	 * new SessionsGenerator(); Session session =
	 * FactoryObject.getFactory().openSession(); try {
	 * 
	 * session.beginTransaction(); session.saveOrUpdate(achat);
	 * session.getTransaction().commit();
	 * 
	 * } finally { session.close(); } }
	 * 
	 * public void delete(Achat expedition) { SessionsGenerator FactoryObject =
	 * new SessionsGenerator(); Session session =
	 * FactoryObject.getFactory().openSession(); try {
	 * 
	 * session.beginTransaction(); session.delete(expedition);
	 * session.getTransaction().commit();
	 * 
	 * } finally { session.close(); } }
	 * 
	 * public List<Achat> list2(){ SessionsGenerator FactoryObject = new
	 * SessionsGenerator(); Session session =
	 * FactoryObject.getFactory().openSession(); List<Achat> list= new
	 * ArrayList<>(); list= session.createQuery("from Achat").list(); return
	 * list; }
	 * 
	 * public List<Achat> listSelectedYear(){ SessionsGenerator FactoryObject =
	 * new SessionsGenerator(); Session session =
	 * FactoryObject.getFactory().openSession(); List<Achat> list= new
	 * ArrayList<>();
	 * System.err.println(AnneeQueries.getSelected().getIdAnnee()+"aaa"); list=
	 * session.createQuery("from Achat where id_annee='"+AnneeQueries.
	 * getSelected().getIdAnnee()+"'").list(); return list; }
	 */
}
