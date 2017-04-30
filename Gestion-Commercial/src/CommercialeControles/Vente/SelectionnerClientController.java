
package CommercialeControles.Vente;

import CommercialeControles.Client.ClienCell;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.gestionCommerciale.HibernateSchema.Client;
import com.gestionCommerciale.Models.ClientQueries;


public class SelectionnerClientController implements Initializable {

    @FXML
    private JFXListView<ClienCell> ClientListe;
     private ClientQueries clientQueries = new ClientQueries();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Client> listClientsDB = clientQueries.list();
        List<ClienCell> list = new ArrayList<>();
        for (int i = 0; i < listClientsDB.size(); i++) {
            list.add(new ClienCell(listClientsDB.get(i).getId(),listClientsDB.get(i).getPrenom()+" "+listClientsDB.get(i).getName()
                    , listClientsDB.get(i).getTypeActivity()
                    , listClientsDB.get(i).getNumRegCom()
                   ));            
        }
        ObservableList<ClienCell> myObservableList = FXCollections.observableList(list);
        ClientListe.setItems(myObservableList);
        ClientListe.setExpanded(true);
        
    }    

    
}
