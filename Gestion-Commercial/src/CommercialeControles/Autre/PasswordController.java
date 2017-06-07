package CommercialeControles.Autre;

import UIControle.Methode;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.User;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PasswordController implements Initializable {

    @FXML
    private JFXPasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void valider(ActionEvent event) {
        try {
            if (password.getText().equals(User.getUserConnected().getPassword())) {

                AnchorPane pane = FXMLLoader.load(getClass().getResource(ViewUrl.GestionUtilisateur));
                Scene scene = new Scene(pane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                
                cancel(event);
            }

        } catch (IOException ex) {
            Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Methode.getStage(event).close();
    }

}
