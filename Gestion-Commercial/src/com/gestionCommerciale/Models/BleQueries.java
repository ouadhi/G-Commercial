package com.gestionCommerciale.Models;

import CommercialeControles.Ble.BelCell;
import CommercialeControles.Ble.BleListeController;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gestionCommerciale.HibernateSchema.Ble;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BleQueries {

    public static boolean archive(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            ble.setDeleted(true);
            session.beginTransaction();
            session.update(ble);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Ble ble) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(ble);
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static Ble getBleByCode(String code) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Ble d;
        try {
            d = (Ble) session.createQuery("from Ble where code_ble='" + code + "'").uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }

    public static Ble getBleById(int id) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        Ble d;
        try {
            d = (Ble) session.createQuery("from Ble where id_Ble='" + id + "'").uniqueResult();
            session.flush();
        } finally {
            session.close();
        }
        return d;
    }

    public static List<Ble> list() {
        System.out.println("---------began refreshing ble");
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble where deleted='" + false + "'").list();
            System.out.println("---------got list and it contains: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getCodeBle());
            }
            session.flush();
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            System.out.println("---------finished refreshing list ble");
        }
        return list;
    }

    public static List<Ble> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Ble> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble where deleted= true").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Ble> listRecherche(String key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Ble> list = new ArrayList<>();
        try {
            list = session.createQuery("from Ble where  code_ble like '" + key + "%' and deleted='" + false + "'")
                    .list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static boolean SaveOrUpdate(Ble ble) {
        System.out.println("---------began saving transaction ble");
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(ble);
            session.getTransaction().commit();
            session.flush();
            System.out.println("---------finished commiting transaction ble");
            
           
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            System.out.println("---------finished saving transaction ble");
            return true;
        }
    }
    
    
    public static  void refresh ()  {
        List<Ble> listBlesDB = BleQueries.list();
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i)));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        BleListeController.listeBleStatic.setItems(myObservableList);
        BleListeController.listeBleStatic.setExpanded(true);
        BleListeController.totalstatic.setText(list.size()+"");
    }

}
