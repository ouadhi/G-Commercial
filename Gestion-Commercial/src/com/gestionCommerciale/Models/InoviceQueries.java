/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Inovice;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class InoviceQueries {

    public void SaveOrUpdateInovice(Inovice inovice) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(inovice);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    public void deleteInovice(Inovice inovice) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(inovice);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    public List<Inovice> inovicesList(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Inovice> inovicesList= new ArrayList<>();
        inovicesList= session.createQuery("from Inovice").list();       
        return inovicesList;
    }

}
