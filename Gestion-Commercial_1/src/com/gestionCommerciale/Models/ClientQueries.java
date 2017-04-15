/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Client;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class ClientQueries {

    //method insertion/modification
    public void insererOuModifieClient(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            //save pour ajouté, update pour modifie
            session.saveOrUpdate(client);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    //supprimer un client
    public void deleteClient(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    //la liste des clients
    public List<Client> clientsList(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Client> listClients= new ArrayList<>();
        //Requete HQL pour selectioné tout les client:
        listClients= session.createQuery("from Client").list();
       
        return listClients;
    }

}
