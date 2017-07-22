package com.gestionCommerciale.Models;

import CommercialeControles.Chauffeur.ChauffeurCell;
import CommercialeControles.Chauffeur.ChauffeurController;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hicham
 */
public class ChauffeurQueries {

    public static boolean archive(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            chauffeur.setDeleted(true);
            session.beginTransaction();
            session.update(chauffeur);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(chauffeur);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static Chauffeur getChauffeurById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Chauffeur d;
        try {
            d = (Chauffeur) session.createQuery("from Chauffeur where IdChauffeur='" + id + "'").uniqueResult();
            session.flush();
            session.clear();
        } finally {
            session.close();
        }
        return d;
    }

    public static Chauffeur getChauffeurByNomPrenom(String nom, String prenom) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Chauffeur d;
        try {
            d = (Chauffeur) session
                    .createQuery("from Chauffeur where nomChauffeur='" + nom + "' AND prenomChauffeur='" + prenom + "'")
                    .uniqueResult();
            session.flush();
            session.clear();
        } finally {
            session.close();
        }
        return d;
    }

    public static List<Chauffeur> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where deleted='" + false + "'").list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listInterne() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where type='INTERNE' AND deleted='" + false + "'").list();
            session.flush();
            session.clear();
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listExterne() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where type='EXTERNE' AND deleted='" + false + "'").list();
            session.flush();
            session.clear();
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur").list();
            session.flush();
            session.clear();
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where deleted = true ").list();
            session.flush();
            session.clear();
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listRecherche(String key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where( nomChauffeur Like'%" + key
                    + "%' OR prenomChauffeur Like '%" + key + "%' OR type Like '%" + key + "%'  )and deleted='" + false + "' ").list();
            session.flush();
            session.clear();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static boolean SaveOrUpdate(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(chauffeur);
            session.flush();
            session.clear();
            session.getTransaction().commit();
           
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            return true;
        }
    }
    
    public static void refreshListeView( ){
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.list();
        List<ChauffeurCell> list = new ArrayList<>();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurCell(listChauffeursDB.get(i)));

        }

        ObservableList<ChauffeurCell> myObservableList = FXCollections.observableList(list);
        ChauffeurController.listeChauffeur.setItems(myObservableList);
        ChauffeurController.listeChauffeur.setExpanded(true);

        ChauffeurController.totalstatic.setText(list.size()+"");
    }

}
