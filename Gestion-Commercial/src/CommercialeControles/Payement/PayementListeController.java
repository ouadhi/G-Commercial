package CommercialeControles.Payement;

import CommercialeControles.Client.ClienCell;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.HibernateSchema.Payment;
import com.gestionCommerciale.Models.FactureQueries;
import com.gestionCommerciale.Models.PaymentQueries;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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

    private Client client  ; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AfficheListePayement();
        //printFactureDetails();
        listepayement.setExpanded(true);

    }

    public static String floatFormat(Double f) {
        DecimalFormat df = new DecimalFormat("##.00");
        String value = df.format(f);
        if (value.startsWith(".")) {
            value = "0" + value;
        }
        return value;
    }

//    private void printFactureDetails() {
//        FactureQueries fq = new FactureQueries();
//        Facture f = fq.getFacture(numero_facture);
//        System.err.println(numero_facture + "++++++++++++");
//
//        double totale = f.getMontant();
//        double totalePaye = f.getPaymentsMontant();
//        double reste = totale - totalePaye;
//        totalefacture.setText(floatFormat(totale));
//        totlepaye.setText(floatFormat(totalePaye));
//        this.reste.setText(floatFormat(reste));
//    }

    private void AfficheListePayement() {
        List<Payment> listDB = PaymentQueries.list();

        List<PayementCell> list = new ArrayList<>();
        for (int i = 0; i < listDB.size(); i++) {
            list.add(new PayementCell(listDB.get(i)));
        }
        ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
        listepayement.setItems(myObservableList);
        listepayement.setExpanded(true);
        
    }

    @FXML
    private void addpayment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewUrl.Addpayement));
            loader.load();

            AjouterPayementController pay = loader.getController();
            pay.setdata(client, listepayement);

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

    public void setDate(Client  client ) {
        this.client = client;
       // Nfacture.setText(this.client.getName()+" "+this.client.getPrenom());
        AfficheListePayement();
        listepayement.setExpanded(true);
        this.listepayement = listepay;

    }

}
