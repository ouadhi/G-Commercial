
package CommercialeControles.OperationAchat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import CommercialeControles.Dock.ShowDockSlideController;
import UIControle.Transition;
import UIControle.ViewUrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class AchatSlidController implements Initializable {

	@FXML
	private JFXButton laste;
	@FXML
	private JFXButton next;
	@FXML
	private AnchorPane pane;

	private JFXListView<AchatCell> liste;
	private int i = 0;

	public AnchorPane getAchat(int index) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ViewUrl.ModifierAchat));
			loader.load();

			AchatCell achatC = liste.getItems().get(index);

			ModifierAchatController modification = loader.getController();
			// edited
			modification.setData(achatC.achat);

			AnchorPane pane = loader.getRoot();

			return pane;
		} catch (IOException ex) {
			Logger.getLogger(ShowDockSlideController.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void lastAchat(ActionEvent event) {
		if (i > 0) {
			--i;

			Transition transition = new Transition(pane, getAchat(i), 2000, 0, -2000);
			transition.show();

			if (i == 0) {
				laste.setDisable(true);
			} else {
				laste.setDisable(false);
				next.setDisable(false);
			}
		}
	}

	@FXML
	private void nextAchat(ActionEvent event) {
		if (i < liste.getItems().size()) {
			i++;
			Transition transition = new Transition(pane, getAchat(i), 2000, 0, -2000);
			transition.show();

			if (i == liste.getItems().size() - 1) {
				next.setDisable(true);
			} else {
				next.setDisable(false);
				laste.setDisable(false);
			}
		}

	}

	public void setData(JFXListView<AchatCell> liste, int selectedIndex) {
		this.liste = liste;
		i = selectedIndex;
		pane.getChildren().setAll(getAchat(selectedIndex));
	}

}
