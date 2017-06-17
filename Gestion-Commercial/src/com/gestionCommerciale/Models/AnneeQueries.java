package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Annee;

/**
 *
 * @author CHERABRAB
 */
public class AnneeQueries {

	public static boolean delete(Annee annee) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(annee);
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	// public static boolean archive(Annee annee) {
	// SessionsGenerator FactoryObject = new SessionsGenerator();
	// Session session = FactoryObject.getFactory().openSession();
	// try {
	// annee.setDeleted(true);
	// session.beginTransaction();
	// session.update(annee);
	// if (annee.isSelected()) {
	// Annee a = getAnneeById(maxId());
	// select(maxId());
	// }
	// session.getTransaction().commit();
	// } catch (Exception e) {
	// return false;
	// } finally {
	// session.close();
	// }
	// return true;
	// }

	public static Annee getAnneeById(int idAnnee) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		Annee d;
		try {
			d = (Annee) session.createQuery("from Annee where id_annee='" + idAnnee + "'").uniqueResult();
		} finally {
			session.close();
		}
		return d;
	}

	public static Annee getSelected() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		Annee d;
		try {
			d = (Annee) session.createQuery("from Annee where selected=" + true).uniqueResult();
		} finally {
			session.close();
		}
		return d;
	}

	public static List<Annee> list() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Annee> list = new ArrayList<>();
		try {
			list = session.createQuery("from Annee where deleted='" + false + "'").list();
		} finally {
			session.close();
		}
		return list;
	}

	public static List<Annee> listAll() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Annee> list = new ArrayList<>();
		try {
			list = session.createQuery("from Annee").list();
		} finally {
			session.close();
		}
		return list;
	}

	public static List<Annee> listArchived() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Annee> list = new ArrayList<>();
		try {
			list = session.createQuery("from Annee where deleted='" + true + "'").list();
		} finally {
			session.close();
		}
		return list;
	}

	// public static boolean select(int idAnnee) {
	// SessionsGenerator FactoryObject = new SessionsGenerator();
	// Session session = FactoryObject.getFactory().openSession();
	// try {
	// session.beginTransaction();
	// session.createQuery("update Annee set selected='"+false+"'");
	// // session.createQuery("update Annee set selected='" + true + "'where
	// id_annee='" + idAnnee + "' ");
	// session.getTransaction().commit();
	// } catch (Exception e) {
	// return false;
	// } finally {
	// session.close();
	// }
	// return true;
	// }

	public static int maxId() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		int d;
		try {
			d = (int) session.createQuery("select max(id_annee) from Annee where deleted='" + false + "'")
					.uniqueResult();
		} finally {
			session.close();
		}
		return d;
	}

	public static boolean SaveOrUpdate(Annee annee) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(annee);
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
			return true;
		}
	}
}
