/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report.FactureReport;

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
public class OperationFactuReport {

	Collection<FactureBean> collBean = new ArrayList<FactureBean>();
	int id = 1;

	public JRDataSource getData() {
		return new JRBeanCollectionDataSource(collBean, false);

	}

	public JasperPrint getJasperPrint() {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
			JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
			jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			// InputStream stream=
			// this.getClass().getResourceAsStream("jasperreport/tableExample.jasper");
			InputStream stream = getClass().getResourceAsStream("ReportFacture.jasper");

			JasperReport report = (JasperReport) JRLoader.loadObject(stream);
			jasperPrint = JasperFillManager.fillReport(report, params, getData());
			this.id = 1;
			this.collBean.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasperPrint;

	}

	public void printReport() {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
			JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
			jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			// InputStream stream=
			// this.getClass().getResourceAsStream("jasperreport/tableExample.jasper");
			InputStream stream = getClass().getResourceAsStream("ReportFacture.jasper");

			JasperReport report = (JasperReport) JRLoader.loadObject(stream);
			jasperPrint = JasperFillManager.fillReport(report, params, getData());
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JasperViewer.viewReport(jasperPrint, false);

			this.id = 1;
			this.collBean.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void putReportInfo(String nom, String code, String address, String rc, String fiscal, String date,
            String numFacture, String article, String montantHT, String tva, String timbre, String ttc,
            String montantlettre, String chauffeur, String matricule, List<String> designations, List<String> qtes,
            List<String> prixs, List<String> montants, String payement,String titreRC, String titreFiscal,String titreArticle,
            String titreTel,String titreFax,String titreEmail) {
		// patient info is the first to be written

		FactureBean beanInfo = new FactureBean(id, nom, code, address, rc, fiscal, date, numFacture, article, montantHT,
				tva, timbre, ttc, montantlettre, chauffeur, matricule, designations, qtes, prixs, montants,payement,
                titreRC,  titreFiscal, titreArticle,titreTel, titreFax, titreEmail);
		collBean.add(beanInfo);
		id++;
	}
}
