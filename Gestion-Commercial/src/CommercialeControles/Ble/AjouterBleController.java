package CommercialeControles.Ble;
import CommercialeControles.OperationAchat.BleListeH;
import CommercialeControles.OperationAchat.SelectionnerBleController;
import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Ble;
import com.gestionCommerciale.Models.BleQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tray.notification.NotificationType;
public class AjouterBleController implements Initializable {
    @FXML
    private ImageView close;
    @FXML
    private JFXButton addbttn;
    @FXML
    private JFXButton cancelbttn;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField quntite;
    @FXML
    private JFXTextField prix;
    @FXML
    private Label savelabel;
    BleQueries queries = new BleQueries();
    Label total ; 
    
    JFXListView<BelCell> listeBle = null; 
    JFXListView<BleListeH> listeBleH = null  ; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyInteger(quntite, 16);
        Methode.setOnlyFloat(prix, 16);
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = Methode.getStageMouses(event);
        stage.close();
    }
    @FXML
    private void saveble(ActionEvent event) {
        String codeval = this.code.getText();
        String quantiteval = this.quntite.getText();
        String prixval = this.prix.getText();
        if (codeval.isEmpty() || quantiteval.isEmpty() || prixval.isEmpty()) {
            Notification.notif(NotificationType.ERROR, "Vérification", "Vérifier que tout les champs sont remplis!");
        } else {
            if (queries.getBle(codeval) != null) {
                //notification for already exists
                Notification.error("Ce ble est exite déja!");
            } else {
                try {
                    Ble ble = new Ble(Integer.parseInt(codeval), Integer.parseInt(quantiteval), Double.parseDouble(prixval));
                    queries.SaveOrUpdate(ble);
                    Notification.Addnotification();
                    closestage(event);
                    savelabel.setVisible(true);
                    if (listeBle == null) {
                        refresheH();
                    } else {
                        refresheV();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void closestage(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
    public  void  setData(JFXListView<BelCell> listeBle , Label total ) {
        this.listeBle  = listeBle  ; 
        this.total = total  ; 
        
    }
    
    public  void  setData2(JFXListView<BleListeH> listeBle ) {
        this.listeBleH = listeBle  ; 
        
        
    }
    
    
    public void refresheV (){
        
         List<Ble> listBlesDB = queries.list();
        List<BelCell> list = new ArrayList<>();
        for (int i = 0; i < listBlesDB.size(); i++) {
            list.add(new BelCell(listBlesDB.get(i).getIdBle(), listBlesDB.get(i).getQte(),
                    listBlesDB.get(i).getPrix()
            ));
        }
        ObservableList<BelCell> myObservableList = FXCollections.observableList(list);
        listeBle.setItems(myObservableList);
        listeBle.setExpanded(true);
        total.setText(Integer.toString(listeBle.getItems().size()));
             
    }
    
    public void refresheH (){
        listeBleH.getItems().clear();
        List<Ble> listBlesDB = queries.list();
        List<BleListeH> list = new ArrayList<>();
        
        for (int i = 0; i < listBlesDB.size(); i++) {
           list.add(new BleListeH(listBlesDB.get(i), listBlesDB.get(i).getQte())) ; 
        }
        
        BleListeH ch  = new BleListeH(listeBleH) ; 
        list.add(ch) ; 
        ObservableList<BleListeH> myObservableList = FXCollections.observableList(list);
       listeBleH.setItems(myObservableList);
        
    }
}
