package CommercialeControles.Chauffeur;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModificationChauffeurController implements Initializable {

    @FXML
    private JFXTextField nomchauffeur;
    @FXML
    private JFXTextField prenomchauffeur;
   
    @FXML
    private JFXTextField telchauffeur;
    
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
   
    @FXML
    private ImageView close;
    
    public ArrayList<Camion> camions_Chauffeur = new ArrayList<>();
    private Chauffeur chauffeur;
    @FXML
    private JFXComboBox<String> typechauffeur;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        Methode.SetUpper(nomchauffeur ,30);
        Methode.setOnlyInteger(telchauffeur, 10);
    }

    @FXML
    private void sauvegarder(ActionEvent event) {
        String nom = nomchauffeur.getText();
        String prenom = prenomchauffeur.getText();
        String tel = telchauffeur.getText();
        String type = typechauffeur.getSelectionModel().getSelectedItem() ; 
        Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || type.isEmpty()) {
                Notification.champVideNotification();
            } else {
                Chauffeur chauffeur = ShowChauffeurController.getChauffeur();
                chauffeur.setNom(nom);
                chauffeur.setPrenom(prenom);
                chauffeur.setTelephone(tel);
                chauffeur.setType(type);
                Chauffeur c = ChauffeurQueries.getChauffeurByNomPrenom(nom, prenom);
                if (c != null && c.getId() != chauffeur.getId()) {
                    Notification.error("Ce Nom existe déja, utilisé un autre");
                } else if (ChauffeurQueries.SaveOrUpdate(chauffeur)) {
                    ChauffeurQueries.SaveOrUpdate(chauffeur);
                    Notification.Updatenotification();
                    new ShowPane().showChauffeur();
                    
                    annuler(event);
                }
            }
        }
    }

    @FXML
    private void annuler(ActionEvent event
    ) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();
    }

    public void setData(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
        setType();
        nomchauffeur.setText(chauffeur.getNom());
        prenomchauffeur.setText(chauffeur.getPrenom());
        telchauffeur.setText(chauffeur.getTelephone());
        typechauffeur.setValue(chauffeur.getType());
    }

    @FXML
    private void closewindow(MouseEvent event) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();
    }
    
     public void setType() {
      typechauffeur.getItems().add("INTERNE") ; 
      typechauffeur.getItems().add("EXTERNE") ; 
  }
    
}
