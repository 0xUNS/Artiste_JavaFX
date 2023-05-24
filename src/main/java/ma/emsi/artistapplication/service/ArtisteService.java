package ma.emsi.artistapplication.service;

import java.util.List;

import ma.emsi.artistapplication.dao.ArtisteDao;
import ma.emsi.artistapplication.dao.impl.ArtisteDaoImp;
import ma.emsi.artistapplication.entities.Artiste;

public class ArtisteService {
	private ArtisteDao artisteDao = new ArtisteDaoImp();

	public List<Artiste> findAll() {
		return artisteDao.findAll();
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
		SpreadsheetWrite.export(path);
	}

	public void importerDepuisExcel(String path) throws Exception {
		SpreadsheetRead.importer(path);
	}


	public void exporterVersTxt(String path) {

	}

	public void importerDepuisTxt(String path) {

	}
}
