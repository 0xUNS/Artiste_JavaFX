package ma.emsi.artistapplication.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Artiste implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String pseudoNom;
	private boolean estMort;
	private String paysOrigine;
	private String adresse;

	public Artiste(Integer id, String nom, String prenom, LocalDate dateNaissance, String pseudoNom, boolean estMort,
			String paysOrigine, String adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.pseudoNom = pseudoNom;
		this.estMort = estMort;
		this.paysOrigine = paysOrigine;
		this.adresse = adresse;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPseudoNom() {
		return pseudoNom;
	}
	public void setPseudoNom(String pseudoNom) {
		this.pseudoNom = pseudoNom;
	}
	public boolean isEstMort() {
		return estMort;
	}
	public void setEstMort(boolean estMort) {
		this.estMort = estMort;
	}
	public String getPaysOrigine() {
		return paysOrigine;
	}
	public void setPaysOrigine(String paysOrigine) {
		this.paysOrigine = paysOrigine;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public int hashCode() {
		return Objects.hash(adresse, dateNaissance, estMort, id, nom, paysOrigine, prenom, pseudoNom);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artiste other = (Artiste) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(dateNaissance, other.dateNaissance)
				&& estMort == other.estMort && Objects.equals(id, other.id) && Objects.equals(nom, other.nom)
				&& Objects.equals(paysOrigine, other.paysOrigine) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(pseudoNom, other.pseudoNom);
	}
	@Override
	public String toString() {
		return "Artiste [id=" + id + ", nom=" + nom +  "]";
	}
	public Artiste() {
		super();
	}

	
}