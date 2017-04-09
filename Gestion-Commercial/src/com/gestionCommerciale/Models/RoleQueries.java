/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.Role;
import com.gestionCommerciale.HibernateSchema.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hicham
 */
public class RoleQueries {
    public void SaveOrUpdate(Role role) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(role);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    
    public void delete(Role role) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
    public List<Role> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<Role> list = new ArrayList<>();
        list = session.createQuery("from Role").list();

        return list;
    }
    
    public Role getRole(int  idRole){
        Role role=null;
        List<Role> listOfRoles=list();

        for (int i = 0; i < listOfRoles.size(); i++) {
            if (idRole==listOfRoles.get(i).getIdRole()) {
                role=listOfRoles.get(i);
            }
        }
        return role;
    }
    
}
