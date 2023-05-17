package ma.emsi.artistapplication.dao;

import java.util.List;

import ma.emsi.artistapplication.entities.Artiste;

public interface ArtisteDao {
	void insert(Artiste artiste);

	void update(Artiste artiste);

	void deleteById(Integer id);

	Artiste findById(Integer id);

	List<Artiste> findAll();
}
