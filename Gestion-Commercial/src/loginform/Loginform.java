
package loginform;

import UIControle.ViewUrl;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Loginform extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(ViewUrl.ProduitList));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        transitionIN(root); 
        
    }

    public void transitionIN(Parent root) {
        FadeTransition  fadeIn   = new FadeTransition(Duration.seconds(3), root) ;
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        
        fadeIn.play();
    }
    
    public void transitionOut(Parent root) {
        FadeTransition  fadeIn   = new FadeTransition(Duration.seconds(3), root) ;
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0);
        fadeIn.setCycleCount(1);
        
        fadeIn.play();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
