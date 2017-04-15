
package UIControle;

import Conroles.MeunFXMLController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class Transition {
      private  AnchorPane root  ,nouveau  ;  
      private  int rigth ,center, left  ; 

    public Transition(AnchorPane root, AnchorPane nouveau, int rigth, int center, int left) {
        this.root = root;
        this.nouveau = nouveau;
        this.rigth = rigth;
        this.center = center;
        this.left = left;
    }
 
      
     private TranslateTransition transitionout(AnchorPane node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setFromX(center);
        transition.setToX(rigth);
        transition.setDuration(Duration.millis(100));
        transition.setDelay(Duration.millis(100));
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setCycleCount(1);

        return transition;
    }

    private TranslateTransition transitionIn(AnchorPane node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setFromX(left);
        transition.setToX(center);
        transition.setDuration(Duration.millis(100));
        transition.setDelay(Duration.millis(100));
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setCycleCount(1);

        return transition;

    }

    public void show() {

        TranslateTransition trans1 = transitionout(root);
        trans1.play();

        trans1.setOnFinished(e -> {

            root.getChildren().setAll(nouveau);
            TranslateTransition tran2 = transitionIn(root);
            tran2.play();

        });

    }
}
