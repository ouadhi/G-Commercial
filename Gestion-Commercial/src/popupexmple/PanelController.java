/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupexmple;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class PanelController implements Initializable {

    @FXML
    private JFXButton utilisateurBttn;
    @FXML
    private JFXButton infobuttn;
    @FXML
    private JFXButton chagerButtn;
    @FXML
    private JFXButton exite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showgestion(ActionEvent event) {
    }

    @FXML
    private void showinfo(ActionEvent event) {
    }

    @FXML
    private void showLoginform(ActionEvent event) {
    }

    @FXML
    private void quitter(ActionEvent event) {
    }
    
}
