package fr.helpad.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRISE_MEDICAMENT")
@IdClass(PriseMedicament.class)
public class PriseMedicament implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	@Id
	private Long idPensionnaire;
	@Id
	private Long idMedicament;
	private Long idInfirmiere;
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@OrderBy
	private Date heure;

	public Long getIdPensionnaire() {
		return idPensionnaire;
	}

	public void setIdPensionnaire(Long idPensionnaire) {
		this.idPensionnaire = idPensionnaire;
	}

	public Long getIdMedicament() {
		return idMedicament;
	}

	public void setIdMedicament(Long idMedicament) {
		this.idMedicament = idMedicament;
	}

	public Long getIdInfirmiere() {
		return idInfirmiere;
	}

	public void setIdInfirmiere(Long idInfirmiere) {
		this.idInfirmiere = idInfirmiere;
	}

	public Date getHeure() {
		return heure;
	}

	public void setHeure(Date heure) {
		this.heure = heure;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PriseMedicament(Long idPensionnaire, Long idMedicament, Long idInfirmiere, Date heure) {
		super();
		this.idPensionnaire = idPensionnaire;
		this.idMedicament = idMedicament;
		this.idInfirmiere = idInfirmiere;
		this.heure = heure;
	}

	public PriseMedicament(Long idInfirmiere) {
		super();
		this.idInfirmiere = idInfirmiere;
	}

	public PriseMedicament() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(heure, idInfirmiere, idMedicament, idPensionnaire);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriseMedicament other = (PriseMedicament) obj;
		return Objects.equals(heure, other.heure) && Objects.equals(idInfirmiere, other.idInfirmiere)
				&& Objects.equals(idMedicament, other.idMedicament)
				&& Objects.equals(idPensionnaire, other.idPensionnaire);
	}

	@Override
	public String toString() {
		return "PriseMedicament [idPensionnaire=" + idPensionnaire + ", idMedicament=" + idMedicament
				+ ", idInfirmiere=" + idInfirmiere + ", heure=" + heure + "]";
	}

}
