/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.HibernateSchema.Annee;
import com.gestionCommerciale.HibernateSchema.Banque;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Company;
import com.gestionCommerciale.HibernateSchema.Dock;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.HibernateSchema.Role;
import com.gestionCommerciale.HibernateSchema.User;

/**
 *
 * @author Hicham
 */
public class SessionsGenerator {
    // class singleton pour generer les sessions,

    static SessionFactory factory = null;
    static int singletonCounter = 0;

    public static SessionFactory getFactory() {
        return factory;
    }

    public SessionsGenerator() {

        if (singletonCounter == 0) {
            // create singleton factory object
            Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml")
            //Configuration configuration = new Configuration().configure("/resources/hibernatep.cfg.xml")
                    .addAnnotatedClass(Client.class).addAnnotatedClass(Chauffeur.class).addAnnotatedClass(Camion.class)
                    .addAnnotatedClass(Achat.class).addAnnotatedClass(Dock.class).addAnnotatedClass(Facture.class)
                    .addAnnotatedClass(Facture_Produit.class).addAnnotatedClass(Produit.class)
                    .addAnnotatedClass(Ble.class).addAnnotatedClass(Payment.class).addAnnotatedClass(User.class)
                    .addAnnotatedClass(Role.class).addAnnotatedClass(Banque.class).addAnnotatedClass(Annee.class)
                    .addAnnotatedClass(Company.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            factory = configuration.buildSessionFactory(builder.build());
            singletonCounter++;
        }
    }
}
