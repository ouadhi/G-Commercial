/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class ChauffeurQueries {

    public static boolean SaveOrUpdate(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(chauffeur);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public static boolean archive(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            chauffeur.setDeleted(true);
            session.beginTransaction();
            session.update(chauffeur);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(chauffeur);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Chauffeur> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where deleted='" + false + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur where deleted='" + true + "'").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Chauffeur> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Chauffeur> list = new ArrayList<>();
        try {
            list = session.createQuery("from Chauffeur").list();
        } finally {
            session.close();
        }
        return list;
    }

    public static Chauffeur getChauffeurById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Chauffeur d;
        try {
            d = (Chauffeur) session.createQuery("from Chauffeur where IdChauffeur='" + id + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public static Chauffeur getChauffeurByNomPrenom(String nom, String prenom) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        Chauffeur d;
        try {
            d = (Chauffeur) session.createQuery("from Chauffeur where nomChauffeur='" + nom + "' AND prenomChauffeur='" + prenom + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    /*
    //method insertion/modification
    public void saveOrUpdate(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            //save pour ajouté, update pour modifie
            session.saveOrUpdate(chauffeur);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    //supprimer un chauffeur
    public void deleteChauffeur(Chauffeur chauffeur) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(chauffeur);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    //la liste des chauffeurs
    public List<Chauffeur> chauffeursList(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Chauffeur> listChauffeurs= new ArrayList<>();
        //Requete HQL pour selectioné tout les chauffeur:
        try{
        listChauffeurs= session.createQuery("from Chauffeur").list();
        }finally{
            session.close();
        }
       
        return listChauffeurs;
    }
    public Chauffeur getChauffeur(String nomPrenom){
        Chauffeur chauffeur=null;
        List<Chauffeur> listOfchauffeurs=chauffeursList();

        for (int i = 0; i < listOfchauffeurs.size(); i++) {
            if (nomPrenom.equals(listOfchauffeurs.get(i).getPrenomChauffeur()+" "+listOfchauffeurs.get(i).getNomChauffeur())) {
                chauffeur=listOfchauffeurs.get(i);
            }
        }
        return chauffeur;
    }
    
    public Map<Integer,String> getUsersId(){
        List<Chauffeur> listOfChauffeurs= chauffeursList();
        Map<Integer,String> chauffeursIds= new HashMap <>();
        for (int i = 0; i < listOfChauffeurs.size(); i++) {
            chauffeursIds.put(listOfChauffeurs.get(i).getId(), listOfChauffeurs.get(i).getNomChauffeur());    
        }
        return chauffeursIds ;
    }

     */
}
