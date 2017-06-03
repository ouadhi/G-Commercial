package CommercialeControles.Payement;

import UIControle.Methode;
import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.Annee;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.AnneeQueries;
import com.gestionCommerciale.Models.ClientQueries;
import com.gestionCommerciale.Models.PaymentQueries;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tray.notification.NotificationType;

public class AjouterPayementController implements Initializable {

    @FXML
    private Text Nfacture;
    @FXML
    private JFXDatePicker datepayment;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXTextField montont;
    ArrayList<String> Types = new ArrayList<>();

    private Client client;
    private JFXListView<PayementCell> listepayement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datepayment.setValue(LocalDate.now());
        Types.add("Especes");
        Types.add("Cheque");
        ObservableList<String> liste = FXCollections.observableList(Types);
        type.setItems(liste);
        type.getSelectionModel().select(0);
                Methode.setOnlyDouble(montont, 8);

    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @FXML
    private void save(ActionEvent event) {

        String typeValue = type.getValue();
        double montant = Double.parseDouble(montont.getText());
        Date dateValue = Date.from(datepayment.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (typeValue.isEmpty() || montant == 0 || dateValue == null) {
            Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
        } else {

            Payment p = new Payment(typeValue, montant, dateValue);
            p.setClient(client);
            p.setAnnee(AnneeQueries.getSelected());
            PaymentQueries.SaveOrUpdate(p);
            Notification.Addnotification();
            AfficheListePayement();
            close(event);
        }
    }

    public void setdata(Client client, JFXListView<PayementCell> listepayement) {
        this.client = client;
        this.listepayement = listepayement;
        Nfacture.setText(client.getPrenom()+" "+client.getName());
    }

    private void AfficheListePayement() {
        PayementListeController.listepay.getItems().clear();
        List<Payment> listDB = PaymentQueries.list();

        List<PayementCell> list = new ArrayList<>();
        for (int i = 0; i < listDB.size(); i++) {
            list.add(new PayementCell(listDB.get(i)));
        }
        ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
        PayementListeController.listepay.setItems(myObservableList);
        PayementListeController.listepay.setExpanded(true);
        
    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

}
