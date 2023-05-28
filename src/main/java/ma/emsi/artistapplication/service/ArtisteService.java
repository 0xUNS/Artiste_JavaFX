package ma.emsi.artistapplication.service;

import java.util.List;

import ma.emsi.artistapplication.dao.ArtisteDao;
import ma.emsi.artistapplication.dao.impl.ArtisteDaoImp;
import ma.emsi.artistapplication.entities.Artiste;

public class ArtisteService {
	private static final ArtisteDao artisteDao = new ArtisteDaoImp();

	public List<Artiste> findAll() {
		return artisteDao.findAll();
	}

	public boolean artisteInList(String nom, String prenom, String pseudoNom) {
		for (Artiste artiste : findAll())
			if (artiste.getNom().equals(nom) && artiste.getPrenom().equals(prenom) && artiste.getPseudoNom().equals(pseudoNom))
				return true;
		return false;
	}

	public void save(Artiste artiste) {
		artisteDao.insert(artiste);
		System.out.println("Artist Saved");
	}
	public void update(Artiste artiste) {
		artisteDao.update(artiste);
		System.out.println("Artist Updated");
	}
	public void remove(Artiste artiste) {
		artisteDao.deleteById(artiste.getId());
		System.out.println("Artist Removed");
	}

	public void exporterVersExcel(String path) throws Exception {
		SpreadsheetService.exporter(path);
	}

	public void importerDepuisExcel(String path) throws Exception {
		SpreadsheetService.importer(path);
	}


	public void exporterVersJson(String path) {
		JsonService.exporter(path);
	}

	public void importerDepuisJson(String path) {
		JsonService.importer(path);
	}

	public void exporterVersCsv(String path) {
		CsvService.exporter(path);
	}

	public void importerDepuisCsv(String path) {
		CsvService.impoter(path);
	}

	public void exportData(String path) throws Exception {
		if (path.startsWith("/"))
			path = path.substring(1);
		if (path.endsWith(".xlsx"))
			exporterVersExcel(path);
		else if (path.endsWith(".json"))
			exporterVersJson(path);
		else if (path.endsWith(".csv"))
			exporterVersCsv(path);
		else if (path.endsWith(".txt"))
			exporterVersCsv(path);
		else if (path.length() == 0)
			exporterVersCsv("/Documents/data.txt");
		else exporterVersCsv(path+".txt");
	}

	public void importerData(String path) throws Exception {
		if (path.startsWith("/"))
			path = path.substring(1);
		if (path.endsWith(".xlsx"))
			importerDepuisExcel(path);
		else if (path.endsWith(".json"))
			importerDepuisJson(path);
		else if (path.endsWith(".csv"))
			importerDepuisCsv(path);
		else if (path.endsWith(".txt"))
			importerDepuisCsv(path);
	}
}
