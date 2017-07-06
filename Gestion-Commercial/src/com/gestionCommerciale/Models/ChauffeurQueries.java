
package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Chauffeur;

/**
 *
 * @author Hicham
 */
public class ChauffeurQueries {

	public static boolean archive(Chauffeur chauffeur) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {
			chauffeur.setDeleted(true);
			session.beginTransaction();
			session.update(chauffeur);
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public static boolean delete(Chauffeur chauffeur) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(chauffeur);
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public static Chauffeur getChauffeurById(int id) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		Chauffeur d;
		try {
			d = (Chauffeur) session.createQuery("from Chauffeur where IdChauffeur='" + id + "'").uniqueResult();
		} finally {
			session.close();
		}
		return d;
	}

	public static Chauffeur getChauffeurByNomPrenom(String nom, String prenom) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		Chauffeur d;
		try {
			d = (Chauffeur) session
					.createQuery("from Chauffeur where nomChauffeur='" + nom + "' AND prenomChauffeur='" + prenom + "'")
					.uniqueResult();
		} finally {
			session.close();
		}
		return d;
	}

	public static List<Chauffeur> list() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Chauffeur> list = new ArrayList<>();
		try {
			list = session.createQuery("from Chauffeur where deleted='" + false + "'").list();
		} finally {
			session.close();
		}
		return list;
	}
	public static List<Chauffeur> listInterne() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Chauffeur> list = new ArrayList<>();
		try {
			list = session.createQuery("from Chauffeur where type='INTERNE' AND deleted='" + false + "'").list();
		} finally {
			session.close();
		}
		return list;
	}
	public static List<Chauffeur> listExterne() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Chauffeur> list = new ArrayList<>();
		try {
			list = session.createQuery("from Chauffeur where type='EXTERNE' AND deleted='" + false + "'").list();
		} finally {
			session.close();
		}
		return list;
	}

	public static List<Chauffeur> listAll() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Chauffeur> list = new ArrayList<>();
		try {
			list = session.createQuery("from Chauffeur").list();
		} finally {
			session.close();
		}
		return list;
	}

	public static List<Chauffeur> listArchived() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Chauffeur> list = new ArrayList<>();
		try {
			list = session.createQuery("from Chauffeur where deleted = true ").list();

		} finally {
			session.close();
		}
		return list;
	}

	public static List<Chauffeur> listRecherche(String key) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Chauffeur> list = new ArrayList<>();
		try {
			list = session.createQuery("from Chauffeur where( nomChauffeur Like'%" + key
					+ "%' OR prenomChauffeur Like '%" + key + "%' OR type Like '%" + key + "%'  )and deleted='" + false + "' ").list();
		} finally {
			session.close();
		}
		return list;
	}

	public static boolean SaveOrUpdate(Chauffeur chauffeur) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(chauffeur);
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
			return true;
		}
	}

}
