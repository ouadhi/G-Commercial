package com.gestionCommerciale.Models;

import com.gestionCommerciale.HibernateSchema.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author CHERABRAB
 */
public class UserQueries {

    public static void SaveOrUpdate(User user) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }


    public static void delete(User user) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public static List<User> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<User> list = new ArrayList<>();
        list = session.createQuery("from User").list();

        return list;
    }

    public static User getUserByName(String nom) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        User d;
        try {
            d = (User) session.createQuery("from User where nom='" + nom + "'").uniqueResult();
        } finally {
            session.close();
        }
        return d;

    }

    public static User getUserByNameAndPassword(String nom,String password) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        User d;
        try {
            d = (User) session.createQuery("from User where nom='" + nom + "' AND password='"+password+"'").uniqueResult();
        } finally {
            session.close();
        }
        return d;
    }

    public static Map<Integer, String> getUsersId() {
        List<User> listOfUsers = list();
        Map<Integer, String> usersIds = new HashMap<>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            usersIds.put(listOfUsers.get(i).getIdUser(), listOfUsers.get(i).getNom());
        }
        return usersIds;
    }

}
