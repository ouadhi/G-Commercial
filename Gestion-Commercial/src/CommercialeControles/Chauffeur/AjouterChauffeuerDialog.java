package CommercialeControles.Chauffeur;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AjouterChauffeuerDialog extends Stage {

	public AjouterChauffeuerDialog(Stage owner) {
		super();
		try {
			initOwner(owner);
			initModality(Modality.APPLICATION_MODAL);
			setResizable(false);
			initStyle(StageStyle.UNDECORATED);

			AnchorPane pane = FXMLLoader
					.load(getClass().getResource("/CommercialeView/Chauffeur/AjouterChauffeurView.fxml"));
			Scene scene = new Scene(pane, 614, 475);
			setScene(scene);

		} catch (IOException ex) {
			Logger.getLogger(AjouterChauffeuerDialog.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
