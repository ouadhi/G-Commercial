/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.commercial;

import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ChauffeurQueries;
import com.gestionCommerciale.Models.ClientQueries;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gestionCommerciale.HibernateSchema.*;
import com.gestionCommerciale.Models.*;
import com.gestionCommerciale.HibernateSchema.*;
import com.gestionCommerciale.Models.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Hicham
 */
public class GestionCommercial extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        

        URL url=null;
        try {
            url = new File("/FXMLDocument.fxml").toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestionCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //test hibernate
        /*
        ClientQueries clQueries = new ClientQueries();
        ChauffeurQueries chauffQueries = new ChauffeurQueries();
        Date date = new Date();
        Client clientObject1 = new Client("athman", "arriwatt", "dssdd", "sss", "", "", "", date);
        Client clientObject2 = new Client("djdfhjdf", "dfdfdf", "", "ss", "", "", "", date);
        Client clientObject3 = new Client("atdsdsdhman", "fdffdf", "", "", "sssss", "", "", date);

        Chauffeur ch1 = new Chauffeur("anouch", "mafia");
        Chauffeur ch2 = new Chauffeur("hiphop", "almarikani");
        Chauffeur ch3 = new Chauffeur("najm", "mafia");

        List<Chauffeur> listChauf = new ArrayList<>();
        listChauf.add(ch1);
        listChauf.add(ch2);
        listChauf.add(ch3);
        List<Client> listClient = new ArrayList<>();
        listClient.add(clientObject1);
        listClient.add(clientObject1);
        listClient.add(clientObject1);

        //test manyToMany
        clientObject1.setChauffeurs(listChauf);
        clientObject2.setChauffeurs(listChauf);
        //ch2.setClients(listClient);

        clQueries.insererOuModifieClient(clientObject1);
        clQueries.insererOuModifieClient(clientObject2);
        clQueries.insererOuModifieClient(clientObject3);
        chauffQueries.insererOuModifieChauffeur(ch1);
        chauffQueries.insererOuModifieChauffeur(ch2);
        chauffQueries.insererOuModifieChauffeur(ch3);


        for (int i = 0; i < clQueries.clientsList().size(); i++) {
            System.out.println(clQueries.clientsList().get(i).getName() + " "
                    + clQueries.clientsList().get(i).getPrenom());
        }

        ProduitQueries proQueries = new ProduitQueries();
        Produit productObject = new Produit("p1", 22, 2222.22f);
        proQueries.SaveOrUpdate(productObject);
        productObject = new Produit("p2", 33, 3333.33f);
        proQueries.SaveOrUpdate(productObject);
        for (int i = 0; i < proQueries.list().size(); i++) {
            System.out.println(proQueries.list().get(i).getNom()+ ","
                    + proQueries.list().get(i).getQuantite()+ ","
                    + proQueries.list().get(i).getPrix());
         }
        
  */      
}
    public void testExpedition(){
    
    }
}
