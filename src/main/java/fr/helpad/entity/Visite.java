package fr.helpad.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Visite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VISITE")
	private Long idVisite;
	@Temporal(TemporalType.DATE)
	@OrderBy
	private Date jour;
	@OneToOne(cascade=CascadeType.MERGE)
	private Medecin medecin;
	@OneToOne(cascade=CascadeType.MERGE)
	private Pensionnaire pensionnaire;

	public Visite(Long idVisite, Date jour, Medecin medecin, Pensionnaire pensionnaire) {
		super();
		this.idVisite = idVisite;
		this.jour = jour;
		this.medecin = medecin;
		this.pensionnaire = pensionnaire;
	}

	public Visite(Date jour, Medecin medecin, Pensionnaire pensionnaire) {
		super();
		this.jour = jour;
		this.medecin = medecin;
		this.pensionnaire = pensionnaire;
	}

	public Visite() {
		super();
	}

	@Override
	public String toString() {
		return "Visite [idVisite=" + idVisite + ", jour=" + jour + ", medecin=" + medecin + ", pensionnaire="
				+ pensionnaire + "]";
	}

	public Long getIdVisite() {
		return idVisite;
	}

	public void setIdVisite(Long idVisite) {
		this.idVisite = idVisite;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
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

	@Override
	public int hashCode() {
		return Objects.hash(idVisite, jour, medecin, pensionnaire);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visite other = (Visite) obj;
		return Objects.equals(idVisite, other.idVisite) && Objects.equals(jour, other.jour)
				&& Objects.equals(medecin, other.medecin) && Objects.equals(pensionnaire, other.pensionnaire);
	}

}
