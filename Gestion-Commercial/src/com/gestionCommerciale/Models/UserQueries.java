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

    public void SaveOrUpdate(User user) {
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

    public void delete(User user) {
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

    public List<User> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = FactoryObject.getFactory().openSession();
        List<User> list = new ArrayList<>();
        list = session.createQuery("from User").list();

        return list;
    }
    public User getUser(String nom){
        User user=null;
        List<User> listOfUsers=list();

        for (int i = 0; i < listOfUsers.size(); i++) {
            if (nom.equals(listOfUsers.get(i).getNom())) {
                user=listOfUsers.get(i);
            }
        }
        
        return user;
    }
    public Map<Integer,String> getUsersId(){
        List<User> listOfUsers=list();
        Map<Integer,String> usersIds= new HashMap <>();
        for (int i = 0; i < listOfUsers.size(); i++) {
            usersIds.put(listOfUsers.get(i).getIdUser(), listOfUsers.get(i).getNom());    
        }
        return usersIds ;
    }

}
