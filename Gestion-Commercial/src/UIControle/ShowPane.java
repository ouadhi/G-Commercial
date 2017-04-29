package UIControle;

import CommercialeControles.Home2FXMLController;
import CommercialeControles.HomeFXMLController;
import Conroles.MeunFXMLController;
import Conroles.Users_ListController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ShowPane {

      public void showRole() {
        try {
            FXMLLoader loader = new FXMLLoader();

            AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/RoleFXML.fxml"));

            Conroles.AdminFXMLController.rootp.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showUserList() {
        try {
            FXMLLoader loader = new FXMLLoader();

            AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/Users_List.fxml"));

            Conroles.AdminFXMLController.rootp.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(Users_ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showChauffeur() {

        String url = "/CommercialeView/Chauffeur/ChauffeurView.fxml";

        show(url, Home2FXMLController.workespacepane);

    }

    public void showCamion() {

        String url = "/CommercialeView/Camion/CamionView.fxml";

        show(url, Home2FXMLController.workespacepane);

    }

    public void showClient() {
        
        String url = ViewUrl.ClientView;

        show(url, Home2FXMLController.workespacepane);
    }

    public void showProduit() {

        show(ViewUrl.ProduitList, Home2FXMLController.workespacepane);
    }

    public void showBle() {

        show(ViewUrl.bleview, Home2FXMLController.workespacepane);
    }

    public void showDock() {

        show(ViewUrl.DockList, Home2FXMLController.workespacepane);
    }
    
    public void showOperationAchat () {
         show(ViewUrl.operationAchat, Home2FXMLController.workespacepane);
    }
    
    public void showListAchat() {
         show(ViewUrl.ListAchats, Home2FXMLController.workespacepane);
    }
    
    


    private TranslateTransition transitionout(AnchorPane node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setFromX(0);
        transition.setToX(2000);
        transition.setDuration(Duration.millis(100));
        transition.setDelay(Duration.millis(100));
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setCycleCount(1);

        return transition;
    }

    private TranslateTransition transitionIn(AnchorPane node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setFromX(2000);
        transition.setToX(0);
        transition.setDuration(Duration.millis(100));
        transition.setDelay(Duration.millis(100));
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setCycleCount(1);

        return transition;

    }

    private void show(String fileIn, AnchorPane workspace) {

        TranslateTransition trans1 = transitionout(workspace);
        trans1.play();

        trans1.setOnFinished(e -> {

            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource(fileIn));
                workspace.getChildren().setAll(root);
                TranslateTransition tran2 = transitionIn(workspace);
                tran2.play();
            } catch (IOException ex) {
                Logger.getLogger(MeunFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

}
