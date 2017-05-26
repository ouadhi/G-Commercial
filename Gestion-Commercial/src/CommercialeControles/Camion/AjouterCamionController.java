
package CommercialeControles.Camion;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AjouterCamionController implements Initializable {

    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    private Label savelabel;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField codecamion;
    @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField typecamion;
    
    CamionQueries camionQueries= new CamionQueries();
    @FXML
    private JFXTextField poisCamion;
    @FXML
    private Label labelsave;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyFloat(poisCamion, 5);
        
    }    

    @FXML
    private void sauvegarder(ActionEvent event) {
        String code  = codecamion.getText()  ; 
        String matricule  = this.matricule.getText() ; 
        String type  = typecamion.getText()  ;  
        Double poid= Double.parseDouble(poisCamion.getText());

        if (code.isEmpty()|| matricule.isEmpty()|| type.isEmpty()|| poid.equals(0)) {
            Notification.champVideNotification();   
        } else {
            if(camionQueries.getCamionByMatricule(matricule)!=null){              
                 Notification.error("Ce camion exist deja");
            }else{
            try {
                Camion camion = new Camion(code, matricule, type,poid);
                camionQueries.SaveOrUpdate(camion);
                new ShowPane().showCamion();
                Notification.Addnotification();
                savelabel.setVisible(true);
                annuler(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        
       Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow() ; 
       stage.close();
    }

    @FXML
    private void closewindow(MouseEvent event) {
        Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow() ; 
       stage.close();
    }
    
}
