package loginform;

import Report.FactureReport.ToutFacture;
import UIControle.ViewUrl;
import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JasperPrint;

public class Loginform extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Thread for creating the session factory
        new Thread() {
            public void run() {
                SessionsGenerator FactoryObject = new SessionsGenerator();
                            List<Facture> factures = FactureQueries.list();

            List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
            for (int i = 0; i < factures.size(); i++) {
                jasperPrints.add(ToutFacture.ItererJaspoerPrint(factures.get(i)));
                System.out.println("------------- facture: " + factures.get(i).getClient().getPrenom());

            }

            }
        }.start();
         Image icon = new Image(getClass().getResourceAsStream("/icons/logo.jpg"));
         stage.getIcons().add(icon) ; 
         stage.setTitle("MoulinMax - Commerciale");
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Employee_LoginFXML.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource(ViewUrl.Home1));
        //Image icon = new Image(getClass().getResourceAsStream("/icons/ok.png"));
        //stage.getIcons().add(icon);
        /////Parent root = FXMLLoader.load(getClass().getResource("/Views/Employee_LoginFXML.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource(ViewUrl.Home1));
        //Parent root = FXMLLoader.load(getClass().getResource(ViewUrl.DockList));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

        transitionIN(root);

    }

    public void transitionIN(Parent root) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        fadeIn.play();
    }

    public void transitionOut(Parent root) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0);
        fadeIn.setCycleCount(1);

        fadeIn.play();
    }


}
