
package UIControle;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Methode {
    
    
    public static Stage getStage  (ActionEvent event) {
        
        return (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    }
    
    
     public static Stage getStageMouses  (MouseEvent event) {
        
        return (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    }
     
     public static void setOnlyNumbre (JFXTextField field) {
         
         field.textProperty().addListener(new ChangeListener<String>() {
             
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    field.setText(oldValue);
                }
                
            }
        });
         
     }
    
    
}
