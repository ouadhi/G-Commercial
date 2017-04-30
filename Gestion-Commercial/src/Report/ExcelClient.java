package Report;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class ExcelClient {
	
	
	public static List<List<String>> iterateExcel(String file){
		List<List<String>> listOfClientSheets= new ArrayList<>();

		try {
			InputStream input = new FileInputStream(file);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);


			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				HSSFSheet sheet = wb.getSheetAt(i);
				listOfClientSheets.add(iterateOverSheet(wb, i));
			}
			wb.close();
			fs.close();

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return listOfClientSheets;
	}
	public static List<String> iterateOverSheet(HSSFWorkbook workbook ,int sheetCounter) throws IOException{
		
		HSSFSheet sheet= workbook.getSheetAt(sheetCounter);
		List<String> listOfClientValues= new ArrayList<>();
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext() & listOfClientValues.size()<5 ) {
			Row nextRow = iterator.next();
			Cell cell1 = nextRow.getCell(0);
			DataFormatter df = new DataFormatter();
			String valueCellAsString = df.formatCellValue(cell1);
			if (!("".equals(valueCellAsString)) ) {
				if (valueCellAsString.contains(":")) {
					//take substring
					String newString =valueCellAsString.substring(valueCellAsString.indexOf(":") + 1 , valueCellAsString.length());										
					listOfClientValues.add(newString);

				}else{
					listOfClientValues.add(valueCellAsString);
				}
			}
		}
		workbook.close();
		return listOfClientValues;
	}
	
	public static List<List<String>>getExcelClient(){
		
		String excelPath="C:\\Users\\Hicham\\Desktop\\janvier 2017.xls";
		List<List<String>>	list= iterateExcel(excelPath);
		String excelPath2="C:\\Users\\Hicham\\Desktop\\feverie 2017.xls";
		List<List<String>>	list1= iterateExcel(excelPath2);
		String excelPath3="C:\\Users\\Hicham\\Desktop\\mars 2017 (1).xls";
		List<List<String>>	list2= iterateExcel(excelPath3);
		List<List<String>> allList = new ArrayList<>();
		allList.addAll(list);
		allList.addAll(list1);
		allList.addAll(list2);

		
		return allList ;
	}
	


	public static void main(String[] args) {
		//read excel file
		List<List<String>>allList= ExcelClient.getExcelClient();
		System.out.println(allList);
	}

}
