package CommercialeControles.OperationAchat;

import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Achat;
import com.gestionCommerciale.Models.AchatQueries;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

public class FiltreDateController implements Initializable {

    @FXML
    private JFXListView<AchatCell> listeAchats;
    @FXML
    private JFXDatePicker dtpStart;
    @FXML
    private JFXDatePicker dtpEnd;
    @FXML
    private Label todayAchat;
    @FXML
    private Label todayquantite;
    @FXML
    private ImageView close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Achat> achatList = AchatQueries.getAchatsByDate(new Date() ) ; 

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }
        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
        setInfo(achatList);
    }

    @FXML
    private void showAchatSlide(MouseEvent event) {
    }

    @FXML
    private void valider(ActionEvent event) {
             getAchats();
    }

    @FXML
    private void print(ActionEvent event) {
    }

    private void getAchats() {

        Date start = Date.from(dtpStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(dtpEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        List<Achat> achatList = AchatQueries.RechercheParDate(start, end);

        List<AchatCell> list = new ArrayList<>();
        for (int i = 0; i < achatList.size(); i++) {
            list.add(new AchatCell(achatList.get(i)));
        }
        ObservableList<AchatCell> myObservableList = FXCollections.observableList(list);
        listeAchats.setItems(myObservableList);
         setInfo(achatList);
    }
    
    public void  setInfo(List<Achat>  liste  ) {
        double somme  = 0  ; 
        
        for (Achat achat : liste) {
            somme += achat.getQuantiteFour() ; 
        }
        
        todayAchat.setText(liste.size()+"");
        todayquantite.setText(Methode.DoubleFormat(somme));
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
        
    }
}
