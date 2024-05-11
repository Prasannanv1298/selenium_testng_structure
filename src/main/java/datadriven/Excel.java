package datadriven;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.*;

import static org.apache.poi.ss.usermodel.CellType.*;

public class Excel {

    public void createandWriteData(String sheetName,int rowCount,int cellCount) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet =  workbook.createSheet(sheetName);
        sheet.createRow(rowCount).createCell(cellCount);

//        sheet.getRow(0).createCell(0).setCellValue("Hello");
//        sheet.getRow(0).createCell(1).setCellValue("World");
//
//        sheet.createRow(1);
//        sheet.getRow(1).createCell(0).setCellValue("HYR");
//        sheet.getRow(1).createCell(1).setCellValue("Tutorials");
//
//        sheet.createRow(2);
//        sheet.getRow(2).createCell(0).setCellType(CellType.BLANK);
//        sheet.getRow(2).createCell(1).setCellValue("Tutorials");

        File file = new File("C:\\Data\\Personal\\Selenium Course\\SeleniumJavaMaven\\ExcelFiles\\Test4.xls");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
    }

public void readData() throws FileNotFoundException {

//    File file = new File("C:\\Data\\Personal\\Selenium Course\\SeleniumJavaMaven\\ExcelFiles\\Test.xls");
//    FileInputStream fis = new FileInputStream(file);
//    XSSFWorkbook workbook = new XSSFWorkbook(fis);
//    XSSFSheet sheet = workbook.getSheetAt(0);
////		String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
////		System.out.println(cellValue);
//
//    int rowCount = sheet.getPhysicalNumberOfRows();
//
//    for (int i = 0; i < rowCount; i++) {
//        XSSFRow row = sheet.getRow(i);
//
//        int cellCount = row.getPhysicalNumberOfCells();
//        for (int j = 0; j < cellCount; j++) {
//            XSSFCell cell = row.getCell(j);
//            String cellValue = getCellValue(cell);
//            System.out.print("||"+cellValue);
//        }
//        System.out.println();
//    }
//
//    workbook.close();
//    fis.close();

}


    public static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case STRING:
                return cell.getStringCellValue();
            case BLANK:
                return "";
            default:
                return cell.getStringCellValue();
        }
    }

}
