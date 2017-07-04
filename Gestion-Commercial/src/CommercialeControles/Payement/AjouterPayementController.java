package CommercialeControles.Payement;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.AnneeQueries;
import com.gestionCommerciale.Models.ClientQueries;
import com.gestionCommerciale.Models.PaymentQueries;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
	@FXML
	private JFXTextField timbre;
    @FXML
    private HBox extrat;
    @FXML
    private JFXTextField banque;
    @FXML
    private JFXTextField numeroCmpt;

    private void AfficheListePayement() {

        PayementListeController.listepay.getItems().clear();
        List<Payment> listDB = PaymentQueries.getPaymentsListByClientId(client.getId());
        double totalFactured = ClientQueries.totalFactured(client);
        double totalVersed = ClientQueries.totalVersed(client);
        double solde = totalVersed - totalFactured;
        PayementListeController.STtotalefacture.setText(Methode.DoubleFormat(totalFactured) + "");
        PayementListeController.STtotlepaye.setText(Methode.DoubleFormat(totalVersed) + "");
        PayementListeController.STreste.setText(Methode.DoubleFormat(solde) + "");

        List<PayementCell> list = new ArrayList<>();
        for (int i = 0; i < listDB.size(); i++) {
            list.add(new PayementCell(listDB.get(i)));
        }
        ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
        PayementListeController.listepay.setItems(myObservableList);
        PayementListeController.listepay.setExpanded(true);

    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		datepayment.setValue(LocalDate.now());
		Types.add("Especes");
		Types.add("Cheque");
		Types.add("A terme");
                Types.add("Virement");
		ObservableList<String> liste = FXCollections.observableList(Types);
		type.setItems(liste);
		type.getSelectionModel().select(0);
		Methode.setOnlyDouble(montont, 8);
		Methode.setZeroRemoved(montont);
		Methode.setSelectedMouseClick(montont);
		montont.setText("0.00");

    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void save(ActionEvent event) {

        String typeValue = type.getValue();
        String numCVValue = "";
        String banqueValue = "";
        //kada
        if (typeValue == "Cheque" || typeValue == "Virement") {
             numCVValue = numeroCmpt.getText()  ;  
             banqueValue = banque.getText()  ; 
        }
        double montant = Double.parseDouble(montont.getText());
        Date dateValue = Date.from(datepayment.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (typeValue.isEmpty() || montant == 0 || dateValue == null) {
            Notification.notif(NotificationType.ERROR, "V\u00E9rification", "V\u00E9rifier que tout les champs sont remplis!");
        } else {

            Payment p = new Payment(typeValue, numCVValue, banqueValue, montant, dateValue);
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
        Nfacture.setText(client.getPrenom() + " " + client.getName());
    }

    @FXML
    private void typeEvent(ActionEvent event) {
         if (type.getSelectionModel().getSelectedItem().equals("Cheque") ||type.getSelectionModel().getSelectedItem().equals("Virement") ) {
            extrat.setVisible(true);
        } else {
            extrat.setVisible(false);
        }
    }

}
