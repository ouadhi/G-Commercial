package com.gestionCommerciale.Models;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Company;

public class CompanyQueries {

	public static Company getCompany() {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		Company company = null;
		try {
			company = (Company) session.createQuery("from Company").uniqueResult();

		} finally {
			session.close();
		}
		return company;
	}

	public static void SaveOrUpdate(Company company) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(company);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
}
