package ma.emsi.artistapplication.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import ma.emsi.artistapplication.entities.Artiste;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvService {
	private static final ArtisteService artisteService = new ArtisteService();
	public static void exporter(String path) {
		List<Artiste> artistes = artisteService.findAll();

		String[] csvHeader = {"Id", "Nom", "Prenom", "Date Naissance", "Pseudo Nom", "est Mort", "Pays Origine", "Adresse"};

		try (CSVWriter writer = new CSVWriter(new FileWriter("/home/abc/" + path))) {
			writer.writeNext(csvHeader);

			for (Artiste artiste : artistes) {
				String[] rowData = {
						artiste.getId().toString(),
						artiste.getNom(),
						artiste.getPrenom(),
						artiste.getDateNaissance().toString(),
						artiste.getPseudoNom(),
						String.valueOf(artiste.isEstMort()),
						artiste.getPaysOrigine(),
						artiste.getAdresse()
				};
				writer.writeNext(rowData);
			}

			System.out.println("Artistes exported to artistes.csv successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void impoter(String path) {
		for(Artiste artiste: readArtisteDataFromCSV(path))
			if (!artisteService.artisteInList(artiste.getNom(), artiste.getPrenom(), artiste.getPseudoNom()))
				artisteService.save(artiste);
	}

	private static List<Artiste> readArtisteDataFromCSV(String path) {
		List<Artiste> artistes = new ArrayList<>();

		try (CSVReader reader = new CSVReader(new FileReader("/home/abc/"+path))) {
			String[] nextLine;
			// Skip the header line
			reader.readNext();

			while ((nextLine = reader.readNext()) != null) {
				// Assuming the CSV file columns are in the order: id, nom, prenom, dateNaissance, pseudoNom, estMort, paysOrigine, adresse
				int id = Integer.parseInt(nextLine[0]);
				String nom = nextLine[1];
				String prenom = nextLine[2];
				LocalDate dateNaissance = LocalDate.parse(nextLine[3]);
				String pseudoNom = nextLine[4];
				boolean estMort = Boolean.parseBoolean(nextLine[5]);
				String paysOrigine = nextLine[6];
				String adresse = nextLine[7];

				Artiste artiste = new Artiste(id, nom, prenom, dateNaissance, pseudoNom, estMort, paysOrigine, adresse);
				artistes.add(artiste);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			throw new RuntimeException(e);
		}

		return artistes;
	}
}
