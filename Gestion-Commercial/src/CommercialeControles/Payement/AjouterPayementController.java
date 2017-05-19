
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class AjouterPayementController implements Initializable {

    @FXML
    private Text Nfacture;
    @FXML
    private JFXDatePicker datepayment;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXTextField montont;
    ArrayList<String> Types = new ArrayList<>()  ; 
    
    private int Numero_facture  ; 
    private JFXListView<PayementCell> listepayement ; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }   

   
    


    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }

    @FXML
    private void save(ActionEvent event) {
        Notification.Addnotification();
        AfficheListePayement();
        close(event);
    }
    
    public void setdata (int facutre , JFXListView<PayementCell> listepayement) {
        this.Numero_facture  = facutre ; 
        this.listepayement  =  listepayement ; 
        
        Nfacture.setText(Integer.toString(facutre));
        
        Types.add("Cache") ; 
        Types.add("Espace") ;
        ObservableList<String> liste = FXCollections.observableList(Types);
        type.setItems(liste);
        
        datepayment.setTime(LocalTime.now());
        
        Methode.setOnlyFloat(montont, 8);
    }
    
    private void AfficheListePayement() {
        listepayement.getItems().clear(); 
        List<PayementCell> list = new ArrayList<>();
       
        for (int i = 0; i < 5; i++) {
            
            Payment  payement  =  new Payment("Cache", 4000, new Date()) ;
            PayementCell cell  = new PayementCell(payement) ;
            list.add(cell) ;      
            
        }
        
        ObservableList<PayementCell> myObservableList = FXCollections.observableList(list);
        listepayement.setItems(myObservableList);
    }    

    

    @FXML
    private void quitter(MouseEvent event) {
         Methode.getStageMouses(event).close();
    }
    
}
