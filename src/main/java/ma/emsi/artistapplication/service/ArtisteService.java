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
		
	}
	public void update(Artiste artiste) {
		
			artisteDao.update(artiste);
		
	}
	public void remove(Artiste artiste) {
		artisteDao.deleteById(artiste.getId());
	}
	
	
	
	public void exporterVersExcel(String path) {
		
	}
	
	public void importerDepuisExcel(String path) {
		
	}
	

	public void exporterVersTxt(String path) {
		
	}
	
	public void importerDepuisTxt(String path) {
		
	}
}
