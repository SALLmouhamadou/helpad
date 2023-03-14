package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Justificatif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_JUSTIFICATIF")
	private Long IdJustificatif;
	@OneToOne
	private Candidature candidature;
	private String chemin;

	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}

	public Justificatif() {
		super();
	}

	public Justificatif(Candidature candidature, String chemin) {
		super();
		this.candidature = candidature;
		this.chemin = chemin;
	}

	public Justificatif(Long idJustificatif, Candidature candidature, String chemin) {
		super();
		IdJustificatif = idJustificatif;
		this.candidature = candidature;
		this.chemin = chemin;
	}

	public Long getIdJustificatif() {
		return IdJustificatif;
	}

	public void setIdJustificatif(Long idJustificatif) {
		IdJustificatif = idJustificatif;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	@Override
	public String toString() {
		return "Justificatif [IdJustificatif=" + IdJustificatif + ", candidature=" + candidature + ", chemin=" + chemin
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(IdJustificatif, candidature, chemin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Justificatif other = (Justificatif) obj;
		return Objects.equals(IdJustificatif, other.IdJustificatif) && Objects.equals(candidature, other.candidature)
				&& Objects.equals(chemin, other.chemin);
	}

}
