/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.HibernateSchema.Produit;

/**
 *
 * @author Hicham
 */
public class Facture_ProduitQueries {

	public static List<Facture_Produit> list(Facture f) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		List<Facture_Produit> list = new ArrayList<>();
		try {
			list = session.createQuery("from Facture_Produit where id_fact='" + f.getIdFacture() + "'").list();
		} finally {
			session.close();
		}
		return list;
	}

	public void delete(Facture_Produit facture_Produit) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {

			session.beginTransaction();
			session.delete(facture_Produit);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	// get info du Facture( les produits dans la factures + les qtes des
	// produits)
	// public Map<Facture_Produit,Produit> getFactureInfo2(Facture facture){
	// Map<Facture_Produit,Produit> produitEtQte= new HashMap<>();
	// for (int i = 0; i < facture.getQtes().size(); i++) {
	// produitEtQte.put(facture.getQtes().get(i),
	// facture.getQtes().get(i).getProduit());
	// }
	// return produitEtQte;
	// }

	// inserer/Update/Delete
	// when you create the object of the relation you set the corresponding
	// objects
	// of Facture and Produit in the assiciatibe table
	public void insertOrUpdate(Facture_Produit facture_Produit, Facture facture, Produit produit) {
		SessionsGenerator FactoryObject = new SessionsGenerator();
		Session session = SessionsGenerator.getFactory().openSession();
		try {

			session.beginTransaction();
			// set the corresponding objects before saving
			facture_Produit.setProduit(produit);
			facture_Produit.setFacture(facture);
			session.saveOrUpdate(facture_Produit);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

}
