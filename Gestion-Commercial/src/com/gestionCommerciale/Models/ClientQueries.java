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
    public void SaveOrUpdate(Client client) {
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
    public void delete(Client client) {
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
    public List<Client> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Client> listClients = new ArrayList<>();
        try {
            //Requete HQL pour selectioné tout les client:
            listClients = session.createQuery("from Client").list();
        } finally {
            session.close();
        }
        return listClients;
    }
    public Client getClientByRegistre(String registre) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Client c;
        try {
            //Requete HQL pour selectioné tout les client:
            c = (Client) session.createQuery("from Client where numRegCom='" + registre + "'").uniqueResult();
        } finally {
            session.close();
        }
        return c;
    }
    public Client getClient(String id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Client c;
        try {
            //Requete HQL pour selectioné tout les client:
            c = (Client) session.createQuery("from Client where id='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return c;
    }
}
