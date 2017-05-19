package CommercialeControles.Payement;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PayementListeController implements Initializable {

    @FXML
    private Text Nfacture;
    @FXML
    private JFXListView<PayementCell> listepayement;
    public static JFXListView<PayementCell> listepay;
    
    @FXML
    private JFXTextField totalefacture;
    @FXML
    private JFXTextField totlepaye;
    @FXML
    private JFXTextField reste;

    private int numero_facture;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AfficheListePayement();
        listepayement.setExpanded(true);

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

    @FXML
    private void addpayment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.Addpayement));
            loader.load();

            AjouterPayementController pay = loader.getController();
            pay.setdata(numero_facture, listepayement);

            AnchorPane root = loader.getRoot();

            StageDialog dialog = new StageDialog(Methode.getStage(event), root);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ClienCell.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    public void setDate(int facture) {
        this.numero_facture = facture;
        Nfacture.setText(Integer.toString(facture));
        AfficheListePayement();
        listepayement.setExpanded(true);
        this.listepayement = listepay ; 

    }

}
