/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatEstimatifParClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hicham
 */
public class OperationEtatEstimatifClient {

    Collection<EtatEstimatifClientBean> collBean = new ArrayList<EtatEstimatifClientBean>();

    public JRDataSource getData() {
        return new JRBeanCollectionDataSource(collBean, false);

    }

    public void printReport() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
            jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            // InputStream stream=
            // this.getClass().getResourceAsStream("jasperreport/tableExample.jasper");
            InputStream stream = getClass().getResourceAsStream("EtatEstimatif.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, getData());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JasperViewer.viewReport(jasperPrint, false);
            // this.collBean.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putReportInfo(String nomPrenom, String activity, String address,
             String rc, String fiscal, String article, String dateDebut,
             String dateFin, String totalMontant, String totalTva, String totalTtc,
             String totalTimbre, String totalFarine, String totalSon, String totalQte,
             List<String> dates, List<String> nums, List<String> produits, List<String> montants,
             List<String> tvas, List<String> ttcs, List<String> timbres, List<String> qtes,String titreRC, String titreFiscal, String titreArticle,
            String titreTel, String titreFax, String titreEmail,String entrepriseNom,String entrepriseAddress) {
        // patient info is the first to be written

        EtatEstimatifClientBean beanInfo = new EtatEstimatifClientBean(nomPrenom, activity, address, rc, fiscal,
                article, dateDebut, dateFin, totalMontant, totalTva, totalTtc, totalTimbre, totalFarine, totalSon, totalQte, dates,
                nums, produits, montants, tvas, ttcs, timbres, qtes, titreRC,  titreFiscal,  titreArticle,
             titreTel,  titreFax,  titreEmail, entrepriseNom, entrepriseAddress);
        collBean.add(beanInfo);
    }

}
