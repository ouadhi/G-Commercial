
package com.gestionCommerciale.Models;
import com.gestionCommerciale.HibernateSchema.Client;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class ClientQueries {
        public static boolean SaveOrUpdate(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    public static boolean archive(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            client.setDeleted(true);
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Client> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client where deleted='"+false+"'").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Client> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client where deleted = true ").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static List<Client> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client").list();
        } finally {
            session.close();
        }
        return list;
    }
    public static Client getClientById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Client d;
        try {
            d = (Client) session.createQuery("from Client where id_client='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    public static Client getClientByRegistre(String num) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Client d;
        try {
            d = (Client) session.createQuery("from Client where numRegCom='" +num+"'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }
    
     public static List<Client> listRechereche(String Key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client where (name like '%"+Key+"%' OR  prenom like '%"+Key+"%' ) AND deleted='"+false+"'").list();
        } finally {
            session.close();
        }
        return list;
    }


    
    
    /*
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
*/
}
