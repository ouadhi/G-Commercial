/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionCommerciale.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Role;

/**
 *
 * @author Hicham
 */
public class RoleQueries {

    public void delete(Role role) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Role getRole(int idRole) {
        Role role = null;
        List<Role> listOfRoles = list();

        for (int i = 0; i < listOfRoles.size(); i++) {
            if (idRole == listOfRoles.get(i).getIdRole()) {
                role = listOfRoles.get(i);
            }
        }
        return role;
    }

    public List<Role> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Role> list = null;
        try {

            list = new ArrayList<>();
            list = session.createQuery("from Role").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public void SaveOrUpdate(Role role) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(role);
            session.getTransaction().commit();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
