package loginform;

import Report.EtatBleReport.EtatBleBean;
import java.util.ArrayList;
import UIControle.Methode;
import UIControle.Notification;
import com.gestionCommerciale.Models.SessionsGenerator;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
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
        List<String> macs = getMacs();

//change && uncomment

//        if (macs.contains(("A8-86-DD-91-25-3E").replace(" ", "").replace("-", ""))
//                || macs.contains(("68-5b-35-95-f1-47").replace(" ", "").replace("-", "")))
        
        
// uncomment poste 1
       //if (macs.contains(("DC-85-DE-BF-05-50").replace(" ", "").replace("-", ""))
       //         || macs.contains(("00-E0-4C-68-01-33").replace(" ", "").replace("-", ""))) 
        {
            launch(args);
        }
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

        stage.setOnCloseRequest(e -> {
            e.consume();
            closeStage();

        });

    }

    private void closeStage() {
        Optional<ButtonType> result = Notification.quitterAlert().showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
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

    private static List<String> getMacs() {
        List<String> macsList = new ArrayList<String>();

        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();

                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "").replace(" ", "").replace("-", ""));
                    }
                    //System.out.println(sb.toString());
                    macsList.add(sb.toString());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return macsList;
    }

}
