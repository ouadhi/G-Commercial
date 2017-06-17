
package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Banque;

public class BanqueQueries {

	public void delete(Banque banque) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {

			session.beginTransaction();
			session.delete(banque);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	public List<Banque> list() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Banque> banqueList = null;
		try {
			banqueList = new ArrayList<>();
			banqueList = session.createQuery("from Banque where deleted='" + false + "'").list();

		} finally {
			session.close();
		}

		return banqueList;
	}

	public List<Banque> listAll() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Banque> banqueList = null;
		try {
			banqueList = new ArrayList<>();
			banqueList = session.createQuery("from Banque").list();

		} finally {
			session.close();
		}

		return banqueList;
	}

	public List<Banque> listArchived() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Banque> banqueList = null;
		try {
			banqueList = new ArrayList<>();
			banqueList = session.createQuery("from Banque Ble where deleted= true").list();

		} finally {
			session.close();
		}

		return banqueList;
	}

	public List<String> listNomBanque() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Banque> banqueList = null;
		List<String> banqueNom = new ArrayList<>();
		try {
			banqueList = new ArrayList<>();
			banqueList = session.createQuery("from Banque").list();
			for (int i = 0; i < banqueList.size(); i++) {
				banqueNom.add(banqueList.get(i).getNom());
			}
		} finally {
			session.close();
		}

		return banqueNom;
	}

	public List<Banque> listRechreche(String key) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Banque> banqueList = null;
		try {
			banqueList = new ArrayList<>();
			banqueList = session
					.createQuery("from Banque where (nom like '" + key + "%' OR compte Like '" + key + "%' ) ").list();
		} finally {
			session.close();
		}

		return banqueList;
	}

	public void SaveOrUpdate(Banque banque) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(banque);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

}
