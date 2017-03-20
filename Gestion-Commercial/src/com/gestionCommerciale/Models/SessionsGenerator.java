/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Hicham
 */
public class SessionsGenerator {
    //class singleton pour generer les sessions,

    static SessionFactory factory = null;
    static int singletonCounter = 0;

    public SessionsGenerator() {

        if (singletonCounter == 0) {
            //create singleton factory object
            Configuration configuration = new Configuration()
                    .configure("/resources/hibernate.cfg.xml")
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Chauffeur.class)
                    .addAnnotatedClass(Camion.class)
                    .addAnnotatedClass(Expedition.class)
                    .addAnnotatedClass(Dock.class)
                    .addAnnotatedClass(Facture.class)
                    .addAnnotatedClass(Facture_Produit.class)
                    .addAnnotatedClass(Produit.class)
                    .addAnnotatedClass(Ble.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            factory = configuration.buildSessionFactory(builder.build());
            singletonCounter++;
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}