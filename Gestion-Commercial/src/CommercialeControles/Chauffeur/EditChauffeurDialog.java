package CommercialeControles.Chauffeur;

import UIControle.Methode;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditChauffeurDialog extends Stage {

	public EditChauffeurDialog(Stage owner, ChauffeurCell box) {
		super();
		try {
			initOwner(owner);
			initModality(Modality.APPLICATION_MODAL);
			setResizable(false);
			initStyle(StageStyle.UNDECORATED);

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/CommercialeView/Chauffeur/ModificationChauffeurView.fxml"));
			loader.load();

			ModificationChauffeurController modification = loader.getController();
			// modification.setData(box.nom, "112", box.telephone, box.voyage);
			// back
			System.out.println("this is editchauffeurdialog edit");
			ShowChauffeurController.setChauffeur(box.getChauffeur());

			modification.setData(box.getChauffeur());
			AnchorPane pane = loader.getRoot();
                        Methode.moveFocus(pane);

			Scene scene = new Scene(pane, 614, 475);
			setScene(scene);

		} catch (IOException ex) {
			Logger.getLogger(AjouterChauffeuerDialog.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
