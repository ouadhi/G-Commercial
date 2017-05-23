/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.EtatExpeditionReport;

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
public class OperationEtatExpedition {

    Collection<EtatExpeditionBean> collBean = new ArrayList<EtatExpeditionBean>();

    public JRDataSource getData() {
        return new JRBeanCollectionDataSource(collBean, false);

    }

    public void putReportInfo(String date, String totalFarine, String totalSon,
            String totalMontant, String totalVersement, String totalVersemntMoinMontant,
            String totalQuantite, String farineTotal, String sonTotal, String montantTotal,
            String versementTotal, String differenceTotal, List<String> clients,
            List<String> nums, List<String> produits, List<String> qteFarins, List<String> qteSons,
            List<String> prixs, List<String> montants, List<String> versements, List<String> observations) {
        //patient info is the first to be written

        EtatExpeditionBean beanInfo = new EtatExpeditionBean(date, totalFarine, totalSon,
                totalMontant, totalVersement, totalVersemntMoinMontant,
                totalQuantite, farineTotal, sonTotal, montantTotal,
                versementTotal, differenceTotal, clients,
                nums, produits, qteFarins, qteSons,
                prixs, montants, versements, observations);
        collBean.add(beanInfo);
    }

    public void printReport() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
            jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            //InputStream stream= this.getClass().getResourceAsStream("jasperreport/tableExample.jasper");
            InputStream stream = getClass().getResourceAsStream("EtatExpedition.jasper");
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
