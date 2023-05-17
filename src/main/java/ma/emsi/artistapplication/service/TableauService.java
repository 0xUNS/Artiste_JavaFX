package ma.emsi.artistapplication.service;

import java.util.List;

import ma.emsi.artistapplication.dao.TableauDao;
import ma.emsi.artistapplication.dao.impl.TableauDaoImp;
import ma.emsi.artistapplication.entities.Tableau;

public class TableauService {
	private TableauDao tableauDao = new TableauDaoImp();

	public List<Tableau> findAll() {
		return tableauDao.findAll();
	}

	public void save(Tableau tableau) {
	
			tableauDao.insert(tableau);
		
	}
	public void update(Tableau tableau) {
		
			tableauDao.update(tableau);
		
	}
	public void remove(Tableau tableau) {
		tableauDao.deleteById(tableau.getId());
	}
}
