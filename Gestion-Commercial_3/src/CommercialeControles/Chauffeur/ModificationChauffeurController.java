package CommercialeControles.Chauffeur;

import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
    private Label camion;
    @FXML
    private JFXTextField codechauffeur;
    @FXML
    private JFXTextField telchauffeur;
    @FXML
    private JFXTextField typechauffeur;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;
    
    private ChauffeurQueries chauffeurQueries= new ChauffeurQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void sauvegarder(ActionEvent event) {
        String nom = nomchauffeur.getText();
        String prenom = prenomchauffeur.getText();
        //String codech = codechauffeur.getText();
        String tel = telchauffeur.getText();
        String type = typechauffeur.getText();

        Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || type.isEmpty()) {

                Notification.champVideNotification();
            } else {
                Chauffeur chauffeur = ShowChauffeurController.getChauffeur();
                chauffeur.setNomChauffeur(nom);
                chauffeur.setPrenomChauffeur(prenom);
                chauffeur.setTelephone(tel);
                chauffeur.setType(type);

                chauffeurQueries.saveOrUpdate(chauffeur);
                Notification.Updatenotification();
                new ShowPane().showChauffeur();
                savelabel.setVisible(true);
                annuler(event);
            }
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();

    }

    public void setData(String nom,String prenom, String codeCh, String tel, String type) {

        nomchauffeur.setText(nom);
        prenomchauffeur.setText(prenom);
        codechauffeur.setText(codeCh);
        telchauffeur.setText(tel);
        typechauffeur.setText(type);
    }

    @FXML
    private void closewindow(MouseEvent event) {
           Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();

        
    }
}