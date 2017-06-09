package CommercialeControles.OperationAchat;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.AchatQueries;
import com.gestionCommerciale.Models.AnneeQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;

public class FinOperationController implements Initializable {
    
    public static DockListeH dock = null;
    public static CamionListeH camion = null;
    public static ChauffeurListH chauffeur = null;
    public static BleListeH ble = null;
    
    private static String imgOk = "/icons/OkGreen.png";
    private static String imgfaux = "/icons/cancel.png";
    
    private Image view = new Image(getClass().getResourceAsStream("/icons/preview.png"));
    private Image viewHover = new Image(getClass().getResourceAsStream("/icons/previewGreen.png"));
    
    @FXML
    private ImageView statechauffeur;
    static ImageView statech;
    
    @FXML
    private ImageView statecamion;
    static ImageView statecam;
    
    @FXML
    private ImageView stateble;
    static ImageView stateb;
    
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
    static ImageView statDock;
    @FXML
    private JFXTextField numerotickit;
    private JFXTextField numeroBon;
    @FXML
    private JFXTextField poidTotal;
    @FXML
    private JFXTextField poidcamion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statech = this.statechauffeur;
        statecam = statecamion;
        stateb = stateble;
        statDock = statedock;
        Methode.setOnlyDouble(poidTotal, 10);
        Methode.setSelectedMouseClick(poidTotal);
        Q_fournie.setEditable(false);
        Q_fournie.setText("0.00");
        poidTotal.setText("0.00");
        Q_Acquit.setText("0.00");
        Q_Acquit.setOnKeyReleased(e->{
            poidtotalkey(e);
        });
        Methode.setOnlyInteger(numero, 10);
        Methode.setOnlyInteger(poidcamion, 10);
        Methode.setOnlyInteger(numerotickit, 10);
        Methode.setOnlyDouble(Q_Acquit, 10);
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
    
    @FXML
    private void CloseInforChauffeur(MouseEvent event) {
        popup.hide();
        infochaffeur.setImage(view);
    }
    
    @FXML
    private void opneInfoChauufeur(MouseEvent event) {
        infochaffeur.setImage(viewHover);
        popup.setContentNode(chauffeur);
        popup.show(infochaffeur);
        
    }
    
    @FXML
    private void CloseInfoCamion(MouseEvent event) {
        infocamion.setImage(view);
        popup.hide();
    }
    
    @FXML
    private void opneInfoCamion(MouseEvent event) {
        infocamion.setImage(viewHover);
        popup.setContentNode(camion);
        popup.show(infocamion);
    }
    
    @FXML
    private void opneInfoBle(MouseEvent event) {
        infoBle.setImage(viewHover);
        popup.setContentNode(ble);
        popup.show(infoBle);
    }
    
    @FXML
    private void CloseinfoBle(MouseEvent event) {
        infoBle.setImage(view);
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
        //back
//        if (d == 0) {
//            diffIcon.setImage(new Image(imgOk));
//        } else {
//            diffIcon.setImage(new Image(imgfaux));
//        }
    }
    
    @FXML
    private void sauvgarder(ActionEvent event) {
        String num = numero.getText();
        String Q_acquit = Q_Acquit.getText();
        String Q_four = this.Q_fournie.getText();
        String bon = numeroBon.getText();
        String tickit = numerotickit.getText();
        
        if (num.isEmpty() || Q_acquit.isEmpty() || Q_four.isEmpty() || bon.isEmpty() || tickit.isEmpty()) {
            Notification.champVideNotification();
        } else {
            addAchat();
            Notification.Addnotification();
            new ShowPane().showListAchat();
            setnull();
            
        }
        
    }
    
    @FXML
    private void quitter(ActionEvent event) {
        new ShowPane().showOperationAchat();
        setnull();
        
    }
    
    private void setnull() {
        
        camion = null;
        chauffeur = null;
        ble = null;
        dock = null;
        
    }
    
    @FXML
    private void CloseInforDock(MouseEvent event) {
        infodock.setImage(view);
        popup.hide();
    }
    
    @FXML
    private void opneInfoDock(MouseEvent event) {
        infodock.setImage(viewHover);
        popup.setContentNode(dock);
        popup.show(infodock);
    }
    
    public void addAchat() {
        String numTiquetVal = numerotickit.getText();
        String numAcqtVal = numero.getText();
        double qActVal = Double.parseDouble(Q_Acquit.getText());
        double qFourVal = Double.parseDouble(Q_fournie.getText());
        double diffVal = Double.parseDouble("0");//modification
        Date date = java.sql.Date.valueOf(this.date.getValue());
        
        String numBonVal = numeroBon.getText();
        Achat achat = new Achat(numTiquetVal, numAcqtVal, qActVal,
                qFourVal, diffVal, date, numBonVal, AnneeQueries.getSelected());
        achat.setCamion(camion.getCamion());
        achat.setChauffeur(chauffeur.getChauffeur());
        achat.setDock(dock.getDock());
        achat.setBle(ble.getBle());
        AchatQueries.SaveOrUpdate(achat);
    }
    
    public static void ClearVar() {
        dock = null;
        camion = null;
        chauffeur = null;
        ble = null;
    }

    //back
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
        
        double poidTotalVal = Double.parseDouble(poidTotal.getText());
        double dif = poidTotalVal - camion.getCamion().getPoid();
        Q_fournie.setText(Methode.DoubleFormat(dif) + "");
        
        double qun_acquit = Double.parseDouble(Q_Acquit.getText());
        double qun_founier = Double.parseDouble(Q_fournie.getText());
        double d = qun_founier - qun_acquit ;
        diff.setText(Methode.DoubleFormat(d) + "");
        
    }
    
}
