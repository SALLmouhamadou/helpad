package fr.helpad.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CANDIDAT")
@PrimaryKeyJoinColumn(name = "idPersonne")
public class Candidat extends Personne {
	@Column(name = "date_Naissance")
	private LocalDate dateNaissance;
	@Column(name = "date_entree")
	private LocalDate dateEntree;
	@Column(name = "numero_security_social")
	private String numeroSecuriteSocial;
	@Column(name = "numero_de_caf")
	private String numeroDeCaf;
	private double revenu;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Candidature> MesCandidatures;

	public Candidat() {}
	
	public Candidat(String nom, String prenom, String telephone, String email, Adresse adresse, LocalDate dateNaissance, LocalDate dateEntree,
			String numeroSecuriteSocial, String numeroDeCaf, double revenu, List<Candidature> mesCandidatures) {
		super(nom, prenom, telephone, email, adresse);

	public Candidat(String nom, String prenom, String telephone, Adresse adresse, String email, String password,
			LocalDate dateNaissance, LocalDate dateEntree, String numeroSecuriteSocial, String numeroDeCaf,
			double revenu, List<Candidature> mesCandidatures) {
		super(nom, prenom, telephone, adresse, email, password);
	public Candidat() {
		super();
	}

	

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public LocalDate getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}

	public String getNumeroSecuriteSocial() {
		return numeroSecuriteSocial;
	}

	public void setNumeroSecuriteSocial(String numeroSecuriteSocial) {
		this.numeroSecuriteSocial = numeroSecuriteSocial;
	}

	public String getNumeroDeCaf() {
		return numeroDeCaf;
	}

	public void setNumeroDeCaf(String numeroDeCaf) {
		this.numeroDeCaf = numeroDeCaf;
	}

	public double getRevenu() {
		return revenu;
	}

	public void setRevenu(double revenu) {
		this.revenu = revenu;
	}

	public List<Candidature> getMesCandidatures() {
		return MesCandidatures;
	}

	public void setMesCandidatures(List<Candidature> mesCandidatures) {
		MesCandidatures = mesCandidatures;
	}
}
