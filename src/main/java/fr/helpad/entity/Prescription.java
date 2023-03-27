package fr.helpad.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@IdClass(Prescription.class)
public class Prescription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRESCRIPTION")
	private Long id;
	@Autowired
	@Column(name = "ID_MEDICAMENT", unique = true, nullable = false)
	private Medicament medicament;
	@Autowired
	@Column(name = "ID_PERSONNE", unique = true, nullable = false)
	private Pensionnaire pensionnaire;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebutTraitement;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFinTraitement;
	private String posologie;
	private int quantiteParPrise;

	@Override
	public int hashCode() {
		return Objects.hash(dateDebutTraitement, dateFinTraitement, id, medicament, pensionnaire, posologie,
				quantiteParPrise);
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
				&& Objects.equals(dateFinTraitement, other.dateFinTraitement) && Objects.equals(id, other.id)
				&& Objects.equals(medicament, other.medicament) && Objects.equals(pensionnaire, other.pensionnaire)
				&& Objects.equals(posologie, other.posologie) && quantiteParPrise == other.quantiteParPrise;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", medicament=" + medicament + ", pensionnaire=" + pensionnaire
				+ ", dateDebutTraitement=" + dateDebutTraitement + ", dateFinTraitement=" + dateFinTraitement
				+ ", posologie=" + posologie + ", quantiteParPrise=" + quantiteParPrise + "]";
	}

	public Prescription() {
		super();
	}

	public Prescription(Medicament medicament, Pensionnaire pensionnaire, LocalDate dateDebutTraitement,
			LocalDate dateFinTraitement, String posologie, int quantiteParPrise) {
		super();
		this.medicament = medicament;
		this.pensionnaire = pensionnaire;
		this.dateDebutTraitement = dateDebutTraitement;
		this.dateFinTraitement = dateFinTraitement;
		this.posologie = posologie;
		this.quantiteParPrise = quantiteParPrise;
	}

	public Prescription(Long id, Medicament medicament, Pensionnaire pensionnaire, LocalDate dateDebutTraitement,
			LocalDate dateFinTraitement, String posologie, int quantiteParPrise) {
		super();
		this.id = id;
		this.medicament = medicament;
		this.pensionnaire = pensionnaire;
		this.dateDebutTraitement = dateDebutTraitement;
		this.dateFinTraitement = dateFinTraitement;
		this.posologie = posologie;
		this.quantiteParPrise = quantiteParPrise;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the medicament
	 */
	public Medicament getMedicament() {
		return medicament;
	}

	/**
	 * @return the pensionnaire
	 */
	public Pensionnaire getPensionnaire() {
		return pensionnaire;
	}

	/**
	 * @return the dateDebutTraitement
	 */
	public LocalDate getDateDebutTraitement() {
		return dateDebutTraitement;
	}

	/**
	 * @return the dateFinTraitement
	 */
	public LocalDate getDateFinTraitement() {
		return dateFinTraitement;
	}

	/**
	 * @return the posologie
	 */
	public String getPosologie() {
		return posologie;
	}

	/**
	 * @return the quantiteParPrise
	 */
	public int getQuantiteParPrise() {
		return quantiteParPrise;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param medicament the medicament to set
	 */
	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	/**
	 * @param pensionnaire the pensionnaire to set
	 */
	public void setPensionnaire(Pensionnaire pensionnaire) {
		this.pensionnaire = pensionnaire;
	}

	/**
	 * @param dateDebutTraitement the dateDebutTraitement to set
	 */
	public void setDateDebutTraitement(LocalDate dateDebutTraitement) {
		this.dateDebutTraitement = dateDebutTraitement;
	}

	/**
	 * @param dateFinTraitement the dateFinTraitement to set
	 */
	public void setDateFinTraitement(LocalDate dateFinTraitement) {
		this.dateFinTraitement = dateFinTraitement;
	}

	/**
	 * @param posologie the posologie to set
	 */
	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}

	/**
	 * @param quantiteParPrise the quantiteParPrise to set
	 */
	public void setQuantiteParPrise(int quantiteParPrise) {
		this.quantiteParPrise = quantiteParPrise;
	}

}
