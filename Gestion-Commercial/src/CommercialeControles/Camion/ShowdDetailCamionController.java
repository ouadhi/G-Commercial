package CommercialeControles.Camion;

import UIControle.Methode;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestionCommerciale.HibernateSchema.Camion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import UIControle.Transition;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ShowdDetailCamionController implements Initializable {

	private static Camion camion;
	public static Camion getCamion() {
		return camion;
	}
	public static void setCamion(Camion camion) {
		ShowdDetailCamionController.camion = camion;
	}
	@FXML
	private JFXButton precedent;
	@FXML
	private JFXButton suivant;

	@FXML
	private AnchorPane PaneMain;

	private JFXListView<CamionCell> liste;

	private int i = 0;

	public AnchorPane getcamion(int id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.modifierCamion));
			loader.load();

			CamionCell camionCell = liste.getItems().get(id);
			ModifierCamionController modification = loader.getController();
			// modification.setData(camion.id, camion.marque, camion.matricule,
			// camion.taille);
			modification.setData(camionCell.getCamion());
			AnchorPane pane = loader.getRoot();
                         Methode.moveFocus(pane);
			return pane;
		} catch (IOException ex) {
			Logger.getLogger(ShowdDetailCamionController.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
            
	}

	@FXML
	private void precedent(ActionEvent event) {
		if (i > 0) {
			--i;
			Transition transition = new Transition(PaneMain, getcamion(i), 2000, 0, -2000);
			transition.show();
			if (i == 0) {
				precedent.setDisable(true);
			} else {
				suivant.setDisable(false);
				precedent.setDisable(false);
			}
		}
	}

	public void setListechauffeur(JFXListView<CamionCell> liste, int idItemSelceted) {
		this.liste = liste;
		i = idItemSelceted;
		PaneMain.getChildren().setAll(getcamion(i));
	}

	@FXML
	private void suivant(ActionEvent event) {
		if (i < liste.getItems().size()) {
			i++;
			Transition transition = new Transition(PaneMain, getcamion(i), -2000, 0, 2000);
			transition.show();
			if (i == liste.getItems().size() - 1) {
				suivant.setDisable(true);
			} else {
				suivant.setDisable(false);
				precedent.setDisable(false);
			}
		}
	}

}
