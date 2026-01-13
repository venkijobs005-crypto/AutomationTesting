package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtility {
    public static File file;
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook xlWB;
    public static XSSFSheet xlSheet;
    public static XSSFRow xlRow;
    public static XSSFCell xlCell;
    public static XSSFCellStyle cellStyle;
    public static String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\utilities\\";


    public static void createNewXlbookNSheetWithHeadersOnly(String fileName, List<String> cellHeaders ) throws Exception{
        xlWB = new XSSFWorkbook();
        xlSheet = xlWB.createSheet("Sheet1");
        xlRow = xlSheet.createRow(0);
        cellStyle = xlWB.createCellStyle();
        for(int colCnt=0;colCnt<=cellHeaders.size()-1;colCnt++){
            xlRow.createCell(colCnt).setCellValue(cellHeaders.get(colCnt));
            xlCell = xlRow.getCell(colCnt);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            xlCell.setCellStyle(cellStyle);
        }

        fo = new FileOutputStream( filePath + fileName);
        xlWB.write(fo);
        xlWB.close();
    }

    public static int getXLRowCount(String fileName, String sheetName) throws Exception{
        fi = new FileInputStream(fileName);
        xlWB = new XSSFWorkbook(fi);
        xlSheet = xlWB.getSheet(sheetName);
        int rowCnt = xlSheet.getLastRowNum();
        xlWB.close();
        fi.close();
        return rowCnt;

    }

    public static int getXLCellCount(String fileName, String sheetName, int rowNum) throws Exception{
        fi = new FileInputStream(fileName);
        xlWB = new XSSFWorkbook(fi);
        xlSheet = xlWB.getSheet(sheetName);
        xlRow=xlSheet.getRow(rowNum);
        int cellCnt = xlRow.getLastCellNum();
        xlWB.close();
        fi.close();
        return cellCnt;
    }

    public static String getXLCellData(String fileName, String sheetName, int rowNum, int colNum) throws Exception{
        fi = new FileInputStream(fileName);
        xlWB = new XSSFWorkbook(fi);
        xlSheet = xlWB.getSheet(sheetName);
        xlRow=xlSheet.getRow(rowNum);
        xlCell = xlRow.getCell(colNum);
        String cellValue;
        try{
            DataFormatter format = new DataFormatter();
            cellValue = format.formatCellValue(xlCell);
        } catch (Exception e){
            cellValue = "";
        }
        xlWB.close();
        fi.close();
        return cellValue;
    }

    public static void setXLCellData(String fileName, String sheetName, int rowNum, int colNum, String value) throws Exception{
        fi = new FileInputStream(fileName);
        xlWB = new XSSFWorkbook(fi);
        xlSheet = xlWB.getSheet(sheetName);
        xlRow=xlSheet.getRow(rowNum);
        try{
            xlRow.createCell(colNum).setCellValue(value);
        }catch(NullPointerException e){
            ExcelUtility.xlRow = ExcelUtility.xlSheet.createRow(rowNum);
            xlRow.createCell(colNum).setCellValue(value);
        }

        fo = new FileOutputStream(fileName);
        xlWB.write(fo);
        xlWB.close();
        fi.close();
        fo.close();
    }

    public static void fillGreenColor(String fileName, String sheetName, int rowNum, int colNum) throws Exception{
        fillColors(fileName, sheetName, rowNum, colNum, "GREEN");
    }

    public static void fillRedColor(String fileName, String sheetName, int rowNum, int colNum) throws Exception{
        fillColors(fileName, sheetName, rowNum, colNum, "RED");
    }

    public static void fillYellowColor(String fileName, String sheetName, int rowNum, int colNum) throws Exception{
        fillColors(fileName, sheetName, rowNum, colNum, "YELLOW");
    }

    public static void fillGreyColor(String fileName, String sheetName, int rowNum, int colNum) throws Exception{
        fillColors(fileName, sheetName, rowNum, colNum, "GREY");
    }

    public static void fillColors(String fileName, String sheetName, int rowNum, int colNum, String color) throws Exception{
        fi = new FileInputStream(fileName);
        xlWB = new XSSFWorkbook(fi);
        xlSheet = xlWB.getSheet(sheetName);
        xlRow=xlSheet.getRow(rowNum);
        xlCell = xlRow.getCell(colNum);

        cellStyle = xlWB.createCellStyle();
        switch (color.toUpperCase()){
            case "GREEN":
                cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                break;
            case "RED":
                cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                break;
            case "YELLOW":
                cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                break;
            case "GREY":
                cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                break;
        }
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        xlCell.setCellStyle(cellStyle);
        fo = new FileOutputStream(fileName);
        xlWB.write(fo);
        xlWB.close();
        fi.close();
        fo.close();
    }

    public static Map<Integer,Map<String, String>> storeXLValsInMap(String fileName, String sheetName) throws Exception{
        Map<Integer,Map<String, String>> headermap = new LinkedHashMap<>();
        Map<String, String> valueMap;
        fi = new FileInputStream(fileName);
        xlWB = new XSSFWorkbook(fi);
        xlSheet = xlWB.getSheet(sheetName);
        int cellCnt = xlSheet.getRow(0).getLastCellNum();
        int row = xlSheet.getLastRowNum();
        for(int rowVal=0;rowVal<=row-1;rowVal++){
            valueMap = new LinkedHashMap<>();
            for(int colCnt=0;colCnt<=cellCnt-1;colCnt++){
                xlRow=xlSheet.getRow(0);
                XSSFCell headerCellVal;
                headerCellVal = xlRow.getCell(colCnt);
                xlRow=xlSheet.getRow(rowVal+1);
                xlCell=xlRow.getCell(colCnt);
                String headerVal, cellValue;
                try{
                    DataFormatter format = new DataFormatter();
                    headerVal = format.formatCellValue(headerCellVal);
                    cellValue = format.formatCellValue(xlCell);
                } catch (Exception e){
                    cellValue = "";
                    headerVal = "";
                }
                valueMap.put(headerVal,cellValue);
                headermap.put(rowVal+1,valueMap);
            }
        }
        xlWB.close();
        fi.close();
        return headermap;
    }
}
