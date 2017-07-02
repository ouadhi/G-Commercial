package CommercialeControles.Chauffeur;

import CommercialeControles.OperationAchat.ChauffeurListH;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.HibernateSchema.Chauffeur;
import com.gestionCommerciale.Models.ChauffeurQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AjouterChauffeurExtratController implements Initializable {

    @FXML
    private JFXTextField nomchauffeur;
    @FXML
    private JFXTextField prenomchauffeur;

    @FXML
    private JFXTextField telchauffeur;
    @FXML
    private JFXComboBox<String> typechauffeur;
    @FXML
    private JFXButton savebttn;
    @FXML
    private JFXButton anullerbttn;
    @FXML
    private Label savelabel;
    @FXML
    private ImageView close;

    public ArrayList<Camion> camions_Chauffeur = new ArrayList<>();

    private JFXComboBox<String> camionbox;

    JFXListView<ChauffeurListH> listeChaffeur;

    private void addmaion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.SelectCamionchauffeur));
            loader.load();

            selectionnerCamionController Modifier = loader.getController();
            Modifier.setData(camions_Chauffeur, camionbox);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(AjouterChauffeurViewController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void deletecamion(ActionEvent event) {
        int com = camionbox.getSelectionModel().getSelectedIndex();
        if (com >= 0) {
            camionbox.getItems().remove(com);
            camions_Chauffeur.remove(com);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.SetUpper(nomchauffeur, 30);
        Methode.setOnlyInteger(telchauffeur, 10);
        Methode.setsizeString(prenomchauffeur, 30);
        setType();
    }

    @FXML
    private void sauvegarder(ActionEvent event) {

        String nom = nomchauffeur.getText();
        String prenom = prenomchauffeur.getText();
        String tel = telchauffeur.getText();
        String type = typechauffeur.getSelectionModel().getSelectedItem();

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || type.isEmpty()) {

            Notification.champVideNotification();
        } else {
            if (ChauffeurQueries.getChauffeurByNomPrenom(nom, prenom) != null) {

                Notification.error("Ce chauffeur exist deja");
            } else {

                try {
                    Chauffeur chauffeur = new Chauffeur(nom, prenom, tel, type);
                    ChauffeurQueries.SaveOrUpdate(chauffeur);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Notification.Addnotification();
                annuler(event);
                refreshListe();
            }

        }

    }

    public void setType() {
        typechauffeur.getItems().add("INTERNE");
        typechauffeur.getItems().add("EXTERNE");
    }

    public void setData(JFXListView<ChauffeurListH> listeChaffeur) {
        this.listeChaffeur = listeChaffeur;
    }

    public void refreshListe() {
        List<ChauffeurListH> list = new ArrayList<>();
        List<Chauffeur> listChauffeursDB = ChauffeurQueries.list();
        for (int i = 0; i < listChauffeursDB.size(); i++) {
            list.add(new ChauffeurListH(listChauffeursDB.get(i)));

        }

        ChauffeurListH ch = new ChauffeurListH(listeChaffeur);
         list.add(ch) ;
        ObservableList<ChauffeurListH> myObservableList = FXCollections.observableList(list);
        listeChaffeur.setItems(myObservableList);
        
        listeChaffeur.setOrientation(Orientation.HORIZONTAL);
        
    }

}
