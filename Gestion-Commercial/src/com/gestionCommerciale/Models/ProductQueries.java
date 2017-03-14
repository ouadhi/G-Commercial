/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Product;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class ProductQueries {
    //Save or update a product
    public void SaveOrUpdateProduct(Product product) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    //delete a Product
    public void deleteProduct(Product product) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    //products List
    public List<Product> productsList(){
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Product> productsList= new ArrayList<>();
        productsList= session.createQuery("from product").list();
       
        return productsList;
    }
 
}
