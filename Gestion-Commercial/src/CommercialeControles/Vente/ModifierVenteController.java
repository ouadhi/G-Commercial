
package CommercialeControles.Vente;

import CommercialeControles.Client.ClienCell;
import CommercialeControles.OperationAchat.CamionListeH;
import CommercialeControles.OperationAchat.ChauffeurListH;
import UIControle.Methode;
import UIControle.StageDialog;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;


public class ModifierVenteController implements Initializable {

    @FXML
    private JFXTextField montantFinal;
    @FXML
    private JFXTextField montant1;
    @FXML
    private ImageView clienticon;
    @FXML
    private ImageView chauffeuricon;
    @FXML
    private ImageView camionIcon;
    @FXML
    private ImageView produitIcon;

    StageDialog dialog  ; 
    private Image view = new Image(getClass().getResourceAsStream("/icons/preview.png"));
    private Image viewHover = new Image(getClass().getResourceAsStream("/icons/previewGreen.png"));
     PopOver popup;
     Facture  facture  ; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

     @FXML
    private void clientIN(MouseEvent event) {
         ClienCell  c  = new   ClienCell(facture.getClient()) ; 
        clienticon.setImage(viewHover);
        popup.setContentNode(c);
        popup.show(clienticon);
    }

    @FXML
    private void chauffeurOUT(MouseEvent event) {
        popup.hide();
        chauffeuricon.setImage(view);
    }

    @FXML
    private void chauffeurIN(MouseEvent event) {
        chauffeuricon.setImage(viewHover);
        ChauffeurListH ch = new ChauffeurListH(facture.getChauffeur());
        popup.setContentNode(ch);
        popup.show(chauffeuricon);
    }

    @FXML
    private void camionOUT(MouseEvent event) {
        popup.hide();
        camionIcon.setImage(view);
    }

    @FXML
    private void camionIN(MouseEvent event) {
        camionIcon.setImage(viewHover);
        CamionListeH ch = new CamionListeH(facture.getCamion());
        popup.setContentNode(ch);
        popup.show(camionIcon);
    }

    @FXML
    private void produitOUT(MouseEvent event) {
        dialog.close();
        produitIcon.setImage(view);
    }

    @FXML
    private void produitIN(MouseEvent event) throws IOException {
        produitIcon.setImage(viewHover);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ViewUrl.produitfacture));
        loader.load();

        ListeProduitVenteController Modifier = loader.getController();
        Modifier.setData(facture);

        AnchorPane root = loader.getRoot();

        dialog = new StageDialog(Methode.getStageMouses(event), root);
        dialog.show();
        
        
    }

    private void intpop() {
        popup = new PopOver();
        popup.setCornerRadius(4);
        popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
    }
    
    
    public  void  setData(Facture facture  ) {
        this.facture  = facture  ; 
        montantFinal.setText(this.facture.getMontantFinal()+"");
        montant1.setText(this.facture.getDate().toString());
        intpop();
    }

     @FXML
    private void clientOUT(MouseEvent event) {
        clienticon.setImage(view);
    }

    @FXML
    private void close(MouseEvent event) {
        Methode.getStageMouses(event).close();
    }
    
}
