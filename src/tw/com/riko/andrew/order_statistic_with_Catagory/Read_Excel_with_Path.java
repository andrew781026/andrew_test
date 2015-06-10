package tw.com.riko.andrew.order_statistic_with_Catagory;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class Read_Excel_with_Path {
	
	public static void main(String [] args) {
		File_Chooser file_Chooser = new File_Chooser();
		String filePath = file_Chooser.file_Read_Choose();
		try {
			new Read_Excel_with_Path().readExcel(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public ArrayList<String> readExcel(String filePath) throws IOException, InvalidFormatException {

		//OPCPackage pkg = OPCPackage.open(filePath);
		
		Workbook wb = WorkbookFactory.create(new File(filePath));
		/*if (wb instanceof HSSFWorkbook) {
		    // do whatever
			wb = new HSSFWorkbook(fis);
		}  else if (wb instanceof XSSFWorkbook) {
		    // do whatever
			wb = new XSSFWorkbook(fis);
		}*/
		
		Sheet sheet = wb.getSheetAt(0); // 取得Excel第一個sheet(sheet_id從0開始計算)
		Cell cell;
		Set<String> Set_input_product_id = new HashSet<>();
		ArrayList<String> input_product_id = new ArrayList<>();
		// getPhysicalNumberOfRows這個比較好　 //getLastRowNum:這個好像會差1筆
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 由於第 0Row 為title,故 i 從1 開始
			try{														
			Row row = sheet.getRow(i); // 取得第 i Row
			
			cell = row.getCell(0);
			//測試讀取檔案是否有作用
			//System.out.println(cell.getStringCellValue() );
			if (cell.getStringCellValue().length() == 11) {
				
				Set_input_product_id.add(cell.getStringCellValue());

			}
			}catch(NullPointerException e){
				System.out.println("column A有空白的儲存格");
			}
			
		}
		
		for(String ID : Set_input_product_id){
			input_product_id.add(ID);
		}
			
		
		wb.close();
		
		//測試ArrayList中到底有甚麼東西！
		/*
		for(String ss :input_product_id){
			System.out.println(ss);
		}
		*/
		
		
		return input_product_id;
		
	}

}
