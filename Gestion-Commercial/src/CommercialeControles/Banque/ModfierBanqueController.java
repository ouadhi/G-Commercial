package CommercialeControles.Banque;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.gestionCommerciale.HibernateSchema.Banque;
import com.gestionCommerciale.Models.BanqueQueries;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class ModfierBanqueController implements Initializable {
    
    @FXML
    private JFXTextField nombanque;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField NumCompte;
    @FXML
    private JFXTextField telephone;
    
    private Banque banque;    
    
    private BanqueQueries querie = new BanqueQueries();    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Methode.setOnlyInteger(NumCompte, 30);
        Methode.setOnlyInteger(NumCompte, 12);
        
    }    
    
    @FXML
    private void quitter(MouseEvent event) {
        Methode.getStageMouses(event).close();        
    }
    
    @FXML
    private void save(ActionEvent event) {
        String nom = nombanque.getText();        
        String adresse = this.adresse.getText();        
        String compte = NumCompte.getText();        
        String tele = telephone.getText();        
        
        if (nom.isEmpty() || adresse.isEmpty() || compte.isEmpty() || tele.isEmpty()) {
            Notification.champVideNotification();            
        } else {
            
            banque.setNom(nom);
            banque.setCompte(compte);
            banque.setAddress(adresse);
            banque.setTelephone(tele);            
            
            querie.SaveOrUpdate(banque);
            Notification.Addnotification();            
            new ShowPane().showBanque();
            close(event);
        }
    }
    
    @FXML
    private void close(ActionEvent event) {
        Methode.getStage(event).close();
    }
    
    public void setData(Banque banque) {
        this.banque = banque;    
        
        this.NumCompte.setText(banque.getCompte());
        this.adresse.setText(this.banque.getAddress());
        this.nombanque.setText(this.banque.getNom());
        this.telephone.setText(this.banque.getTelephone());
    
    }
    
}
