package fr.helpad.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Ordonnance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDONNANCE")
	private Long idOrdonnance;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@OrderBy
	private LocalDate jourVisite;
	@Autowired
	@OneToOne
	private Medecin medecin;
	@Autowired
	@OneToOne
	private Pensionnaire pensionnaire;
	private String chemin;

	@Override
	public String toString() {
		return "Ordonnance [idOrdonnance=" + idOrdonnance + ", jourVisite=" + jourVisite + ", medecin=" + medecin
				+ ", pensionnaire=" + pensionnaire + ", chemin=" + chemin + "]";
	}

	public Long getIdOrdonnance() {
		return idOrdonnance;
	}

	public void setIdOrdonnance(Long idOrdonnance) {
		this.idOrdonnance = idOrdonnance;
	}

	/**
	 * @return the jourVisite
	 */
	public LocalDate getJourVisite() {
		return jourVisite;
	}

	/**
	 * @param jourVisite the jourVisite to set
	 */
	public void setJourVisite(LocalDate jourVisite) {
		this.jourVisite = jourVisite;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Pensionnaire getPensionnaire() {
		return pensionnaire;
	}

	public void setPensionnaire(Pensionnaire pensionnaire) {
		this.pensionnaire = pensionnaire;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Ordonnance(LocalDate jourVisite, Medecin medecin, Pensionnaire pensionnaire, String chemin) {
		super();
		this.jourVisite = jourVisite;
		this.medecin = medecin;
		this.pensionnaire = pensionnaire;
		this.chemin = chemin;
	}

	public Ordonnance(Long idOrdonnance, LocalDate jourVisite, Medecin medecin, Pensionnaire pensionnaire,
			String chemin) {
		super();
		this.idOrdonnance = idOrdonnance;
		this.jourVisite = jourVisite;
		this.medecin = medecin;
		this.pensionnaire = pensionnaire;
		this.chemin = chemin;
	}

	public Ordonnance() {
		super();
	}

}
