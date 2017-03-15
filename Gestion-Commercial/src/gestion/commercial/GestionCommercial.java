/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.commercial;

import com.gestionCommerciale.HibernateSchema.*;
import com.gestionCommerciale.Models.*;
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
        ClientQueries clQueries = new ClientQueries();
        Client clientObject = new Client("attman", "3riwat");
        clQueries.insererOuModifieClient(clientObject);
        for (int i = 0; i < clQueries.clientsList().size(); i++) {
            System.out.println(clQueries.clientsList().get(i).getName() + " "
                    + clQueries.clientsList().get(i).getPrenom());
        }

        ProductQueries proQueries = new ProductQueries();
        Product productObject = new Product("p1", 22, 2222.22f);
        proQueries.SaveOrUpdateProduct(productObject);
        productObject = new Product("p2", 33, 3333.33f);
        proQueries.SaveOrUpdateProduct(productObject);
        for (int i = 0; i < clQueries.clientsList().size(); i++) {
            System.out.println(proQueries.productsList().get(i).getName() + ","
                    + proQueries.productsList().get(i).getQuantity()+ ","
                    + proQueries.productsList().get(i).getPrice());

        }

    }
}
