package ma.emsi.artistapplication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.emsi.artistapplication.dao.TableauDao;
import ma.emsi.artistapplication.entities.Artiste;
import ma.emsi.artistapplication.entities.Tableau;

public class TableauDaoImp implements TableauDao {

	private Connection conn= DB.getConnection();

	

	@Override
	public void insert(Tableau tableau) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"INSERT INTO tableau (nom, 	description, genre, prix,	estVendu,dateCreation, ArtisteId) VALUES (?, ?, ?, ?, ?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, tableau.getNom());
			ps.setString(2, tableau.getDescription());
			ps.setString(3, tableau.getGenre());
			ps.setDouble(4, tableau.getPrix());
			ps.setBoolean(5, tableau.isEstVendu());
			ps.setDate(6, new java.sql.Date(tableau.getDateCreation().getTime()));
			ps.setInt(7, tableau.getArtiste().getId());
			

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					tableau.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyé");;
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un vendeur");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Tableau tableau) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"UPDATE tableau SET nom = ?, description = ?, genre = ?, prix = ?,estVendu= ?,dateCreation= ?, ArtisteId = ? WHERE id = ?");

			ps.setString(1, tableau.getNom());
			ps.setString(2, tableau.getDescription());
			ps.setString(3, tableau.getGenre());
			ps.setDouble(4, tableau.getPrix());
			ps.setBoolean(5, tableau.isEstVendu());
			ps.setDate(6, new java.sql.Date(tableau.getDateCreation().getTime()));
			ps.setInt(7, tableau.getArtiste().getId());
			ps.setInt(8, tableau.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un vendeur");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM tableau WHERE id = ?");

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un vendeur");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Tableau findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT t.*, a.nom AS ArtistNom FROM tableau t INNER JOIN artiste a ON t.ArtisteId = a.id WHERE t.id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Artiste artiste = instantiateArtiste(rs);
				Tableau tableau = instantiateTableau(rs, artiste);

				return tableau;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver le vendeur");
			return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Tableau> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT t.*, a.nom as ArtistNom FROM tableau t INNER JOIN artiste a ON t.ArtisteId = a.id ORDER BY t.nom");
			rs = ps.executeQuery();
			List<Tableau> list = new ArrayList<>();
			Map<Integer, Artiste> map = new HashMap<>();

			while (rs.next()) {
				Artiste dep = map.get(rs.getInt("ArtisteId"));

				if (dep == null) {
					dep = instantiateArtiste(rs);

					map.put(rs.getInt("ArtisteId"), dep);
				}

				Tableau tableau = instantiateTableau(rs, dep);

				list.add(tableau);
			}

			return list;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner les vendeurs");
		return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Tableau> findByArtiste(Artiste artiste) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT t.*, a.nom as ArtistNom FROM tableau t INNER JOIN artiste a ON t.ArtisteId = a.id WHERE t.ArtisteId = ? ORDER BY t.nom");

			ps.setInt(1, artiste.getId());

			rs = ps.executeQuery();
			List<Tableau> list = new ArrayList<>();
			Map<Integer, Artiste> map = new HashMap<>();

			while (rs.next()) {
				Artiste dep = map.get(rs.getInt("ArtisteId"));

				if (dep == null) {
					dep = instantiateArtiste(rs);

					map.put(rs.getInt("ArtisteId"), dep);
				}

				Tableau tableau = instantiateTableau(rs, dep);

				list.add(tableau);
			}

			return list;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner les vendeurs d'un département donné");
		return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Tableau instantiateTableau(ResultSet rs, Artiste artiste) throws SQLException {
		Tableau tableau = new Tableau();

		tableau.setId(rs.getInt("id"));
		tableau.setNom(rs.getString("nom"));
		tableau.setDescription(rs.getString("description"));
		tableau.setGenre(rs.getString("genre"));
		tableau.setPrix(rs.getDouble("prix"));
		tableau.setEstVendu(rs.getBoolean("estVendu"));
		tableau.setDateCreation(new java.util.Date(rs.getTimestamp("dateCreation").getTime()));
		tableau.setArtiste(artiste);

		return tableau;
	}

	private Artiste instantiateArtiste(ResultSet rs) throws SQLException {
		Artiste artiste = new Artiste();

		artiste.setId(rs.getInt("ArtisteId"));
		artiste.setNom(rs.getString("ArtistNom"));

		return artiste;
	}

}
