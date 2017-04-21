package gestion.commercial;

import com.gestionCommerciale.HibernateSchema.*;
import com.gestionCommerciale.Models.*;
import java.util.Date;
import javafx.application.Application;

/**
 *
 * @author CHERABRAB
 */
public class test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testProduitFact();
    }

    public static void testProduitFact() {
        ProduitQueries pQueries = new ProduitQueries();
        Produit po = new Produit("p1","", 22, 11.11f);
        FactureQueries fQueries = new FactureQueries();
        Date d = new Date();
        Facture fo = new Facture(d, 111.00, 111.00, 111.00);
        pQueries.SaveOrUpdate(po);
        fQueries.SaveOrUpdate(fo);
        Produit p = pQueries.list().get(0);
        Facture f = fQueries.list().get(0);
        //p.factures.add(f);
        //f.produits.add(p);
        pQueries.SaveOrUpdate(p);
        fQueries.SaveOrUpdate(f);
    }

    public static void testProduit() {
        ProduitQueries pQueries = new ProduitQueries();
        Produit po = new Produit("p1","", 22, 11.11f);
        pQueries.SaveOrUpdate(po);
        Produit po2 = new Produit("p2","", 22, 22.22f);
        pQueries.SaveOrUpdate(po2);
        for (int i = 0; i < pQueries.list().size(); i++) {
            System.out.println(pQueries.list().get(i).getNom() + ","
                    + pQueries.list().get(i).getQuantite() + ","
                    + pQueries.list().get(i).getPrix());
        }
    }

    public static void testDock() {
        DockQueries queries = new DockQueries();
        Dock o = new Dock("d1", "w1", 22.2f, 22.22f);
        queries.SaveOrUpdate(o);
        Dock o2 = new Dock("d2", "w2", 33.3f, 33.22f);
        queries.SaveOrUpdate(o2);
        for (int i = 0; i < queries.list().size(); i++) {
            System.out.println(queries.list().get(i).getNom() + ","
                    + queries.list().get(i).getWilaya() + ","
                    + queries.list().get(i).getPrixUnitTrans() + ","
                    + queries.list().get(i).getDistance());
            Dock o3 = queries.list().get(i);
            o3.setNom(o3.getNom() + " u");
            queries.SaveOrUpdate(o3);

        }
    }

    public static void testCamion() {
       
      
    }

    public static void testPayment() {
        PaymentQueries queries = new PaymentQueries();
        Date d = new Date();
        Payment o1 = new Payment("t1", 111.00, d);
        queries.SaveOrUpdate(o1);
        Payment o2 = new Payment("t2", 222.00, d);
        queries.SaveOrUpdate(o2);
        for (int i = 0; i < queries.list().size(); i++) {
            System.out.println(queries.list().get(i).getType() + ","
                    + queries.list().get(i).getMontant() + ","
                    + queries.list().get(i).getDate());
            Payment o3 = queries.list().get(i);
            o3.setType(o3.getType() + " u");
            queries.SaveOrUpdate(o3);

        }
    }

    public static void testFacture() {
        FactureQueries queries = new FactureQueries();
        Date d = new Date();
        Facture o1 = new Facture(d, 111.00, 111.00, 111.00);
        queries.SaveOrUpdate(o1);
        Facture o2 = new Facture(d, 222.00, 222.00, 222.00);
        queries.SaveOrUpdate(o2);
        for (int i = 0; i < queries.list().size(); i++) {
            System.out.println(queries.list().get(i).getTimbre() + ","
                    + queries.list().get(i).getMontant() + ","
                    + queries.list().get(i).getTva() + ","
                    + queries.list().get(i).getDate());
            Facture o3 = queries.list().get(i);
            o3.setMontant(o3.getMontant() + 22);
            queries.SaveOrUpdate(o3);

        }
    }
}
