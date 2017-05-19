package Report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class ExcelExpedition {

    public static List<List<String>> iterateOverSheet(String file) throws IOException {

        InputStream input = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(input);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        wb.setMissingCellPolicy(MissingCellPolicy.RETURN_BLANK_AS_NULL);
        DataFormatter fmt = new DataFormatter();
        List<List<String>> ListOfFactures = new ArrayList<>();
        for (int sn = 0; sn < wb.getNumberOfSheets(); sn++) {
            HSSFSheet sheet = wb.getSheetAt(sn);
            for (int rn = 10; rn <= 43; rn++) {
                HSSFRow row = sheet.getRow(rn);
                if (row == null) {
                    // do nothing
                } else {
                    // Row "rn" has data
                    List<String> factures = new ArrayList<>();
                    factures.add(sheet.getSheetName());
                    for (int cn = 0; cn < 7; cn++) {
                        HSSFCell cell = row.getCell(cn);
                        if (cell == null) {
                            // cell is empty
                            factures.add("");
                        } else {
                            if (cell.getCellTypeEnum() == CellType.FORMULA) {
                                switch (cell.getCachedFormulaResultTypeEnum()) {
                                    case NUMERIC:
                                        factures.add(String.valueOf(cell.getNumericCellValue()));
                                        break;
                                    case STRING:
                                        factures.add(cell.getStringCellValue());
                                        break;

                                }
                            }
                            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                factures.add(String.valueOf(cell.getNumericCellValue()));
                            }
                            if (cell.getCellTypeEnum() == CellType.STRING) {
                                factures.add(cell.getStringCellValue());
                            }
                        }
                    }
                    ListOfFactures.add(factures);
                }
            }

        }
        wb.close();
        return ListOfFactures;
    }

    public static List<List<String>> removeEmptyLists(List<List<String>> startList) {

        List<List<String>> finalList = new ArrayList<>();
        for (int i = 0; i < startList.size(); i++) {
            if ("".equals(startList.get(i).get(2))) {

            } else {
                finalList.add(startList.get(i));
            }
        }
        return finalList;
    }

    public static List<List<String>> getExpeditions() throws IOException {
        String excelPath = "ExcelData/janvier expidition(1).xls";
        String excelPath1 = "ExcelData/fevrie expidition.xls";
        String excelPath2 = "ExcelData/mars expidition.xls";

        List<List<String>> list = iterateOverSheet(excelPath);
        List<List<String>> list1 = iterateOverSheet(excelPath1);
        List<List<String>> list2 = iterateOverSheet(excelPath2);

        List<List<String>> allList = new ArrayList<>();
        allList.addAll(list);
        allList.addAll(list1);
        allList.addAll(list2);

        List<List<String>> finaList = removeEmptyLists(allList);

        return finaList;

    }

    public static void main(String[] args) throws IOException {

        System.out.println(getExpeditions());
        System.out.println(getExpeditions().size());

    }

}
