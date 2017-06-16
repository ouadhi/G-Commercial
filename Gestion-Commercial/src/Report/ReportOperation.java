package Report;

import java.io.File;
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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReportOperation {

	public static void main(String[] args) {
		systemLookFeel();
		ArrayList<String> listdes = new ArrayList<>();
		ArrayList<String> listdes1 = new ArrayList<>();
		ArrayList<String> listdes2 = new ArrayList<>();
		ArrayList<String> listqte = new ArrayList<>();
		ArrayList<String> listqte1 = new ArrayList<>();
		ArrayList<String> listqte2 = new ArrayList<>();
		ArrayList<String> listprix = new ArrayList<>();
		ArrayList<String> listprix1 = new ArrayList<>();
		ArrayList<String> listprix2 = new ArrayList<>();
		ArrayList<String> listmont = new ArrayList<>();
		ArrayList<String> listmont1 = new ArrayList<>();
		ArrayList<String> listmont2 = new ArrayList<>();

		String nom = "hdghgf";
		String code = "fdfdf";
		String address = "dfdff";
		String rc = "fdfdf";
		String fiscal = "fdfdf";
		String date = "dfff";
		String numFacture = "fdf";
		String article = "23244355456";
		String montantHT = "2324355";
		String tva = "17";
		String timbre = "34";
		String ttc = "2343546";
		String montantlettre = "cinq cent dinar";
		String chauffeur = "Ahmed";
		String matricule = "23243544546";

		listdes.add("drogo");
		listdes.add("ghdhffg");
		listdes.add("hjhjff");
		listqte.add("khal");
		listdes1.add("robert");
		listqte1.add("baratheon");
		listdes2.add("rheagar");
		listqte2.add("targaryan");
		listprix.add("dothraki");
		listprix1.add("rebels");
		listprix2.add("Dragons");
		listmont.add("essos");
		listmont1.add("westeros");
		listmont2.add("valyria");

		ReportOperation tableExecute = new ReportOperation();
		tableExecute.putReportInfo(nom, code, address, rc, fiscal, date, numFacture, article, montantHT, tva, timbre,
				ttc, montantlettre, chauffeur, matricule, listdes, listqte, listprix, listmont);
		tableExecute.printReport("dffdghh");
	}
	public static void systemLookFeel() {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	Collection<FactureReportBean> collBean = new ArrayList<FactureReportBean>();

	int id = 1;

	public JRDataSource getData() {
		return new JRBeanCollectionDataSource(collBean, false);

	}

	public JasperPrint printReport(String fileName) {
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
			// JasperViewer.viewReport(jasperPrint);

			//
			Exporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

			File exportReportFile = new File("C:\\Users\\Hicham\\Desktop\\factures\\" + fileName + ".pdf");

			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
			exporter.exportReport();
			this.id = 1;
			this.collBean.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jasperPrint;
	}

	public void putReportInfo(String nom, String code, String address, String rc, String fiscal, String date,
			String numFacture, String article, String montantHT, String tva, String timbre, String ttc,
			String montantlettre, String chauffeur, String matricule, List<String> designations, List<String> qtes,
			List<String> prixs, List<String> montants) {
		// patient info is the first to be written

		FactureReportBean beanInfo = new FactureReportBean(id, nom, code, address, rc, fiscal, date, numFacture,
				article, montantHT, tva, timbre, ttc, montantlettre, chauffeur, matricule, designations, qtes, prixs,
				montants);
		collBean.add(beanInfo);
		id++;
	}
}
