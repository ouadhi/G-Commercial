package com.gestionCommerciale.Models;

import CommercialeControles.Client.ClienCell;
import CommercialeControles.Client.ClientListController;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientQueries {
    
    public static boolean archive(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            client.setDeleted(true);
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean delete(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    
    public static Client getClientById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Client d;
        try {
            d = (Client) session.createQuery("from Client where id_client='" + id + "'").uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }
    
    public static Client getClientByNom(String nomPrenom) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Client c = new Client();
        List<Client> listClients = new ArrayList<>();
        try {
            listClients = session.createQuery("from Client where deleted='" + false + "'").list();
            
            for (int i = 0; i < listClients.size(); i++) {
                if ((listClients.get(i).getName() + " " + listClients.get(i).getPrenom()).equals(nomPrenom)) {
                    c = listClients.get(i);
                }
            }
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return c;
    }
    
    public static Client getClientByRegistre(String num) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Client d;
        try {
            d = (Client) session.createQuery("from Client where numRegCom='" + num + "'").uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }
    
    public static List<Client> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client where deleted='" + false + "'  ORDER BY id_client DESC").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static List<Client> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client  ORDER BY id_client DESC").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static List<Client> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client where deleted = true  ORDER BY id_client DESC").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static List<Client> listRechereche(String Key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Client> list = new ArrayList<>();
        try {
            list = session.createQuery("from Client where (name like '%" + Key + "%' OR  prenom like '%" + Key
                    + "%' ) AND deleted='" + false + "'  ORDER BY id_client DESC").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static boolean SaveOrUpdate(Client client) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    
    public static double solde(Client client) {
        
        return totalVersed(client) - totalFactured(client);
    }
    
    public static double totalFactured(Client client) {
        
        List<Facture> list = FactureQueries.getFacturesListByClientId(client.getId());
        double totalefacture = 0;
        for (Facture facture : list) {
            totalefacture += facture.getMontantFinal();
        }
        return totalefacture;
    }
    
    public static double totalVersed(Client client) {
        
        List<Payment> list = PaymentQueries.getPaymentsListByClientId(client.getId());
        double totaleVersed = 0;
        for (Payment payment : list) {
            totaleVersed += payment.getMontant();
        }
        return totaleVersed;
    }
    
    public static void refresheListClient() {
        List<Client> listClientsDB = ClientQueries.list();
        
        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i)));
        }
        
        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        ClientListController.ListeClient.setItems(myObservableList);
        ClientListController.ListeClient.setExpanded(true);
        
        ClientListController.totalstatic.setText(list.size() + "");
        
    }
    
}
