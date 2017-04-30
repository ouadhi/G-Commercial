package Report;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.ibm.icu.text.RuleBasedNumberFormat;
import net.sf.jasperreports.engine.JRException;

public class GenerateReport {

	
	

	public  void generateReport()throws IOException,JRException{

		List<List<String>> listFactures=FactureComplet.finalList();
		ReportOperation tableExecute= new ReportOperation();
		for (int i = 0; i < listFactures.size(); i++) {
			List<String> facture= listFactures.get(i);
			ArrayList<String> listdes= new ArrayList<>();
			ArrayList<String> listqte= new ArrayList<>();
			ArrayList<String> listprix= new ArrayList<>();
			ArrayList<String> listmont= new ArrayList<>();
			listdes.add(facture.get(8));
			listqte.add(facture.get(9));
			listprix.add(facture.get(10));
			listmont.add(facture.get(11));
			String nom=facture.get(0);
			String code="";
			String address=facture.get(4);
			String rc=facture.get(1);
			String fiscal=facture.get(2);
			String date=facture.get(5);
			String numFacture=String.valueOf(new Double(facture.get(7)).intValue());
			String article=facture.get(3);
			String montantHT=facture.get(11);
			String tva=facture.get(12);
			String timbre="0.00";
			String ttc=facture.get(13);
			RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat( new Locale("fr", "FR"),
					RuleBasedNumberFormat.SPELLOUT ); 
			String montantlettre=ruleBasedNumberFormat.format(new Double(ttc))+" Dinars Algérien";
			String chauffeur="";
			String matricule="";

			tableExecute.putReportInfo(nom, code, address, rc, fiscal, date
					, numFacture, article, montantHT, tva, timbre, ttc, montantlettre
					, chauffeur, matricule, listdes, listqte, listprix, listmont);

			tableExecute.printReport(numFacture);
		}
		



	}

	public static void main(String[] args) throws IOException,JRException{
		GenerateReport gr= new GenerateReport();
		gr.generateReport();
	}

}
