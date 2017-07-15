
package CommercialeControles.Produit;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Produit;
import com.gestionCommerciale.Models.ProduitQueries;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class StockProduitController implements Initializable {

    @FXML
    private JFXTextField nomProduit;
    @FXML
    private JFXTextField stocke;
    @FXML
    private JFXTextField Qadd;
    
    private  Produit produit  ; 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nomProduit.setDisable(true);
       stocke.setDisable(true);
       
        Methode.setOnlyDouble(Qadd, 16);
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void save(ActionEvent event) {
        int  quantite  = Integer.parseInt(Qadd.getText()) ; 
        if (Qadd.getText().isEmpty()) {
            Notification.errorNotification();
        }else{
            produit.setQuantite(produit.getQuantite()+quantite);
            ProduitQueries.SaveOrUpdate(produit) ; 
            Notification.Updatenotification();
            new ShowPane().showProduit(); 
            annuler(event);
            
        }
      
    }

    @FXML
    private void annuler(ActionEvent event) {
          Methode.getStage(event).close();
    }
    
    public   void SetData  (Produit  produit) {
        this.produit = produit   ; 
        nomProduit.setText(produit.getNom());
        stocke.setText(produit.getQuantite()+"");
    } 
    
  
    
}
