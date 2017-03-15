/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class ChauffeurQueries {
    //method insertion/modification
    public void insererOuModifieChauffeur(Chauffeur chauffeur) {
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
        //Requete HQL pour selectioné tout les client:
        listChauffeurs= session.createQuery("from Chauffeur").list();
       
        return listChauffeurs;
    }
}
