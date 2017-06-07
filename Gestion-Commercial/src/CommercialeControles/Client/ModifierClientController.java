package CommercialeControles.Client;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.notification.NotificationType;

public class ModifierClientController implements Initializable {

    @FXML
    private ImageView close;
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
    private Client client;
    private JFXTextField Solde;
    @FXML
    private JFXComboBox<String> activiteBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Methode.SetUpper(NRtxt);
        Methode.SetUpper(NAtxt);
        Methode.SetUpper(NCarteF);

    }

    @FXML
    private void Sauvgarder(ActionEvent event) {

        String nom = nomtxt.getText();
        String prenom = prenomtxt.getText();
        String NR = NRtxt.getText();
        String NA = NAtxt.getText();
        String adresse = adressetxt.getText();
        String activite = activitetxt.getText();
        String Ncarte = NCarteF.getText();
        //Date dateDepotDossier = Date.from(datedept.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            if (nom.isEmpty() || prenom.isEmpty() || NR.isEmpty() || NA.isEmpty() || adresse.isEmpty() || activite.isEmpty() || Ncarte.isEmpty() || datedept.getValue() == null) {
                Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
            } else {
                if (ClientQueries.getClientByRegistre(NR) != null) {
                    //notification for already exists
                    Notification.error("Ce client est exite déja!");
                } else {
                    Date dateDepotDossier = Date.from(datedept.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    client.setName(nom);
                    client.setPrenom(prenom);
                    client.setNumArticle(NA);
                    client.setAddressClient(adresse);
                    client.setNumRegCom(NR);
                    client.setTypeActivity(activite);
                    client.setnCarteFiscale(Ncarte);
                    client.setDateDepotDossier(dateDepotDossier);
                    ClientQueries.SaveOrUpdate(client);

                    Notification.Updatenotification();
                    new ShowPane().showClient();
                    savelabel.setVisible(true);
                    quitter(event);
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

    public void SetData(Client client) {

        Methode.SetUpper(nomtxt);
        setActivty();

        this.client = client;
        nomtxt.setText(client.getName());
        prenomtxt.setText(client.getPrenom());
        activitetxt.setText(client.getTypeActivity());
        NRtxt.setText(client.getNumRegCom());
        adressetxt.setText(client.getAddressClient());
        NAtxt.setText(client.getNumArticle());
        NRtxt.setText(client.getNumRegCom());
        NCarteF.setText(client.getnCarteFiscale());
        datedept.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(client.getDateDepotDossier())));
        activiteBox.setValue(client.getTypeActivity());

    }

    public void setActivty() {
        List<String> listeActivty = new ArrayList<>();
        listeActivty.add("Boulangerie");
        listeActivty.add("Agriculteur");
        listeActivty.add("Autre");

        ObservableList<String> list = FXCollections.observableList(listeActivty);

        activiteBox.setItems(list);

    }

}
