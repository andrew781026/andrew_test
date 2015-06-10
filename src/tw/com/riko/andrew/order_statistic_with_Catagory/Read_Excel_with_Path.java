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
		
		Sheet sheet = wb.getSheetAt(0); // ���oExcel�Ĥ@��sheet(sheet_id�q0�}�l�p��)
		Cell cell;
		Set<String> Set_input_product_id = new HashSet<>();
		ArrayList<String> input_product_id = new ArrayList<>();
		// getPhysicalNumberOfRows�o�Ӥ���n�@ //getLastRowNum:�o�Ӧn���|�t1��
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // �ѩ�� 0Row ��title,�G i �q1 �}�l
			try{														
			Row row = sheet.getRow(i); // ���o�� i Row
			
			cell = row.getCell(0);
			//����Ū���ɮ׬O�_���@��
			//System.out.println(cell.getStringCellValue() );
			if (cell.getStringCellValue().length() == 11) {
				
				Set_input_product_id.add(cell.getStringCellValue());

			}
			}catch(NullPointerException e){
				System.out.println("column A���ťժ��x�s��");
			}
			
		}
		
		for(String ID : Set_input_product_id){
			input_product_id.add(ID);
		}
			
		
		wb.close();
		
		//����ArrayList���쩳���ƻ�F��I
		/*
		for(String ss :input_product_id){
			System.out.println(ss);
		}
		*/
		
		
		return input_product_id;
		
	}

}
