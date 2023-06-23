package fr.helpad.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import fr.helpad.service.Encrypt;

@Entity
@Table(name = "CANDIDAT")
@PrimaryKeyJoinColumn(name = "idPersonne")
public class Candidat extends Personne {
	private String civilite;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_Naissance")
	private LocalDate dateNaissance;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_entree")
	private LocalDate dateEntree;
	@Convert(converter = Encrypt.class)
	@Column(name = "numero_security_social", unique = true)
	private String numeroSecuriteSocial;
	@Convert(converter = Encrypt.class)
	@Column(name = "numero_de_caf", unique=true)
	private String numeroDeCaf;
	private double revenu;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Candidature> mesCandidatures = new ArrayList<>();

	public Candidat() {
	}
	
	public Candidat(String nom, String prenom, String telephone, String email, Adresse adresse, String civilite,
			LocalDate dateNaissance, LocalDate dateEntree, String numeroSecuriteSocial, String numeroDeCaf,
			double revenu, List<Candidature> mesCandidatures) {
		super(nom, prenom, telephone, email, adresse);
		this.civilite = civilite;
		this.dateNaissance = dateNaissance;
		this.dateEntree = dateEntree;
		this.numeroSecuriteSocial = numeroSecuriteSocial;
		this.numeroDeCaf = numeroDeCaf;
		this.revenu = revenu;
		this.mesCandidatures = mesCandidatures;
	}


	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
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
		return mesCandidatures;
	}

	public void setMesCandidatures(List<Candidature> mesCandidatures) {
		this.mesCandidatures = mesCandidatures;
	}
}
