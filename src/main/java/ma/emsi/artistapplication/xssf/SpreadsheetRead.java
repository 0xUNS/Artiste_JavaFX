package ma.emsi.artistapplication.xssf;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class SpreadsheetRead {
	static XSSFRow row;
	public static void sheetRead(XSSFWorkbook workbook, int page){
		XSSFSheet spreadsheet = workbook.getSheetAt(page);
		Iterator<Row> rowIterator = spreadsheet.iterator();

		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator <Cell>  cellIterator = row.cellIterator();

			while ( cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				System.out.print(cell.toString()+"\t|\t");

			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws Exception {
		try(FileInputStream fis = new FileInputStream(new File("src/main/resources/Data.xlsx")))
		{
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			System.out.println("-- TABLEAU --");
			sheetRead(workbook, 0);
			System.out.println("-- ARTISTE --");
			sheetRead(workbook, 1);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
		catch (IOException e) {
			// TODO: handle exception
		}
	}
}
