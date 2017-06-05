package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Payment;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class PaymentQueries {

    public static boolean SaveOrUpdate(Payment payment) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public static boolean archive(Payment payment) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            payment.setDeleted(true);
            session.beginTransaction();
            session.update(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Payment payment) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Payment> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> list = new ArrayList<>();
        try {
            //list = session.createQuery("from Payment where deleted='"+false+"' AND id_annee='2017'").list();
            System.out.println(AnneeQueries.getSelected().getIdAnnee() + "-----------");
            list = session.createQuery("from Payment where deleted='" + false + "' AND id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Payment> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> list = new ArrayList<>();
        try {
            list = session.createQuery("from Payment where deleted= true AND id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Payment> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> list = new ArrayList<>();
        try {
            list = session.createQuery("from Payment" + "' AND id_annee='" + AnneeQueries.getSelected().getIdAnnee() + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static Payment getPaymentById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Payment d;
        try {
            d = (Payment) session.createQuery("from Payment where id_Payment='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    public static List<Payment> getPaymentsListByClientId(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> list = new ArrayList<>();
        try {
            list= session.createQuery("from Payment where id_client='" + id + "' AND deleted='"+false+"'").list();
        } finally {
            session.close();
        }
        return list;
    }

    /*
    public void SaveOrUpdate(Payment payment) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(payment);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public void delete(Payment payment) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(payment);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public List<Payment> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> paymentsList = new ArrayList<>();
        paymentsList = session.createQuery("from Payment").list();

        return paymentsList;
    }
    
    public List<Payment> listByPayment(int idFact) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> paymentsList = new ArrayList<>();
        paymentsList = session.createQuery("from Payment where id_fact='"+idFact+"'").list();

        return paymentsList;
    }
     */
}
