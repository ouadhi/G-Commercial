
package CommercialeControles.Statistique;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;


public class MenuViewStatisitqueController implements Initializable {

    @FXML
    private BarChart<String, Integer> chartbar;

   private ObservableList<String> monthNames  =  FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
       
    }    
    
//      public void setPersonData(List<Person> persons) {
//        // Count the number of people having their birthday in a specific month.
//        int[] monthCounter = new int[12];
//        for (Person p : persons) {
//            int month = p.getBirthday().getMonthValue() - 1;
//            monthCounter[month]++;
//        }
//
//        XYChart.Series<String, Integer> series = new XYChart.Series<>();
//
//        // Create a XYChart.Data object for each month. Add it to the series.
//        for (int i = 0; i < monthCounter.length; i++) {
//            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
//        }
//
//        barChart.getData().add(series);
//    }

    @FXML
    private void client(ActionEvent event) {
    }

    @FXML
    private void achat(ActionEvent event) {
    }
    
}
