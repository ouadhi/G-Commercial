/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Facture;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class FactureQueries {

    public void SaveOrUpdate(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(facture);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    public void delete(Facture facture) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(facture);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    public List<Facture> list(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Facture> list= new ArrayList<>();
        list= session.createQuery("from Facture").list();       
        return list;
    }

}
