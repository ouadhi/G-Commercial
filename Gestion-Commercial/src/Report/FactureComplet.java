package Report;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FactureComplet {

	public static List<List<String>> combineLists() throws IOException{

		List<List<String>> listOfFactures= new ArrayList<>();
		//List<List<String>> listOfExpeditions= ExcelExpedition.getExpeditions();
                List<List<String>> listOfExpeditions= ExcelExpedition.getExpeditions();
		List<List<String>> listOfClients= ExcelClient.getExcelClient();
		for (int i = 0; i < listOfExpeditions.size(); i++) {
			boolean foundName= false;
			Map<Integer, Integer> distance=new HashMap<>();
			List<String> facture=new ArrayList<>();
			List<String> client= null;
			List<String> expedition= listOfExpeditions.get(i);
			for (int j = 0; j < listOfClients.size(); j++) {
				client= listOfClients.get(j);

				if (expedition.get(1).equals(client.get(0))) {
					foundName= true;
					facture.addAll(client);
					facture.addAll(expedition);
					listOfFactures.add(facture);
					break;
				}
				//add The Levensthein distance for comparing two strings
				if (!foundName) {
					distance.put(j, LevenstheinDistance.getLevenshteinDistance(client.get(0).toUpperCase()
							, expedition.get(1).toUpperCase()));
				}
			}
			if (!foundName) {
				//code to select the most likely name
				int mostSimilarName=getSmallestDistance(distance);
				client= listOfClients.get(mostSimilarName);
				facture.addAll(client);
				facture.addAll(expedition);
				listOfFactures.add(facture);
			}
		}

		return listOfFactures; 
	}

	public static int getSmallestDistance(Map<Integer,Integer> distance){
		int minKey=Collections.min(distance.entrySet(), new Comparator<Map.Entry<Integer,Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {

				return o1.getValue().intValue() - o2.getValue().intValue();

			}}).getKey();

		return minKey;
	}
	
	
	public static List<List<String>> finalList() throws IOException{
		List<List<String>> grandeList=combineLists();
		ArrayList<List<String>> preFinalList= new ArrayList<>();

		for (int i = 0; i < grandeList.size(); i++) {
			if (LevenstheinDistance.getLevenshteinDistance(grandeList.get(i).get(0).toUpperCase()
					,grandeList.get(i).get(6).toUpperCase())>3) {
				//do nothing	
			}
			else{
				preFinalList.add(grandeList.get(i));
			}
		}
		for (int i = 0; i < preFinalList.size(); i++) {
			List<String> facture= preFinalList.get(i);
			if ("".equals(facture.get(9))) {
				//son
				//double newPrixUnitaire
				//System.out.println(facture.get(8));
				String newValuePrix= String.valueOf(round(new Double(facture.get(11))/1.07,2));
				facture.set(11,newValuePrix);
				String newMontant=String.valueOf(round(new Double(facture.get(10))*new Double(newValuePrix),2));
				facture.set(12,newMontant);
				String tva=String.valueOf(round(new Double(newMontant)*0.07,2));
                facture.add(tva);
				String ttc= String.valueOf(round(new Double(newMontant)+new Double(tva),2));
				facture.add(ttc);
				facture.remove(9);
				
			}
			else{
				//add tva ttc
				facture.add("0.00");
				facture.add(facture.get(12));
				facture.remove(10);

			}
			
		}
		return preFinalList ;
	}
	
	
	public  static ArrayList<String> getErreurClient()throws IOException{

		List<List<String>> grandeList=combineLists();
		System.out.println(grandeList);
		ArrayList<String> errorList= new ArrayList<>();
		for (int i = 0; i < grandeList.size(); i++) {
			if (LevenstheinDistance.getLevenshteinDistance(grandeList.get(i).get(0).toUpperCase(),grandeList.get(i).get(6).toUpperCase())>3) {
				String error=grandeList.get(i).get(6)+" numero BL: "+ grandeList.get(i).get(7);
				System.out.println("client: "+grandeList.get(i).get(0)+" expedition: "+grandeList.get(i).get(6));
				System.out.println(error);
				errorList.add(error);
			}
		}
		return errorList;
	}
	public static void writeToExcel(ArrayList<String> array){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("excel clients");

		int rowNum = 0;
		System.out.println("Creating excel");
		for(int RowNum=0; RowNum<array.size();RowNum++){
			HSSFRow row = sheet.createRow(RowNum);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(array.get(RowNum));
		}
		try {
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Hicham\\Desktop\\generatedExcel.xls");
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public static void main(String[] args)throws IOException {

		//List<List<String>> listOfFactures= combineLists();
		//System.out.println(listOfFactures);
		List<List<String>> finalList= finalList();
		System.out.println(finalList);
		System.out.println(finalList.size());
		//writeToExcel(getErreurClient());

	}

}
