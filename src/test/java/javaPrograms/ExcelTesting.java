package javaPrograms;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import utilities.ExcelUtility;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExcelTesting {

    public void readExcelData() throws Exception {
        Map<String, Map<String,String>> getXLDataMap = new LinkedHashMap<>();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\utilities\\text123.xls");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheet("Sheet0");
        XSSFRow firstRow = ws.getRow(0);
        int columnCnt = firstRow.getLastCellNum();
        for(int rowCnt = 0; rowCnt <ws.getLastRowNum()-1; rowCnt++) {
            String empNo = ws.getRow(rowCnt).getCell(0).toString();
            Map<String,String> getRowVal = new LinkedHashMap<>();
            for (int colCnt = 0; colCnt <= columnCnt-1; colCnt++) {
                String header = firstRow.getCell(colCnt).getStringCellValue();
                String valueFromCell = ws.getRow(rowCnt).getCell(colCnt).toString();
                getRowVal.put(header, valueFromCell);

            }
            getXLDataMap.put(empNo, getRowVal);
        }
        fis.close();
        for(Map.Entry<String, Map<String, String>> entry : getXLDataMap.entrySet())
            System.out.println(entry);
    }

    @Test
    public void writeDataIntoExcelSimple() throws Exception{
        System.out.println(System.getProperty("user.dir"));
        String fileName = "text123.xls";
        List<String> colHeaders = new LinkedList<>();
        colHeaders.add("Name");
        colHeaders.add("Dept");
        colHeaders.add("Designation");
        colHeaders.add("Salary");
        ExcelUtility.createNewXlbookNSheetWithHeadersOnly(fileName,colHeaders);
        for(int name =0;name<= names().size()-1;name++){
            ExcelUtility.setXLCellData(ExcelUtility.filePath + fileName, "Sheet1", name+1, 0, names().get(name));
        }
        for(int deptNm =0;deptNm<= dept().size()-1;deptNm++){
            ExcelUtility.setXLCellData(ExcelUtility.filePath + fileName, "Sheet1", deptNm+1, 1, dept().get(deptNm));
        }
        for(int desig =0;desig<= designation().size()-1;desig++){
            ExcelUtility.setXLCellData(ExcelUtility.filePath + fileName, "Sheet1", desig+1, 2, designation().get(desig));
        }
        for(int sal = 0; sal <= salary().size()-1; sal++){
            ExcelUtility.setXLCellData(ExcelUtility.filePath + fileName, "Sheet1", sal +1, 3, salary().get(sal));
        }
        System.out.println(ExcelUtility.storeXLValsInMap(ExcelUtility.filePath + fileName, "Sheet1"));
    }

    public static List<String> names(){
        List<String> namelist = new LinkedList<>();
        namelist.add("Sachin");
        namelist.add("Bumrah");
        namelist.add("Dhoni");
        namelist.add("Jaiswal");
        namelist.add("Chahal");
        namelist.add("Rohit");
        return namelist;
    }

    public static List<String> dept(){
        List<String> namelist = new LinkedList<>();
        namelist.add("Batting");
        namelist.add("Bowling");
        namelist.add("Keeper");
        namelist.add("Sub");
        namelist.add("Bowling");
        namelist.add("Batting");
        return namelist;
    }

    public static List<String> designation(){
        List<String> namelist = new LinkedList<>();
        namelist.add("Top Order");
        namelist.add("Strike bowler");
        namelist.add("Captain");
        namelist.add("Junior");
        namelist.add("Spin");
        namelist.add("Opening");
        return namelist;
    }

    public static List<String> salary(){
        List<String> namelist = new LinkedList<>();
        namelist.add("1000");
        namelist.add("1000");
        namelist.add("1000");
        namelist.add("500");
        namelist.add("700");
        namelist.add("1000");
        return namelist;
    }

}
