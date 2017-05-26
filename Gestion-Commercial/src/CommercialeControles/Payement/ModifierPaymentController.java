package CommercialeControles.Payement;

import UIControle.Methode;
import UIControle.Notification;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.analytics.data.Measure;

public class ModifierPaymentController implements Initializable {

    @FXML
    private Text Nfacture;
    @FXML
    private JFXDatePicker datepayment;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXTextField montont;
    @FXML
    private Text Npayement;

    private Payment payment;
    private JFXListView<PayementCell> listepayement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save(ActionEvent event) {

        Optional<ButtonType> result = Notification.updateAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            Notification.Updatenotification();
            AfficheListePayement();
            close(event);
        }

    }

    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    ArrayList<String> Types = new ArrayList<>();

    public void setData(Payment payement, JFXListView<PayementCell> listepayement) {
        this.payment = payement;
        this.listepayement = listepayement;

        // ajoute le numero de  la facture 
        //  Nfacture.setText(Integer.toString(this.num_facture));
        Npayement.setText(Integer.toString(this.payment.getIdPayment()));

        Types.add("Cache");
        Types.add("Espace");
        ObservableList<String> liste = FXCollections.observableList(Types);
        type.setItems(liste);

        datepayment.setTime(LocalTime.now());

        Methode.setOnlyDouble(montont, 8);

    }

    private void AfficheListePayement() {
        List<PayementCell> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Payment payement = new Payment("Cache", 4000, new Date());
            PayementCell cell = new PayementCell(payement);
            list.add(cell);

        }

        ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
        listepayement.setItems(myObservableList);
    }

}
