package com.qa.api.testautomation.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author rituraj
 *
 */
public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static String TESTDATA_SHEET_PATH = "./src/main/java/com/qa/api/testautomation/testdata/GoRestTestData.xlsx";
	
	/**
	 * 
	 * @param sheetName
	 * @return
	 */
	public static Object[][] getTestData(String sheetName) {
		
		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
		   book = WorkbookFactory.create(ip);
		  sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object data [][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i =0; i<sheet.getLastRowNum();i++) {
			
			for(int k =0;k<sheet.getRow(0).getLastCellNum();k++) {
			   data[i][k] = sheet.getRow(i+1).getCell(k).toString();	
			}
		}
		return data;
	
	}

}
