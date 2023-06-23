//package fr.helpad.entity;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.OneToOne;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//
//
//@Entity
//@IdClass(Prescription.class)
//public class Prescription implements Serializable {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 6L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID_PRESCRIPTION")
//	private Long id;
//	@Autowired
//	@OneToOne
//	private WebGouvMedic medicament;
//	@Autowired
//	@OneToOne
//	private Pensionnaire pensionnaire;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate dateDebutTraitement;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate dateFinTraitement;
//	private String posologie;
//	@Column(name = "QUANTITE_PAR_PRISE", unique = false, nullable = false)
//	private int quantiteParPrise;
//	@Column(name = "PRISE_PAR_JOUR", unique = false, nullable = false)
//	private float priseParJour;
//	@Column(name = "JOUR_RESTANT", unique = false, nullable = false)
//	private int jourRestant;
//	private Moment moment;
//	private Regularite regularite;
//
//	enum Moment {
//		MATIN, MIDI, SOIR, MATIN_SOIR, MATIN_MIDI_SOIR, IRREGULIER
//	}
//
//	enum Regularite {
//		TOUS_LES_JOURS, SI_CRISE, IRREGULIER
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(dateDebutTraitement, dateFinTraitement, id, medicament, pensionnaire, posologie,
//				quantiteParPrise);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Prescription other = (Prescription) obj;
//		return Objects.equals(dateDebutTraitement, other.dateDebutTraitement)
//				&& Objects.equals(dateFinTraitement, other.dateFinTraitement) && Objects.equals(id, other.id)
//				&& Objects.equals(medicament, other.medicament) && Objects.equals(pensionnaire, other.pensionnaire)
//				&& Objects.equals(posologie, other.posologie) && quantiteParPrise == other.quantiteParPrise;
//	}
//
//	@Override
//	public String toString() {
//		return "Prescription [id=" + id + ", medicament=" + medicament + ", pensionnaire=" + pensionnaire
//				+ ", dateDebutTraitement=" + dateDebutTraitement + ", dateFinTraitement=" + dateFinTraitement
//				+ ", posologie=" + posologie + ", quantiteParPrise=" + quantiteParPrise + "]";
//	}
//
//	public Prescription() {
//		super();
//	}
//
//	public Prescription(WebGouvMedic medicament, Pensionnaire pensionnaire, LocalDate dateDebutTraitement,
//			LocalDate dateFinTraitement, String posologie, int quantiteParPrise,
//			float priseParJour, int jourRestant, Moment moment, Regularite regularite) {
//		super();
//		this.medicament = medicament;
//		this.pensionnaire = pensionnaire;
//		this.dateDebutTraitement = dateDebutTraitement;
//		this.dateFinTraitement = dateFinTraitement;
//		this.posologie = posologie;
//		this.quantiteParPrise = quantiteParPrise;
//		this.priseParJour = priseParJour;
//		this.jourRestant = jourRestant;
//		this.moment = moment;
//		this.regularite = regularite;
//	}
//
//	public Prescription(Long id, WebGouvMedic medicament, Pensionnaire pensionnaire, LocalDate dateDebutTraitement,
//			LocalDate dateFinTraitement, String posologie, int quantiteParBoite, int quantiteParPrise,
//			float priseParJour, int jourRestant, Moment moment, Regularite regularite) {
//		super();
//		this.id = id;
//		this.medicament = medicament;
//		this.pensionnaire = pensionnaire;
//		this.dateDebutTraitement = dateDebutTraitement;
//		this.dateFinTraitement = dateFinTraitement;
//		this.posologie = posologie;
//		this.quantiteParPrise = quantiteParPrise;
//		this.priseParJour = priseParJour;
//		this.jourRestant = jourRestant;
//		this.moment = moment;
//		this.regularite = regularite;
//	}
//
//	public Regularite getRegularite() {
//		return regularite;
//	}
//
//	public Moment getMoment() {
//		return moment;
//	}
//
//	public void setMoment(Moment moment) {
//		this.moment = moment;
//	}
//
//	public void setRegularite(Regularite regularite) {
//		this.regularite = regularite;
//	}
//
//	public int getJourRestant() {
//		return jourRestant;
//	}
//
//	public void setJourRestant(int jourRestant) {
//		this.jourRestant = jourRestant;
//	}
//
//	public float getPriseParJour() {
//		return priseParJour;
//	}
//
//	public void setPriseParJour(float priseParJour) {
//		this.priseParJour = priseParJour;
//	}
//
//	/**
//	 * @return the serialversionuid
//	 */
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	/**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//
//	/**
//	 * @return the medicament
//	 */
//	public WebGouvMedic getMedicament() {
//		return medicament;
//	}
//
//	/**
//	 * @return the pensionnaire
//	 */
//	public Pensionnaire getPensionnaire() {
//		return pensionnaire;
//	}
//
//	/**
//	 * @return the dateDebutTraitement
//	 */
//	public LocalDate getDateDebutTraitement() {
//		return dateDebutTraitement;
//	}
//
//	/**
//	 * @return the dateFinTraitement
//	 */
//	public LocalDate getDateFinTraitement() {
//		return dateFinTraitement;
//	}
//
//	/**
//	 * @return the posologie
//	 */
//	public String getPosologie() {
//		return posologie;
//	}
//
//	/**
//	 * @return the quantiteParPrise
//	 */
//	public int getQuantiteParPrise() {
//		return quantiteParPrise;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	/**
//	 * @param medicament the medicament to set
//	 */
//	public void setMedicament(WebGouvMedic medicament) {
//		this.medicament = medicament;
//	}
//
//	/**
//	 * @param pensionnaire the pensionnaire to set
//	 */
//	public void setPensionnaire(Pensionnaire pensionnaire) {
//		this.pensionnaire = pensionnaire;
//	}
//
//	/**
//	 * @param dateDebutTraitement the dateDebutTraitement to set
//	 */
//	public void setDateDebutTraitement(LocalDate dateDebutTraitement) {
//		this.dateDebutTraitement = dateDebutTraitement;
//	}
//
//	/**
//	 * @param dateFinTraitement the dateFinTraitement to set
//	 */
//	public void setDateFinTraitement(LocalDate dateFinTraitement) {
//		this.dateFinTraitement = dateFinTraitement;
//	}
//
//	/**
//	 * @param posologie the posologie to set
//	 */
//	public void setPosologie(String posologie) {
//		this.posologie = posologie;
//	}
//
//	/**
//	 * @param quantiteParPrise the quantiteParPrise to set
//	 */
//	public void setQuantiteParPrise(int quantiteParPrise) {
//		this.quantiteParPrise = quantiteParPrise;
//	}
//
//}
//
