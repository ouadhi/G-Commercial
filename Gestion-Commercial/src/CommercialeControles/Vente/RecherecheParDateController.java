
package CommercialeControles.Vente;

import UIControle.Methode;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
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


public class RecherecheParDateController implements Initializable {

    @FXML
    private JFXListView<VenteCell> listevente;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void valider(ActionEvent event) {
        Date  from  = new Date(2017,06,01)  ; 
        Date  to  = new Date(2017, 06, 20) ; 
        
        
//        List<Facture> factureList = FactureQueries.getFacturesListByClientIdDates(new Date(from.getTime()), new Date(to.getTime()));
        List<Facture> factureList = FactureQueries.getFacturesListByClientIdDates(from, to);
        List<VenteCell> list = new ArrayList<>();
        for (int i = 0; i < factureList.size(); i++) {
            list.add(new VenteCell(factureList.get(i)));
        }

        ObservableList<VenteCell> myObservableList = FXCollections.observableList(list);
        listevente.setItems(myObservableList);
        listevente.setExpanded(true);

    }

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }
    
}
