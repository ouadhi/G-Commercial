package CommercialeControles.OperationAchat;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.AchatQueries;
import com.gestionCommerciale.Models.AnneeQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FinOperationController implements Initializable {

    public static DockListeH dock = null;
    public static CamionListeH camion = null;
    public static ChauffeurListH chauffeur = null;
    public static BleListeH ble = null;

    private static String imgOk = "/icons/OkGreen.png";
    private static String imgfaux = "/icons/cancel.png";

    static ImageView statech;
    static ImageView statecam;

    static ImageView stateb;
    static ImageView statDock;

    public static void ClearVar() {
        dock = null;
        camion = null;
        chauffeur = null;
        ble = null;
    }

    public static void stateicon() {

        if (chauffeur == null) {
            statech.setImage(new Image(imgfaux));

        } else {
            statech.setImage(new Image(imgOk));
        }

        if (camion == null) {
            statecam.setImage(new Image(imgfaux));
        } else {
            statecam.setImage(new Image(imgOk));
        }

        if (ble == null) {
            stateb.setImage(new Image(imgfaux));
        } else {
            stateb.setImage(new Image(imgOk));
        }

        if (dock == null) {
            statDock.setImage(new Image(imgfaux));
        } else {
            statDock.setImage(new Image(imgOk));
        }
    }

    private Image view = new Image(getClass().getResourceAsStream("/icons/preview.png"));
    private Image viewHover = new Image(getClass().getResourceAsStream("/icons/previewGreen.png"));

    @FXML
    private ImageView statechauffeur;

    @FXML
    private ImageView statecamion;
    @FXML
    private ImageView stateble;
    @FXML
    private ImageView infochaffeur;
    private PopOver popup;
    @FXML
    private ImageView infocamion;
    @FXML
    private ImageView infoBle;
    @FXML
    private JFXTextField numero;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField Q_Acquit;
    @FXML
    private JFXTextField Q_fournie;
    @FXML
    private JFXTextField diff;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton annuler;
    private ImageView diffIcon;
    @FXML
    private ImageView infodock;
    @FXML
    private ImageView statedock;
    @FXML
    private JFXTextField numerotickit;
    private JFXTextField numeroBon;

    @FXML
    private JFXTextField poidTotal;

    @FXML
    private JFXTextField poidcamion;

    public void addAchat() {
        String numTiquetVal = numerotickit.getText();
        String numAcqtVal = numero.getText();
        double qActVal = Double.parseDouble(Q_Acquit.getText());
        double qFourVal = Double.parseDouble(Q_fournie.getText());
        double diffVal = Double.parseDouble(diff.getText());// modification
        Date date = java.sql.Date.valueOf(this.date.getValue());

        double poidCamionVal = Double.parseDouble(poidcamion.getText());

        Achat achat = new Achat(numTiquetVal, numAcqtVal, qActVal, qFourVal, diffVal, date, poidCamionVal,
                AnneeQueries.getSelected());
        achat.setCamion(camion.getCamion());
        achat.setChauffeur(chauffeur.getChauffeur());
        achat.setDock(dock.getDock());
        achat.setBle(ble.getBle());
        Ble b = ble.getBle();
        b.setQte(b.getQte()+qFourVal);
        BleQueries.SaveOrUpdate(b);
        AchatQueries.SaveOrUpdate(achat);
    }

    @FXML
    private void CloseinfoBle(MouseEvent event) {
        infoBle.setImage(view);
        popup.hide();
    }

    @FXML
    private void CloseInfoCamion(MouseEvent event) {
        infocamion.setImage(view);
        popup.hide();
    }

    @FXML
    private void CloseInforChauffeur(MouseEvent event) {
        popup.hide();
        infochaffeur.setImage(view);
    }

    @FXML
    private void CloseInforDock(MouseEvent event) {
        infodock.setImage(view);
        popup.hide();
    }

    private void founiePressed(KeyEvent event) {
        float qun_acquit = Float.parseFloat(Q_Acquit.getText());
        float qun_founier = Float.parseFloat(Q_fournie.getText());
        float d = qun_acquit - qun_founier;
        diff.setText(Float.toString(d));
        if (d == 0) {
            diffIcon.setImage(new Image(imgOk));
        } else {
            diffIcon.setImage(new Image(imgfaux));
        }
    }

    private void FournirReleased(KeyEvent event) {

        double qun_acquit = Double.parseDouble(Q_Acquit.getText());
        double qun_founier = Double.parseDouble(Q_fournie.getText());
        double d = qun_founier - qun_acquit;
        diff.setText(Methode.DoubleFormat(d) + "");
        // back
        // if (d == 0) {
        // diffIcon.setImage(new Image(imgOk));
        // } else {
        // diffIcon.setImage(new Image(imgfaux));
        // }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statech = this.statechauffeur;
        statecam = statecamion;
        stateb = stateble;
        statDock = statedock;
        Methode.setOnlyDouble(poidTotal, 10);
        Methode.setSelectedMouseClick(poidTotal);
        Methode.setOnlyDouble(Q_Acquit, 10);
        Methode.setSelectedMouseClick(Q_Acquit);
        Methode.setOnlyDouble(poidcamion, 10);
        Methode.setSelectedMouseClick(poidcamion);
        Q_fournie.setEditable(false);
        Q_fournie.setText("0.00");
        poidTotal.setText("0.00");
        Q_Acquit.setText("0.00");
        poidcamion.setText("0.00");
        diff.setText("0.00");
        Q_Acquit.setOnKeyReleased(e -> {
            poidtotalkey(e);
        });
        poidcamion.setOnKeyReleased(e -> {
            poidtotalkey(e);
        });
        Methode.setOnlyInteger(numero, 10);
        // Methode.setOnlyInteger(poidcamion, 10);
        Methode.setOnlyInteger(numerotickit, 10);
        // Methode.setOnlyDouble(Q_Acquit, 10);
        Methode.setSelectedMouseClick(numero);
        Methode.setSelectedMouseClick(poidcamion);
        Methode.setSelectedMouseClick(numerotickit);
        Methode.setSelectedMouseClick(Q_Acquit);

        date.setValue(LocalDate.now());
        diff.setEditable(false);

        popup = new PopOver();
        PopOver popup = new PopOver();
        popup.setCornerRadius(4);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        
        infocamion.setOnMouseClicked(e -> {
         infocamion.setImage(viewHover);
        popup.setContentNode(camion);
        popup.show(infocamion);
        });

    }

    @FXML
    private void opneInfoBle(MouseEvent event) {
        infoBle.setImage(viewHover);
        popup.setContentNode(ble);
        popup.show(infoBle);
    }

    @FXML
    private void opneInfoCamion(MouseEvent event) {
       
    }

    @FXML
    private void opneInfoChauufeur(MouseEvent event) {
        infochaffeur.setImage(viewHover);
        popup.setContentNode(chauffeur);
        popup.show(infochaffeur);

    }

    @FXML
    private void opneInfoDock(MouseEvent event) {
        infodock.setImage(viewHover);
        popup.setContentNode(dock);
        popup.show(infodock);
    }

    // back
    @FXML
    private void poidtotalkey(KeyEvent event) {
        if (poidTotal.getText().isEmpty()) {
            poidTotal.setText("0.00");
            poidTotal.selectAll();
        }

        if (Q_fournie.getText().isEmpty()) {
            Q_fournie.setText("0.00");
            Q_fournie.selectAll();
        }
        if (poidcamion.getText().isEmpty()) {
            poidcamion.setText("0.00");
            poidcamion.selectAll();
        }
        if (Q_Acquit.getText().isEmpty()) {
            Q_Acquit.setText("0.00");
            Q_Acquit.selectAll();
        }

        double poidTotalVal = Double.parseDouble(poidTotal.getText());
        double poidcamionVal = Double.parseDouble(poidcamion.getText());
        double dif = poidTotalVal - poidcamionVal;
        Q_fournie.setText(Methode.DoubleFormat(dif) + "");

        double qun_acquit = Double.parseDouble(Q_Acquit.getText());
        double qun_founier = Double.parseDouble(Q_fournie.getText());
        double d = qun_founier - qun_acquit;
        diff.setText(Methode.DoubleFormat(d) + "");

    }

    @FXML
    private void quitter(ActionEvent event) {
        new ShowPane().showOperationAchat();
        setnull();

    }

    @FXML
    private void sauvgarder(ActionEvent event) {
        String num = numero.getText();
        String Q_acquit = Q_Acquit.getText();
        String Q_four = this.Q_fournie.getText();
        double poidCamion = Double.parseDouble(poidcamion.getText());
        String tickit = numerotickit.getText();

        if (num.isEmpty() || Q_acquit.isEmpty() || Q_four.isEmpty() || poidCamion == 0 || tickit.isEmpty()) {
            Notification.champVideNotification();
        } else {
            addAchat();
            Notification.Addnotification();
            new ShowPane().showListAchat();
            setnull();

        }

    }

    private void setnull() {

        camion = null;
        chauffeur = null;
        ble = null;
        dock = null;

    }

}
