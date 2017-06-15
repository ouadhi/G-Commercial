package CommercialeControles.Client;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tray.notification.NotificationType;

public class AjouterClientController implements Initializable {

    @FXML
    private JFXTextField nomtxt;
    @FXML
    private JFXTextField prenomtxt;
    @FXML
    private JFXTextField NRtxt;
    @FXML
    private JFXTextField NAtxt;
    @FXML
    private JFXTextField adressetxt;
    @FXML
    private JFXTextField activitetxt;
    @FXML
    private JFXTextField NCarteF;
    @FXML
    private DatePicker datedept;
    @FXML
    private JFXButton annuler;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;
    private JFXTextField Solde;
    @FXML
    private JFXComboBox<String> activiteBox;
    @FXML
    private JFXCheckBox fermer;
    @FXML
    private JFXTextField solde;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datedept.setValue(LocalDate.now());
         Methode.SetUpper(nomtxt, 30);
        Methode.setsizeString(prenomtxt, 30);
        Methode.setsizeString(prenomtxt, 30);
        Methode.SetUpper(NRtxt, 30);
        Methode.SetUpper(NAtxt, 20);
        Methode.SetUpper(NCarteF, 20);

        
        setActivty();
    }

    @FXML
    private void Sauvgarder(ActionEvent event) {

        String nom = nomtxt.getText();
        String prenom = prenomtxt.getText();
        String NR = NRtxt.getText();
        String NA = NAtxt.getText();
        String adresse = adressetxt.getText();
        //String activite = activitetxt.getText();
        String activite = activiteBox.getSelectionModel().getSelectedItem();
        String Ncarte = NCarteF.getText();

        if (nom.isEmpty() || prenom.isEmpty() || NR.isEmpty() || NA.isEmpty() || adresse.isEmpty() || activite.isEmpty() || Ncarte.isEmpty() || datedept.getValue() == null) {
            Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
        } else {

            Date dateDepotDossier = Date.from(datedept.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (ClientQueries.getClientByRegistre(NR) != null) {
                //notification for already exists
                Notification.error("Ce client est exite déja!");
            } else {
                // add to database
                try {
                    Client client = new Client(nom, prenom, NR, NA, adresse, activite, dateDepotDossier, Ncarte);
                    ClientQueries.SaveOrUpdate(client);
                    Notification.Addnotification();
                    new ShowPane().showClient();
                    if (!fermer.isSelected()) {
                        quitter(event);
                    } else {
                        clear();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        Stage currentSatge = Methode.getStage(event);
        currentSatge.close();
    }

    @FXML
    private void close(MouseEvent event) {
        Stage currentSatge = Methode.getStageMouses(event);
        currentSatge.close();
    }

    public void setActivty() {
        List<String> listeActivty = new ArrayList<>();
        listeActivty.add("Boulangerie");
        listeActivty.add("Agriculteur");
        listeActivty.add("Grossiste");
        listeActivty.add("Autre");

        ObservableList<String> list = FXCollections.observableList(listeActivty);

        activiteBox.setItems(list);

    }

    private void clear() {
        nomtxt.setText("");
        prenomtxt.setText("");
        NRtxt.setText("");
        NAtxt.setText("");
        adressetxt.setText("");
        NCarteF.setText("");
    }
}
