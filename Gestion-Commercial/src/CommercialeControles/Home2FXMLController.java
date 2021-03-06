package CommercialeControles;
//

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import UIControle.Methode;
import UIControle.ShowPane;
import UIControle.ViewUrl;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Home2FXMLController implements Initializable {

	public static JFXButton bttn_menu;
	public static AnchorPane menup;

	public static AnchorPane workespacepane;

	private boolean smalShow;

	@FXML
	private JFXDrawer main_menu;

	@FXML
	private JFXButton menu_button;

	@FXML
	private AnchorPane panel_menu;
	@FXML
	private JFXHamburger hamburguerbutton;

	@FXML
	private AnchorPane menu;
	@FXML
	private AnchorPane workespace;
	@FXML
	private ImageView iconMore;

	HamburgerBackArrowBasicTransition transition;

	private void changeMenutoSmall() {
		AnchorPane pane = (AnchorPane) menu.getChildren().get(0);
		VBox box = (VBox) pane.getChildren().get(0);

		for (Node node : box.getChildren()) {
			if (node instanceof JFXButton) {
				((JFXButton) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				((JFXButton) node).setAlignment(Pos.CENTER_RIGHT);
			}
		}

	}

	private void changeMenutoStrong() {
		AnchorPane pane = (AnchorPane) menu.getChildren().get(0);
		VBox box = (VBox) pane.getChildren().get(0);
		for (Node node : box.getChildren()) {
			if (node instanceof JFXButton) {
				((JFXButton) node).setContentDisplay(ContentDisplay.LEFT);
				((JFXButton) node).setAlignment(Pos.BASELINE_LEFT);
			}
		}

	}

	@FXML
	private void closemenu(MouseEvent event) {
		if (!smalShow) {
			transitionout(panel_menu).play();
			changeMenutoSmall();
			smalShow = true;
			transition.setRate(transition.getRate() * -1);
			transition.play();
		}

		if (main_menu.isShown()) {
                    
			main_menu.close();
		}
	}

	public void hummberguer_transaction() {

		transition.setRate(-1);
		hamburguerbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			if (smalShow) {
				transitionIn(panel_menu).play();
				changeMenutoStrong();
				smalShow = false;
			} else {
				transitionout(panel_menu).play();
				changeMenutoSmall();
				smalShow = true;
			}

		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			transition = new HamburgerBackArrowBasicTransition(hamburguerbutton);

			workespacepane = workespace;
			menup = menu;
			bttn_menu = menu_button;
			smalShow = true;

			AnchorPane menu_draw = FXMLLoader.load(getClass().getResource("/CommercialeView/MainMenu.fxml"));
			main_menu.setSidePane(menu_draw);

			hummberguer_transaction();
			new ShowPane().showChauffeur();

		} catch (IOException ex) {
			System.out.println("e" + ex);
		}
	}

	private void InMenu(MouseEvent event) {
		transitionIn(panel_menu).play();
		changeMenutoStrong();
	}

	private void OutManu(MouseEvent event) {
		transitionout(panel_menu).play();
		changeMenutoSmall();
	}

	public void setMenu(AnchorPane Menu) {
		menu.getChildren().setAll(Menu);
		smalShow = true;
		menup = menu;
		changeMenutoSmall();
	}

	@FXML
	private void showmenu(ActionEvent event) {

		if (main_menu.isShown()) {
			main_menu.close();
		} else {
			main_menu.open();
		}

	}

	@FXML
	private void showMore(MouseEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(ViewUrl.moreMenu));
		loader.load();

		MoreMenuController control = loader.getController();
		control.setStage(Methode.getStageMouses(event));

		AnchorPane root = loader.getRoot();

		PopOver popup = new PopOver();
		popup.setContentNode(root);
		popup.setCornerRadius(4);

		popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
		popup.show(iconMore);

	}

	private TranslateTransition transitionIn(AnchorPane node) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(node);
		transition.setFromX(-64);
		transition.setToX(64);
		transition.setDuration(Duration.millis(100));
		transition.setDelay(Duration.millis(100));
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setCycleCount(1);

		return transition;
	}

	private TranslateTransition transitionout(AnchorPane node) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(node);
		transition.setFromX(30);
		transition.setToX(0);
		transition.setDuration(Duration.millis(100));
		transition.setDelay(Duration.millis(100));
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setCycleCount(1);

		return transition;
	}

}
