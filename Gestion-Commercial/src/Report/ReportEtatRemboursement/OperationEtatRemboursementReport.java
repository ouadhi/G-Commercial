/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.ReportEtatRemboursement;

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
public class OperationEtatRemboursementReport {

    Collection<EtatRemboursementBean> collBean = new ArrayList<>();

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
            InputStream stream = getClass().getResourceAsStream("EtatRemboursement.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, getData());
            JasperViewer.viewReport(jasperPrint, false);
            // this.collBean.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putReportInfo(String doit, String date, String montantTotal, String montantLettre,
            List<String> parcours, List<String> distances, List<String> nums, List<String> qtes, List<String> prixs,
            List<String> montants, List<String> jours, String titreRC, String titreFiscal,String titreArticle,
            String titreTel,String titreFax,String titreEmail) {
        // patient info is the first to be written

        EtatRemboursementBean beanInfo = new EtatRemboursementBean(doit, date, montantTotal, montantLettre,
                parcours, distances, nums, qtes, prixs, montants, jours, titreRC,  titreFiscal, titreArticle,
             titreTel, titreFax, titreEmail);
        collBean.add(beanInfo);
    }
}
