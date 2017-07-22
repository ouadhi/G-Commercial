package com.gestionCommerciale.Models;
import CommercialeControles.Banque.BanqueCell;
import CommercialeControles.Banque.BanqueListeController;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.gestionCommerciale.HibernateSchema.Banque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BanqueQueries {

    public void delete(Banque banque) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {

            session.beginTransaction();
            session.delete(banque);
            session.getTransaction().commit();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Banque> list() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Banque> banqueList = null;
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque where deleted= false").list();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return banqueList;
    }

    public List<Banque> listAll() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Banque> banqueList = null;
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque").list();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return banqueList;
    }

    public List<Banque> listArchived() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Banque> banqueList = null;
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque Ble where deleted= true").list();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return banqueList;
    }

    public List<String> listNomBanque() {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Banque> banqueList = null;
        List<String> banqueNom = new ArrayList<>();
        try {
            banqueList = new ArrayList<>();
            banqueList = session.createQuery("from Banque").list();
            for (int i = 0; i < banqueList.size(); i++) {
                banqueNom.add(banqueList.get(i).getNom());
            }
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return banqueNom;
    }

    public List<Banque> listRechreche(String key) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        List<Banque> banqueList = null;
        try {
            banqueList = new ArrayList<>();
            banqueList = session
                    .createQuery("from Banque where (nom like '" + key + "%' OR compte Like '" + key + "%' ) ").list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return banqueList;
    }

    public String getCompteBancaire(String nomBanque) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        String numBancaire = "";
        try {

            Banque banque = (Banque) session
                    .createQuery("from Banque where nom = '" + nomBanque + "' ").uniqueResult();
            numBancaire = banque.getCompte();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return numBancaire;
    }

    public void SaveOrUpdate(Banque banque) {
        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(banque);
            session.getTransaction().commit();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean archive(Banque banque) {

        SessionsGenerator FactoryObject = new SessionsGenerator();
        Session session = SessionsGenerator.getFactory().openSession();
        try {
            banque.setDeleted(true);
            session.beginTransaction();
            session.update(banque);
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
    
    
    public static void refresh () {
        BanqueQueries querie = new BanqueQueries();
        List<BanqueCell> list = new ArrayList<>();

        for (int i = 0; i < querie.list().size(); i++) {
            list.add(new BanqueCell(querie.list().get(i)));
        }
        ObservableList<BanqueCell> myObservableList = FXCollections.observableList(list);
        BanqueListeController.listebanquestatic.setItems(myObservableList);
        BanqueListeController.listebanquestatic.setExpanded(true);
        BanqueListeController.totalstatic.setText(list.size()+"");

    }

}
