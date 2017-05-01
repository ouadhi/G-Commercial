package CommercialeControles.OperationAchat;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;

public class ModifierAchatController implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private JFXTextField numero;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField Q_Acquit;
    @FXML
    private JFXTextField Q_fournie;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton annuler;
    @FXML
    private ImageView camionicon;
    @FXML
    private ImageView chauffeuricon;

    PopOver pop = new PopOver();

    ChauffeurListH ch;
    CamionListeH camionH;

    String camiongreen = "/icons/CamioGreen.png";
    String camionGry = "/icons/CamionGry.png";
    
    String ChafffeurGreen = "/icons/ChaffeurGreen.png" ;
            String ChafffeurGry= "/icons/ChauffeurGry.png" ; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }

    @FXML
    private void founiePressed(KeyEvent event) {
    }

    @FXML
    private void FournirReleased(KeyEvent event) {
    }

    @FXML
    private void sauvgarder(ActionEvent event) {
        String num = numero.getText();
        String Q_acquit = Q_Acquit.getText();
        String Q_four = this.Q_fournie.getText();

        if (num.isEmpty() || Q_acquit.isEmpty() || Q_four.isEmpty()) {
            Notification.champVideNotification();
        } else {

            Notification.Updatenotification();
            new ShowPane().showListAchat();
           

        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        Methode.getStage(event).close();
    }

    public void setData(int numeroAquict) {
        numero.setText(Integer.toString(numeroAquict));

        ch = new ChauffeurListH("OUADHI", "Mohammed", "1234567");
        camionH = new CamionListeH("JMC", "1234567");

        pop.setCornerRadius(4);
        pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);

    }

    @FXML
    private void camionOut(MouseEvent event) {
        
        pop.hide();
        camionicon.setImage(new Image(getClass().getResourceAsStream(camionGry)));
    }

    @FXML
    private void camionIN(MouseEvent event) {
        camionicon.setImage(new Image(getClass().getResourceAsStream(camiongreen)));
        pop.setContentNode(camionH);
        pop.show(camionicon);
    }

    @FXML
    private void chaffeurOUT(MouseEvent event) {
        
        pop.hide();
        chauffeuricon.setImage(new Image(getClass().getResourceAsStream(ChafffeurGry)));
    }

    @FXML
    private void chaffeurIN(MouseEvent event) {
        chauffeuricon.setImage(new Image(getClass().getResourceAsStream(ChafffeurGreen)));
        pop.setContentNode(ch);
        pop.show(chauffeuricon);
    }

}
