package ma.emsi.artistapplication.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Tableau implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nom;
	private String description;
	private String genre;
	private  double prix ;
	private  boolean estVendu ;
	private  Date dateCreation ;
	private Artiste artiste;
	public Tableau(Integer id, String nom, String description, String genre, double prix, boolean estVendu,
			Date dateCreation, Artiste artiste) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.genre = genre;
		this.prix = prix;
		this.estVendu = estVendu;
		this.dateCreation = dateCreation;
		this.artiste = artiste;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public boolean isEstVendu() {
		return estVendu;
	}
	public Tableau() {
		super();
	}
	public void setEstVendu(boolean estVendu) {
		this.estVendu = estVendu;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Artiste getArtiste() {
		return artiste;
	}
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	@Override
	public int hashCode() {
		return Objects.hash(artiste, dateCreation, description, estVendu, genre, id, nom, prix);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tableau other = (Tableau) obj;
		return Objects.equals(artiste, other.artiste) && Objects.equals(dateCreation, other.dateCreation)
				&& Objects.equals(description, other.description) && estVendu == other.estVendu
				&& Objects.equals(genre, other.genre) && Objects.equals(id, other.id) && Objects.equals(nom, other.nom)
				&& Double.doubleToLongBits(prix) == Double.doubleToLongBits(other.prix);
	}
	@Override
	public String toString() {
		return "Tableau [id=" + id + ", nom=" + nom + ", description=" + description + ", genre=" + genre + ", prix="
				+ prix + ", estVendu=" + estVendu + ", dateCreation=" + dateCreation + ", artiste=" + artiste + "]";
	}

	
}
