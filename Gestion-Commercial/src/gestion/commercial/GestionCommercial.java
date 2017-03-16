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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        //test hibernate
        ClientQueries clQueries= new ClientQueries();
        ChauffeurQueries chauffQueries= new ChauffeurQueries();
        Date date= new Date();
        Client clientObject1= new Client("athman", "arriwatt", "dssdd", "sss", "", "", "", date);
        Client clientObject2= new Client("djdfhjdf", "dfdfdf", "", "ss", "", "", "", date);
        Client clientObject3= new Client("atdsdsdhman", "fdffdf", "", "", "sssss", "", "", date);
       
        
        Chauffeur ch1= new Chauffeur("anouch", "mafia");
        Chauffeur ch2= new Chauffeur("hiphop", "almarikani");
        Chauffeur ch3= new Chauffeur("najm", "mafia");
        


        
        List<Chauffeur> listChauf= new ArrayList<>();
        listChauf.add(ch1);
        listChauf.add(ch2);
        listChauf.add(ch3);
        List<Client> listClient= new ArrayList<>();
        listClient.add(clientObject1);
        listClient.add(clientObject1);
        listClient.add(clientObject1);
        
        //test manyToMany
        clientObject1.setChauffeurs(listChauf);
        ch2.setClients(listClient);
        
        clQueries.insererOuModifieClient(clientObject1);
        clQueries.insererOuModifieClient(clientObject2);
        clQueries.insererOuModifieClient(clientObject3);
        chauffQueries.insererOuModifieChauffeur(ch1);
        chauffQueries.insererOuModifieChauffeur(ch2);
        chauffQueries.insererOuModifieChauffeur(ch3);
        

        for (int i = 0; i <clQueries.clientsList().size() ; i++) {
               System.out.println("this executes"+clQueries.clientsList().get(i).getName() +" "+
                       clQueries.clientsList().get(i).getPrenom());
               
        }
    }

}
