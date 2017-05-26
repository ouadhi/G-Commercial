
package UIControle;

import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
         
     }     public static void setOnlyFloat (JFXTextField field,int max) {
         
         field.textProperty().addListener(new ChangeListener<String>() {
             
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (!newValue.matches("\\d{0,"+max+"}([\\.]\\d{0,2})?")) {
                    field.setText(oldValue);
                }
                
            }
        });
         
     }
     public static void setOnlyInteger (JFXTextField field,int max) {
         
         field.textProperty().addListener(new ChangeListener<String>() {
             
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (!newValue.matches("\\d{0,"+max+"}?")) {
                    field.setText(oldValue);
                }
                
            }
        });
         
     }
            public static Double DoubleFormat(double f) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    
}
