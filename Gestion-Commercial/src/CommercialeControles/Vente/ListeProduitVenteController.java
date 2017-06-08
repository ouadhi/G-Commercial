package CommercialeControles.Vente;

import CommercialeControles.Ble.BelCell;
import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Facture_Produit;
import com.gestionCommerciale.Models.BleQueries;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class ListeProduitVenteController implements Initializable {
    
    @FXML
    private Label compte;
    @FXML
    private JFXListView<cellProduit> liste;
    
    private Facture  facture ; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
    
    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close(); 
    }
    
    private void setTotale() {
        compte.setText(liste.getItems().size()+"");
    }
    
    
    public void setData(Facture  facture ){
        this.facture  = facture  ; 
        
         List<cellProduit> list = new ArrayList<>();
        
        for (int i = 0; i < this.facture.getQtes().size(); i++) {
            Facture_Produit pro  =  this.facture.getQtes().get(i) ; 
            list.add(new cellProduit(pro.getProduit().getNom() , pro.getProduit().getPrix()+"" , pro.getQte_fact()+""));
        }
        
        ObservableList<cellProduit> myObservableList = FXCollections.observableList(list);
        
        liste.setItems(myObservableList);
        liste.setExpanded(true);
        
        setTotale();
        
    }
    
    public void setData2(ArrayList<PorduitH> listep ){
       
        
         List<cellProduit> list = new ArrayList<>();
        
        for (int i = 0; i < listep.size(); i++) {
            PorduitH pro  =  listep.get(i)   ; 
            list.add(new cellProduit(pro.getProduit().getNom() , pro.getProduit().getPrix()+"" , pro.getQuantite()+""));
        }
        
        ObservableList<cellProduit> myObservableList = FXCollections.observableList(list);
        
        liste.setItems(myObservableList);
        liste.setExpanded(true);
        
        setTotale();
        
    }
    
    class cellProduit extends GridPane {
        
        ColumnConstraints columnConstraints;
        ColumnConstraints columnConstraints0;
        ColumnConstraints columnConstraints1;
        RowConstraints rowConstraints;
        Label produit;
        Label prix;
        Label qantite;
        
        String produittxt   ; 
        String prixtxt  ; 
        String qunt ; 

        public String getProduittxt() {
            return produittxt;
        }

        public void setProduittxt(String produittxt) {
            this.produittxt = produittxt;
        }

        public String getPrixtxt() {
            return prixtxt;
        }

        public void setPrixtxt(String prixtxt) {
            this.prixtxt = prixtxt;
        }

        public String getQunt() {
            return qunt;
        }

        public void setQunt(String qunt) {
            this.qunt = qunt;
        }

       
      
        
        
       public cellProduit(String produittxt, String prixtxt, String qunt) {
            this.produittxt = produittxt;
            this.prixtxt = prixtxt;
            this.qunt = qunt;
            
            columnConstraints = new ColumnConstraints();
            columnConstraints0 = new ColumnConstraints();
            columnConstraints1 = new ColumnConstraints();
            rowConstraints = new RowConstraints();
            produit = new Label();
            prix = new Label();
            qantite = new Label();
            
            setPrefHeight(45.0);
            setPrefWidth(460.0);
            
            columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
            columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            columnConstraints.setMinWidth(10.0);
            columnConstraints.setPrefWidth(100.0);
            
            columnConstraints0.setHalignment(javafx.geometry.HPos.CENTER);
            columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            columnConstraints0.setMinWidth(10.0);
            columnConstraints0.setPrefWidth(100.0);
            
            columnConstraints1.setHalignment(javafx.geometry.HPos.CENTER);
            columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            columnConstraints1.setMinWidth(10.0);
            columnConstraints1.setPrefWidth(100.0);
            
            rowConstraints.setMinHeight(10.0);
            rowConstraints.setPrefHeight(30.0);
            rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
            
            GridPane.setHalignment(produit, javafx.geometry.HPos.CENTER);
            GridPane.setValignment(produit, javafx.geometry.VPos.CENTER);
            produit.getStyleClass().add("label_txt");
            produit.setText(produittxt);
            produit.setFont(new Font(16.0));
            
            GridPane.setColumnIndex(prix, 1);
            GridPane.setHalignment(prix, javafx.geometry.HPos.CENTER);
            GridPane.setValignment(prix, javafx.geometry.VPos.CENTER);
            prix.getStyleClass().add("label_txt");
            prix.setText(prixtxt);
            prix.setFont(new Font(16.0));
            
            GridPane.setColumnIndex(qantite, 2);
            GridPane.setHalignment(qantite, javafx.geometry.HPos.CENTER);
            GridPane.setValignment(qantite, javafx.geometry.VPos.CENTER);
            qantite.getStyleClass().add("label_txt");
            qantite.setText(qunt);
            qantite.setFont(new Font(16.0));
            
            getColumnConstraints().add(columnConstraints);
            getColumnConstraints().add(columnConstraints0);
            getColumnConstraints().add(columnConstraints1);
            getRowConstraints().add(rowConstraints);
            getChildren().add(produit);
            getChildren().add(prix);
            getChildren().add(qantite);
            
        }
        
    }
    
}
