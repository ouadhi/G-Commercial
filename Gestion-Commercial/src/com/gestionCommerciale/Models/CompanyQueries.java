package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Company;
import org.hibernate.Session;


public class CompanyQueries {

    public static void SaveOrUpdate(Company company) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(company);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public static Company getCompany() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Company company = null;
        try {
            company = (Company)  session.createQuery("from Company").uniqueResult();
           
        } finally {
            session.close();
        }
        return company;
    }
}
