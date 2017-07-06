package CommercialeControles.Camion;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.gestionCommerciale.Models.CamionQueries;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import UIControle.Notification;
import UIControle.ShowPane;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifierCamionController implements Initializable {

	@FXML
	private JFXTextField nomchauffeur;
	@FXML
	private JFXButton savebttn;
	@FXML
	private JFXButton anullerbttn;
	@FXML
	private Label savelabel;
	@FXML
	private ImageView close;
	@FXML
	private JFXTextField codecamion;
	@FXML
	private JFXTextField matricule;
	@FXML
	private JFXTextField taillecamion;
	@FXML
	private JFXTextField PoisCamion;
	Camion camion;
	private JFXComboBox<String> typec;

	@FXML
	private void annuler(ActionEvent event) {
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
	}

	@FXML
	private void closewindow(MouseEvent event) {
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Methode.setOnlyDouble(PoisCamion, 5);
		Methode.SetUpper(codecamion, 10);
		Methode.SetUpper(matricule, 12);
		Methode.setSelectedMouseClick(PoisCamion);
		Methode.setSelectedMouseClick(codecamion);
		Methode.setSelectedMouseClick(matricule);
		// Methode.setSelectedMouseClick(type);
		Methode.setZeroRemoved(PoisCamion);
		Methode.setsizeString(taillecamion, 32);
                
	}

	@FXML
	private void sauvegarder(ActionEvent event) {
		String code = codecamion.getText();
		String matricule = this.matricule.getText();
		// double poid = Double.parseDouble(PoisCamion.getText());
		// String marque = camion.getMarque();
		String marque = taillecamion.getText();
		Optional<ButtonType> result = Notification.updateAlert().showAndWait();
		if (result.get() == ButtonType.OK) {
			if (code.isEmpty() || matricule.isEmpty() || marque.isEmpty()) {
				Notification.champVideNotification();
			} else {
				Camion camion = ShowdDetailCamionController.getCamion();
				camion.setCodeCamion(code);
				camion.setMatricule(matricule);
				// camion.setPoid(poid);
				camion.setMarque(marque);
				Camion c = CamionQueries.getCamionByCode(code);
				Camion c2 = CamionQueries.getCamionByMatricule(matricule);
				if (c != null && c.getId() != camion.getId()) {
					Notification.error("Ce Code existe d\u00E9ja!");
				} else if (c2 != null && c2.getId() != camion.getId()) {
					Notification.error("Cette matriquelle existe d\u00E9ja!");
				} else {
					CamionQueries.SaveOrUpdate(camion);
					Notification.Updatenotification();
					new ShowPane().showCamion();
					savelabel.setVisible(true);
					annuler(event);
				}
			}
		}
	}

	public void setData(Camion camion) {
		setType();
            
		this.camion = camion;
		codecamion.setText(camion.getCodeCamion());
		matricule.setText(camion.getMatricule());
		typec.setValue(camion.getType());
                
		// PoisCamion.setText(Double.toString(camion.getPoid()));
		taillecamion.setText(camion.getMarque());
	}
        
	public void setType() {
		typec.getItems().add("INTERNE");
		typec.getItems().add("EXTERNE");
	}
        
}
