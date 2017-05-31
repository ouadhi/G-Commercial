
package CommercialeControles.Autre;

import UIControle.Methode;
import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.Annee;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;


public class ModifierAnneeController implements Initializable {

    @FXML
    private JFXTextField tva;
    
    Annee annee  ; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void save(ActionEvent event) {
        
       Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tva.getText().isEmpty()) {
                Notification.champVideNotification();
            }else{
                
                Notification.Updatenotification();
                quitter(event);
            }
        }
        
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
    public void setData (Annee annee ) {
        this.annee = annee ; 
        tva.setText(this.annee.getTva()+"");
    }
    
}
