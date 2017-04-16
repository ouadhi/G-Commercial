package CommercialeControles.Chauffeur;

import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjouterChauffeurViewController implements Initializable {

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
    
    
    ChauffeurQueries chauffeurQueries= new ChauffeurQueries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void sauvegarder(ActionEvent event) {

        String nom = nomchauffeur.getText();
        String prenom = prenomchauffeur.getText();
        //String codech = codechauffeur.getText(); //autoIncrement
        String tel = telchauffeur.getText();
        String type = typechauffeur.getText();

        if (nom.isEmpty() || prenom.isEmpty()  || tel.isEmpty() || type.isEmpty()) {

            Notification.champVideNotification();
        } else {
            Chauffeur chauffeur= new Chauffeur(nom, prenom, tel,type );
            chauffeurQueries.saveOrUpdate(chauffeur);
            new ShowPane().showChauffeur();
            Notification.Addnotification();
            savelabel.setVisible(true);
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();
    }

    @FXML
    private void closewindow(MouseEvent event) {

        Stage g = (Stage) ((Node) event.getSource()).getScene().getWindow();
        g.close();

    }

}
