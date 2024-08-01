package Excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDrivenExcel {

	public ArrayList<String> getdata(String testcasename, String SheetName) throws IOException {
			
		ArrayList<String> a = new ArrayList<String>();

		
		FileInputStream FIS = new FileInputStream("C:\\Users\\veereshkumar.s\\Desktop\\demodata.xlsx");
		
				
		XSSFWorkbook WorkBook = new XSSFWorkbook(FIS);
		

		int Sheets = WorkBook.getNumberOfSheets();

		for (int i = 0; i < Sheets; i++) 
		
		{
			if (WorkBook.getSheetName(i).equalsIgnoreCase(SheetName)) 
			
			{
				XSSFSheet Sheet = WorkBook.getSheetAt(i);
				
				Iterator<Row> Rows = Sheet.iterator();
				Row FirstRow = Rows.next();
				
				Iterator<Cell> Cells = FirstRow.cellIterator();

				int k = 0;
				int Coloumn = 0;

				while (Cells.hasNext()) 
				{
					Cell Cellvalue = Cells.next();
					
					if (Cellvalue.getStringCellValue().equalsIgnoreCase("Testcases")) 
					{
						Coloumn = k;
					}

					k++;
				}
				

		  while (Rows.hasNext()) {
			
			Row RowNumber = Rows.next();
			
				if (RowNumber.getCell(Coloumn).getStringCellValue().equalsIgnoreCase(testcasename)) 
					
				{
						Iterator<Cell> RowValues = RowNumber.cellIterator();
						
						while (RowValues.hasNext()) 
							
						{
							Cell RowValue = RowValues.next();
							
							if (RowValue.getCellType() == CellType.STRING) 
								
							{
								a.add(RowValue.getStringCellValue());
							} 
							
							else 
							
							{
								a.add(NumberToTextConverter.toText(RowValue.getNumericCellValue()));
							}
						}
					}

				}
			}
		}
		
		return a;

	}

	public static void main(String[] args) throws IOException {

	}
}



