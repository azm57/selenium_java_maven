package com.pers.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelUtil {

	public static Map<String, String> objTestDataMap = new LinkedHashMap<String, String>();

//public static void main(String[] args) throws FileNotFoundException {

//		@SuppressWarnings("rawtypes")
//Map<String, String> returedSingRowData =ExcelUtil.gFunc_ReadTestData("D:\\SeleniumJavaFramework\\src\\test\\resources\\test_excelreading.xls", "Sheet1", "CartList");
//		
	// System.out.println(returedSingRowData);
//		
//		//Map<String,Map<String,String>> returedMulRowData=ExcelUtil.gFunc_ReadMulTestData("test_excelreading.xls", "Sheet1", 1, 3);
//		//System.out.println(returedMulRowData); 
//}

	public static Map<String, String> gFunc_ReadTestData(String strFilePath, String strSheetName, String TestID)
			throws FileNotFoundException {

		Workbook workbook = null;
		Sheet dataSheet;
		Row HeaderRow;
		Row DataRow = null;
		int intTestDataRowNo = 0;
		FileInputStream fo = null;// to read excel as a stream.
		File file = new File(strFilePath);// to access excel file
		if (!file.exists())// to check for file existence
		{
			return null;
		} else {
			fo = new FileInputStream(file);
		}
		try {
			if (strFilePath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fo);
			} else if (strFilePath.endsWith("xls")) {
				workbook = new HSSFWorkbook(fo);
			}
			dataSheet = workbook.getSheet(strSheetName);

			HeaderRow = dataSheet.getRow(0);

			int intEndRow = dataSheet.getLastRowNum();

			for (int icounter = 1; icounter <= intEndRow; icounter++) {
				DataRow = dataSheet.getRow(icounter);
				String strCellValue = DataRow.getCell(0).toString();
				if (strCellValue.equals(TestID)) {
					intTestDataRowNo = icounter;
					break;
				}
			}

			if (intTestDataRowNo == 0) {
				throw new CustomeExceptionUtil("Invalid TestID");
			} else {
				DataRow = dataSheet.getRow(intTestDataRowNo);
				for (int iCounter = 0; iCounter <= HeaderRow.getLastCellNum(); iCounter++) {
					Cell cellValue = DataRow.getCell(iCounter);
					if (cellValue != null && cellValue.toString().length() > 0)
						objTestDataMap.put(HeaderRow.getCell(iCounter).toString(),
								DataRow.getCell(iCounter).toString());
				}
				System.out.println(objTestDataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objTestDataMap;
	}

//	public static Map<String,String> gFunc_ReadTestData(String StrSheetPath,String strSheetName,int RowNumber) throws FileNotFoundException
//	{
//		Map<String,String> objMap = new LinkedHashMap<String,String>();
//		Workbook workbook = null;
//		Sheet dataSheet;
//		Row HeaderRow;
//		Row DataRow;
//		FileInputStream fo =null;//to read excel as a stream.
//		Sheet scrSheet = null;//to access particular sheet
//		File file = new File(StrSheetPath);//to  access excel file
//		if(!file.exists())//to check for file existence
//		{
//			return null;
//		}
//		else
//		{
//			fo = new FileInputStream(file);
//		}
//		try
//		{
//			if(StrSheetPath.endsWith("xlsx"))
//			{
//				workbook = new XSSFWorkbook(fo);
//			}
//			else if(StrSheetPath.endsWith("xls"))
//			{
//				workbook = new HSSFWorkbook(fo);
//			}
//			dataSheet = workbook.getSheet(strSheetName);
//			HeaderRow = dataSheet.getRow(0);
//			DataRow = dataSheet.getRow(RowNumber);
//			
//			for(int iCounter =0;iCounter<=HeaderRow.getLastCellNum();iCounter++)
//			{
//				objMap.put(HeaderRow.getCell(iCounter).toString(), DataRow.getCell(iCounter).toString());
//			}
//		}
//		catch(Exception e)
//		{
//
//		}
//
//		return objMap;
//	}
//
//public static Map<String,Map<String,String>> gFunc_ReadMulTestData(String strFilePath,String strSheetName,int StartRow,int EndRow) throws IOException 
//
//	{
//
//		Map<String,Map<String,String>> objMulMap = new  LinkedHashMap<String,Map<String,String>>();
//		Row DataRow = null;
//		Workbook workbook = null;
//		FileInputStream fo = new FileInputStream(strFilePath);
//
//		if (strFilePath.endsWith("xlsx")) {
//			workbook = new XSSFWorkbook(fo);
//		} else if (strFilePath.endsWith("xls")) {
//			workbook = new HSSFWorkbook(fo);
//		} else {
//			throw new IllegalArgumentException("The specified file is not Excel file");
//		}
//		Sheet srcSheet = workbook.getSheet(strSheetName);
//		Row hdrRow =  srcSheet.getRow(0);
//
//		for(int intRowCounter = StartRow;intRowCounter <=(StartRow+EndRow)-1; intRowCounter++)
//		{
//			DataRow = srcSheet.getRow(intRowCounter);
//			Map<String,String> objMap = new  HashMap<String,String>();
//			for(int iColumnCounter = 0; iColumnCounter <=hdrRow.getLastCellNum()-1;iColumnCounter++)
//			{
//				Cell cellValue = DataRow.getCell(iColumnCounter);
//
//				if(cellValue != null && cellValue.toString().length() >0)
//				{
//					objMap.put(hdrRow.getCell(iColumnCounter).toString(), cellValue.toString());
//					//System.out.println("Column Name " +hdrRow.getCell(iColumnCounter).toString() + "   Cell Value "+ cellValue);
//
//				}
//			}
//			objMulMap.put("Row"+intRowCounter, objMap);
//			//objMap.clear();
//		}
//		return objMulMap;
//	}	
}
