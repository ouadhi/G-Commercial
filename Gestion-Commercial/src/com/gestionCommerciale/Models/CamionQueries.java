package com.gestionCommerciale.Models;

import CommercialeControles.Camion.CamionCell;
import CommercialeControles.Camion.CamionViewController;
import UIControle.Methode;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Camion;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CamionQueries {

    public static boolean archive(Camion camion) {
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            camion.setDeleted(true);
            session.beginTransaction();
            session.update(camion);
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

    public static boolean delete(Camion camion) {
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(camion);
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

    public static Camion getCamionByCode(String codeCamion) {
        Session session = SessionsGenerator.getFactory().openSession();
        Camion d;
        try {
            d = (Camion) session.createQuery("from Camion where codeCamion='" + codeCamion + "'").uniqueResult();
            session.flush();
            session.clear();
        } finally {
            session.close();
        }
        return d;
    }

    public static Camion getCamionById(int id) {
        Session session = SessionsGenerator.getFactory().openSession();
        Camion d;
        try {
            d = (Camion) session.createQuery("from Camion where id_camion='" + id + "'").uniqueResult();
            session.flush();
            session.clear();
        } finally {
            session.close();
        }
        return d;
    }

    public static Camion getCamionByMatricule(String matricule) {
        Session session = SessionsGenerator.getFactory().openSession();
        Camion d;
        try {
            d = (Camion) session.createQuery("from Camion where matricule='" + matricule + "'").uniqueResult();
            session.flush();
            session.clear();
        } finally {
            session.close();
        }
        return d;
    }

    public List<Camion> list() {

        Session session = SessionsGenerator.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            //list = session.createQuery("from Camion where deleted= false").list();
            list = session.createSQLQuery("select * from Camion c where c.deleted= false")
                    .addEntity(Camion.class).list();
            session.flush();
            session.clear();
            for (int i = 0; i < list.size(); i++) {
                System.out.println("-------------- list camion: " + list.get(i).getCodeCamion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listInterne() {
        Session session = SessionsGenerator.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion where  type='INTERNE' AND deleted= false").list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listExterne() {
        Session session = SessionsGenerator.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion where type='EXTERNE' AND deleted= false ").list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listAll() {
        Session session = SessionsGenerator.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion").list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listArchived() {
        Session session = SessionsGenerator.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion where deleted = true ").list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Camion> listRechreche(String key) {
        Session session = SessionsGenerator.getFactory().openSession();
        List<Camion> list = new ArrayList<>();
        try {
            list = session.createQuery("from Camion where (codeCamion like '" + key + "%'OR  matricule like '" + key
                    + "%'  OR marque like '" + key + "%' ) and deleted= false ").list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static boolean SaveOrUpdate(Camion camion) {
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(camion);
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
    
    public static void refresh () {
       
        List<Camion> listCamionsDB = new CamionQueries().list() ; 
        List<CamionCell> list = new ArrayList<>();
        for (int i = 0; i < listCamionsDB.size(); i++) {
            list.add(new CamionCell(listCamionsDB.get(i)));

        }

        ObservableList<CamionCell> myObservableList = FXCollections.observableList(list);
        CamionViewController.listeViewStatic.setItems(myObservableList);
        
        CamionViewController.totalstatic.setText(list.size()+"");
    }

}
