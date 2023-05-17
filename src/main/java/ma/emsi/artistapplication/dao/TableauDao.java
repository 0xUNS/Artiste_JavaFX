package ma.emsi.artistapplication.dao;

import java.util.List;

import ma.emsi.artistapplication.entities.Artiste;
import ma.emsi.artistapplication.entities.Tableau;

public interface TableauDao {
	void insert(Tableau tableau);

	void update(Tableau tableau);

	void deleteById(Integer id);

	Tableau findById(Integer id);

	List<Tableau> findAll();

	List<Tableau> findByArtiste(Artiste artiste);
}
