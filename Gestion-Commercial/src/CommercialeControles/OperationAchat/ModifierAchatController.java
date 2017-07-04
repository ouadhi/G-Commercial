package CommercialeControles.OperationAchat;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.gestionCommerciale.HibernateSchema.Achat;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import UIControle.Methode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ModifierAchatController implements Initializable {

	@FXML
	private ImageView close;
	@FXML
	private JFXTextField numero;
	@FXML
	private JFXDatePicker date;
	@FXML
	private JFXTextField Q_Acquit;
	@FXML
	private JFXTextField Q_fournie;
	@FXML
	private ImageView camionicon;
	@FXML
	private ImageView chauffeuricon;

	PopOver pop = new PopOver();

	Achat achat;

	ChauffeurListH ch;
	CamionListeH camionH;
	BleListeH bleH;

	String camiongreen = "/icons/CamioGreen.png";
	String camionGry = "/icons/CamionGry.png";

	String ChafffeurGreen = "/icons/ChaffeurGreen.png";
	String ChafffeurGry = "/icons/ChauffeurGry.png";

	String BleGreen = "/icons/Blegreen.png";
	String BleGry = "/icons/BleGry.png";
	@FXML
	private ImageView bleicon;

	@FXML
	private void bleIN(MouseEvent event) {
		bleicon.setImage(new Image(getClass().getResourceAsStream(BleGreen)));
		pop.setContentNode(bleH);
		pop.show(bleicon);
	}

	@FXML
	private void bleOUT(MouseEvent event) {
		pop.hide();
		bleicon.setImage(new Image(getClass().getResourceAsStream(BleGry)));
	}

	@FXML
	private void camionIN(MouseEvent event) {
		camionicon.setImage(new Image(getClass().getResourceAsStream(camiongreen)));
		pop.setContentNode(camionH);
		pop.show(camionicon);
	}

	@FXML
	private void camionOut(MouseEvent event) {

		pop.hide();
		camionicon.setImage(new Image(getClass().getResourceAsStream(camionGry)));
	}

	@FXML
	private void chaffeurIN(MouseEvent event) {
		chauffeuricon.setImage(new Image(getClass().getResourceAsStream(ChafffeurGreen)));
		pop.setContentNode(ch);
		pop.show(chauffeuricon);
	}

	@FXML
	private void chaffeurOUT(MouseEvent event) {

		pop.hide();
		chauffeuricon.setImage(new Image(getClass().getResourceAsStream(ChafffeurGry)));
	}

	@FXML
	private void close(MouseEvent event) {
		Methode.getStageMouses(event).close();
	}

	@FXML
	private void founiePressed(KeyEvent event) {
	}

	@FXML
	private void FournirReleased(KeyEvent event) {
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void setData(Achat achat) {
		this.achat = achat;
		numero.setText(achat.getNumAcqt());
		Q_Acquit.setText(Methode.DoubleFormat(this.achat.getQuantiteAcqt() )+"");
		Q_fournie.setText(Methode.DoubleFormat(this.achat.getQuantiteFour())+"" );
		date.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(this.achat.getDateAcqt())));

		ch = new ChauffeurListH(this.achat.getChauffeur());
		camionH = new CamionListeH(this.achat.getCamion());
		bleH = new BleListeH(this.achat.getBle(), 0);

		pop.setCornerRadius(4);
		pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);

	}

}
