/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.BonLivraisonReport;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class OperationBonLivraisonReport {

    Collection<BonLivraisonBean> collBean = new ArrayList<BonLivraisonBean>();
    int id = 1;

    public JRDataSource getData() {
        return new JRBeanCollectionDataSource(collBean, false);

    }

    public void putReportInfo(String nom, String code, String address,
             String rc, String fiscal, String date, String numFacture,
             String article, String chauffeur,
             String matricule, List<String> designations, List<String> qtes) {
        BonLivraisonBean beanInfo = new BonLivraisonBean(id, nom, code, address, rc, fiscal, date, numFacture,
                 article, chauffeur, matricule, designations, qtes);
        collBean.add(beanInfo);
        id++;
    }

    public void printReport(String fileName) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
            jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            //InputStream stream= this.getClass().getResourceAsStream("jasperreport/tableExample.jasper");
            InputStream stream = getClass().getResourceAsStream("BonLivraison.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,
                     params, getData());
            JasperViewer.viewReport(jasperPrint);
            //this.collBean.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
