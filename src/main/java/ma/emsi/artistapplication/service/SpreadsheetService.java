package ma.emsi.artistapplication.service;

import ma.emsi.artistapplication.entities.Artiste;
import ma.emsi.artistapplication.entities.Tableau;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SpreadsheetService {
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
	public static void importer(String path) throws Exception {
		try(FileInputStream fis = new FileInputStream("/home/abc/" + path))
		{
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			System.out.println("-- ARTISTE --");
			sheetRead(workbook, 0);
			System.out.println("-- TABLEAU --");
			sheetRead(workbook, 1);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	public static void tableauWrite(XSSFWorkbook workbook){
		XSSFSheet spreadsheet = workbook.createSheet("TABLEAU");
		XSSFRow row;
		Cell cell;

		TableauService  tableauService = new TableauService();
		List<Tableau> tableauList = tableauService.findAll();
		row = spreadsheet.createRow(0);
		int cellNum = 0;

		String[] arr = {"Id", "Nom", "Description", "Genre", "Prix", "est Vendu", "Date Creation", "Artiste"};
		for (String e: new ArrayList<>(Arrays.asList(arr))){
			cell = row.createCell(cellNum++);
			cell.setCellValue(e);
		}

		int rowNum = 1;
		for (Tableau tableau : tableauList) {
			row = spreadsheet.createRow(rowNum++);
			cellNum = 0;
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getId());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getNom());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getDescription());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getGenre());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getPrix());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.isEstVendu());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getDateCreation());
			cell = row.createCell(cellNum++);
			cell.setCellValue(tableau.getArtiste().getPseudoNom());
		}
	}

	public static void artisteWrite(XSSFWorkbook workbook){
		XSSFSheet spreadsheet = workbook.createSheet("ARTIST");
		XSSFRow row;
		Cell cell;

		ArtisteService artisteService = new ArtisteService();
		List<Artiste> artisteList = artisteService.findAll();
		row = spreadsheet.createRow(0);
		int cellNum = 0;

		String[] arr = {"Id", "Nom", "Prenom", "Date Naissance", "Pseudo Nom", "est Mort", "Pays Origine", "Adresse"};
		for (String e: new ArrayList<>(Arrays.asList(arr))){
			cell = row.createCell(cellNum++);
			cell.setCellValue(e);
		}

		int rowNum = 1;
		for (Artiste artiste : artisteList) {
			row = spreadsheet.createRow(rowNum++);
			cellNum = 0;
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getId());
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getNom());
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getPrenom());
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getDateNaissance());
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getPseudoNom());
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.isEstMort()? "oui": "non");
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getPaysOrigine());
			cell = row.createCell(cellNum++);
			cell.setCellValue(artiste.getAdresse());
		}
	}
	public static void exporter(String path) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();

		artisteWrite(workbook);
		tableauWrite(workbook);

		FileOutputStream out;
		out = new FileOutputStream(new File("/home/abc/" + path));
		workbook.write(out);
		out.close();
	}
}
