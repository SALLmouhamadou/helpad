package fr.helpad.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@IdClass(Prescription.class)
public class Prescription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	@Autowired
	@Id
	@Column(name = "ID_MEDICAMENT", unique = true, nullable = false)
	private Long idMedicament;
	@Autowired
	@Id
	@Column(name = "ID_PERSONNE", unique = true, nullable = false)
	private Long idPensionnaire;
	@Temporal(TemporalType.DATE)
	private Date dateDebutTraitement;
	@Temporal(TemporalType.DATE)
	private Date dateFinTraitement;
	private String posologie;

	public Long getIdMedicament() {
		return idMedicament;
	}

	public void setIdMedicament(Long idMedicament) {
		this.idMedicament = idMedicament;
	}

	public Long getIdPensionnaire() {
		return idPensionnaire;
	}

	public void setIdPensionnaire(Long idPensionnaire) {
		this.idPensionnaire = idPensionnaire;
	}

	public Date getDateDebutTraitement() {
		return dateDebutTraitement;
	}

	public void setDateDebutTraitement(Date dateDebutTraitement) {
		this.dateDebutTraitement = dateDebutTraitement;
	}

	public Date getDateFinTraitement() {
		return dateFinTraitement;
	}

	public void setDateFinTraitement(Date dateFinTraitement) {
		this.dateFinTraitement = dateFinTraitement;
	}

	public String getPosologie() {
		return posologie;
	}

	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Prescription [idMedicament=" + idMedicament + ", idPensionnaire=" + idPensionnaire
				+ ", dateDebutTraitement=" + dateDebutTraitement + ", dateFinTraitement=" + dateFinTraitement
				+ ", posologie=" + posologie + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateDebutTraitement, dateFinTraitement, idMedicament, idPensionnaire, posologie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prescription other = (Prescription) obj;
		return Objects.equals(dateDebutTraitement, other.dateDebutTraitement)
				&& Objects.equals(dateFinTraitement, other.dateFinTraitement)
				&& Objects.equals(idMedicament, other.idMedicament)
				&& Objects.equals(idPensionnaire, other.idPensionnaire) && Objects.equals(posologie, other.posologie);
	}

	public Prescription(Long idMedicament, Long idPensionnaire, Date dateDebutTraitement, Date dateFinTraitement,
			String posologie) {
		super();
		this.idMedicament = idMedicament;
		this.idPensionnaire = idPensionnaire;
		this.dateDebutTraitement = dateDebutTraitement;
		this.dateFinTraitement = dateFinTraitement;
		this.posologie = posologie;
	}

	public Prescription() {
		super();
	}

}
