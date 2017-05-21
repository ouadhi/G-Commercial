
package CommercialeControles.Vente;

import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class PrintViewController implements Initializable {

    @FXML
    private JFXCheckBox facture;
    @FXML
    private JFXCheckBox chregement;
    @FXML
    private JFXCheckBox livraison;
    
    private  Facture factureimp ; 
    @FXML
    private Text numerofacture;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void print(ActionEvent event) {
        if (facture.isSelected()) {
            
        }
        
        if (chregement.isSelected()) {
            
        }
        
        if (livraison.isSelected()) {
            
        }
        
        
    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
    public void setData ( Facture factureimp){
        
        this.factureimp  = factureimp ; 
        numerofacture.setText(""+factureimp.getIdFacture());
        
       
        
    }
    
}
