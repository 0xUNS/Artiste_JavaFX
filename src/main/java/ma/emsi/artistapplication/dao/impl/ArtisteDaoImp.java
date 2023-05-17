package ma.emsi.artistapplication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.emsi.artistapplication.dao.ArtisteDao;
import ma.emsi.artistapplication.entities.Artiste;

public class ArtisteDaoImp implements ArtisteDao {

	private Connection conn= DB.getConnection();

	@Override
	public void insert(Artiste artiste) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO artiste (nom,prenom,pseudoNom,dateNaissance,estMort,paysOrigine,adresse) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, artiste.getNom());
			ps.setString(2, artiste.getPrenom());
			ps.setString(3, artiste.getPseudoNom());
			ps.setDate(4, new java.sql.Date(artiste.getDateNaissance().getTime()));
			ps.setBoolean(5, artiste.isEstMort());
			ps.setString(6, artiste.getPaysOrigine());
			ps.setString(7, artiste.getAdresse());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					artiste.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un artiste");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Artiste artiste) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE artiste SET nom = ?, prenom=?,pseudoNom=?,dateNaissance=?,estMort=?,paysOrigine=?,adresse=? WHERE id = ?");

			ps.setString(1, artiste.getNom());
			ps.setString(2, artiste.getPrenom());
			ps.setString(3, artiste.getPseudoNom());
			ps.setDate(4, new java.sql.Date(artiste.getDateNaissance().getTime()));
			ps.setBoolean(5, artiste.isEstMort());
			ps.setString(6, artiste.getPaysOrigine());
			ps.setString(7, artiste.getAdresse());
			ps.setInt(8, artiste.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un artiste");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM artiste WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un artiste");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public Artiste findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM artiste WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Artiste artiste = new Artiste();

				artiste.setId(rs.getInt("id"));
				artiste.setNom(rs.getString("nom"));
				artiste.setPrenom(rs.getString("prenom"));
				artiste.setPseudoNom(rs.getString("pseudoNom"));
				artiste.setDateNaissance(rs.getDate("dateNaissance"));
				artiste.setEstMort(rs.getBoolean("estMort"));
				artiste.setPaysOrigine(rs.getString("paysOrigine"));
				artiste.setAdresse(rs.getString("adresse"));
			

				return artiste;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver un artiste");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	@Override
	public List<Artiste> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM artiste");
			rs = ps.executeQuery();

			List<Artiste> listArtiste = new ArrayList<>();

			while (rs.next()) {
				Artiste artiste = new Artiste();

				artiste.setId(rs.getInt("id"));
				artiste.setNom(rs.getString("nom"));
				artiste.setPrenom(rs.getString("prenom"));
				artiste.setPseudoNom(rs.getString("pseudoNom"));
				artiste.setDateNaissance(rs.getDate("dateNaissance"));
				artiste.setEstMort(rs.getBoolean("estMort"));
				artiste.setPaysOrigine(rs.getString("paysOrigine"));
				artiste.setAdresse(rs.getString("adresse"));

				listArtiste.add(artiste);
			}

			return listArtiste;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner un artiste");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

}
