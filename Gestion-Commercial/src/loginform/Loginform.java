package loginform;

import Report.EtatBleReport.EtatBleBean;
import java.util.ArrayList;
import java.util.List;

import com.gestionCommerciale.HibernateSchema.Facture;
import com.gestionCommerciale.Models.FactureQueries;
import Report.FactureReport.ToutFacture;
import UIControle.Methode;
import com.gestionCommerciale.Models.SessionsGenerator;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Loginform extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Thread for creating the session factory
        new Thread() {
            @Override
            public void run() {
                SessionsGenerator FactoryObject = new SessionsGenerator();
                printReport();

            }
        }.start();
        Image icon = new Image(getClass().getResourceAsStream("/icons/logo.jpg"));
        stage.getIcons().add(icon);
        stage.setTitle("MoulinMax - Commerciale");
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Employee_LoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Methode.moveFocus(root);
        stage.show();

       // transitionIN(root);

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

    public void printReport() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
            jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            InputStream stream = getClass().getResourceAsStream("EtatBle.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, getData());
            //JasperViewer.viewReport(jasperPrint, false);
            // this.collBean.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JRDataSource getData() {
        Collection<EtatBleBean> collBean = new ArrayList<EtatBleBean>();
        return new JRBeanCollectionDataSource(collBean, false);

    }
    
    
   


}
