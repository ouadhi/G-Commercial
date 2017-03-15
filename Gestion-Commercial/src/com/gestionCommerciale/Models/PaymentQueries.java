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

    public void SaveOrUpdateDock(Payment payment) {
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

    public void deleteProduct(Payment payment) {
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

    public List<Payment> paymentsList() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Payment> paymentsList = new ArrayList<>();
        paymentsList = session.createQuery("from Payment").list();

        return paymentsList;
    }

}
