package fr.helpad.entity;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;

@Entity
public class WebGouvMedic {
	@Id
	private Long id; // Code CIS
	@OrderBy
	private String nom;
	@Column(name = "FORME_PHARMACEUTIQUE")
	private String formePharmaceutique;
	@Column(name = "VOIE_ADMINISTRATION")
	private String voieAdministration;
	private String autorisation;
	// procedure est un type dans mySQL, génère des erreurs.
	@Column(name = "PROCEDURE_ADMINISTRATIVE")
	private String procedureAdministrative;
	private boolean commercialise;
	@Column(name = "DATE_COMMERCIALISATION")
	private LocalDate dateCommercialisation;
	@Column(name = "STATUT_BDM")
	private String statutBdm;
	@Column(name = "NUMERO_AUTORISATION_EUROPEENNE")
	private String numeroAutorisationEuropeenne;
	private String titulaire;
	@Column(name = "SURVEILLANCE_RENFORCEE")
	private boolean surveillanceRenforcee;
	@Column(name = "LIBELLE_PRESENTATION")
	private String libellePresentation;
	@Column(name = "ETAT_COMMERCIALISATION")
	private String etatCommercialisation;
	@Column(name = "TAUX_REMBOURSEMENT")
	private String tauxRemboursement;
	private float prix;
	@Column(name = "INDICATION_DROIT_REMBOURSEMENT")
	private String indicationDroitRemboursement;
	@Column(name = "ELEMENT_PHARMACEUTIQUE")
	private String elementPharmaceutique;
	@Column(name = "CODE_SUBSTANCE")
	private String codeSubstance;
	@Column(name = "DOSAGE_SUBSTANCE")
	private String dosageSubstance;
	@Column(name = "NATURE_COMPOSANT")
	private String natureComposant;
	@Column(name = "REFERENCE_DOSAGE")
	private String referenceDosage;
	@Column(name = "NUMERO_LIAISON_SUBSTANCES")
	private String numeroLiaisonSubstances;
	@Column(name = "CONDITION_PRESCRIPTION_DELIVRANCE")
	private String conditionPrescriptionDelivrance;

	public String getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}

	public String getLibellePresentation() {
		return libellePresentation;
	}

	public void setLibellePresentation(String libellePresentation) {
		this.libellePresentation = libellePresentation;
	}

	public String getEtatCommercialisation() {
		return etatCommercialisation;
	}

	public void setEtatCommercialisation(String etatCommercialisation) {
		this.etatCommercialisation = etatCommercialisation;
	}

	public String getTauxRemboursement() {
		return tauxRemboursement;
	}

	public void setTauxRemboursement(String tauxRemboursement) {
		this.tauxRemboursement = tauxRemboursement;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getIndicationDroitRemboursement() {
		return indicationDroitRemboursement;
	}

	public void setIndicationDroitRemboursement(String indicationDroitRemboursement) {
		this.indicationDroitRemboursement = indicationDroitRemboursement;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the formePharmaceutique
	 */
	public String getFormePharmaceutique() {
		return formePharmaceutique;
	}

	/**
	 * @return the voieAdministration
	 */
	public String getVoieAdministration() {
		return voieAdministration;
	}

	/**
	 * @return the autorisation
	 */
	public String getAutorisation() {
		return autorisation;
	}

	/**
	 * @return the procedure
	 */
	public String getProcedure() {
		return procedureAdministrative;
	}

	/**
	 * @return the commercialise
	 */
	public boolean isCommercialise() {
		return commercialise;
	}

	/**
	 * @return the dateCommercialisation
	 */
	public LocalDate getDateCommercialisation() {
		return dateCommercialisation;
	}

	/**
	 * @return the statutBdm
	 */
	public String getStatutBdm() {
		return statutBdm;
	}

	/**
	 * @return the numeroAutorisationEuropeenne
	 */
	public String getNumeroAutorisationEuropeenne() {
		return numeroAutorisationEuropeenne;
	}

	/**
	 * @return the societe
	 */
	public String getSociete() {
		return titulaire;
	}

	/**
	 * @return the surveillanceRenforcee
	 */
	public boolean isSurveillanceRenforcee() {
		return surveillanceRenforcee;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param formePharmaceutique the formePharmaceutique to set
	 */
	public void setFormePharmaceutique(String formePharmaceutique) {
		this.formePharmaceutique = formePharmaceutique;
	}

	/**
	 * @param voieAdministration the voieAdministration to set
	 */
	public void setVoieAdministration(String voieAdministration) {
		this.voieAdministration = voieAdministration;
	}

	/**
	 * @param autorisation the autorisation to set
	 */
	public void setAutorisation(String autorisation) {
		this.autorisation = autorisation;
	}

	/**
	 * @param procedure the procedure to set
	 */
	public void setProcedure(String procedure) {
		this.procedureAdministrative = procedure;
	}

	/**
	 * @param commercialise the commercialise to set
	 */
	public void setCommercialise(boolean commercialise) {
		this.commercialise = commercialise;
	}

	/**
	 * @param dateCommercialisation the dateCommercialisation to set
	 */
	public void setDateCommercialisation(LocalDate dateCommercialisation) {
		this.dateCommercialisation = dateCommercialisation;
	}

	/**
	 * @param statutBdm the statutBdm to set
	 */
	public void setStatutBdm(String statutBdm) {
		this.statutBdm = statutBdm;
	}

	/**
	 * @param numeroAutorisationEuropeenne the numeroAutorisationEuropeenne to set
	 */
	public void setNumeroAutorisationEuropeenne(String numeroAutorisationEuropeenne) {
		this.numeroAutorisationEuropeenne = numeroAutorisationEuropeenne;
	}

	/**
	 * @param societe the societe to set
	 */
	public void setSociete(String societe) {
		this.titulaire = societe;
	}

	/**
	 * @param surveillanceRenforcee the surveillanceRenforcee to set
	 */
	public void setSurveillanceRenforcee(boolean surveillanceRenforcee) {
		this.surveillanceRenforcee = surveillanceRenforcee;
	}

	public WebGouvMedic(Long id, String nom, String formePharmaceutique, String voieAdministration, String autorisation,
			String procedure, boolean commercialise, LocalDate dateCommercialisation, String statutBdm,
			String numeroAutorisationEuropeenne, String societe, boolean surveillanceRenforcee) {
		super();
		this.id = id;
		this.nom = nom;
		this.formePharmaceutique = formePharmaceutique;
		this.voieAdministration = voieAdministration;
		this.autorisation = autorisation;
		this.procedureAdministrative = procedure;
		this.commercialise = commercialise;
		this.dateCommercialisation = dateCommercialisation;
		this.statutBdm = statutBdm;
		this.numeroAutorisationEuropeenne = numeroAutorisationEuropeenne;
		this.titulaire = societe;
		this.surveillanceRenforcee = surveillanceRenforcee;
		libellePresentation = "";
		etatCommercialisation = "";
		tauxRemboursement = "";
		prix = 1;
		indicationDroitRemboursement = "";
		elementPharmaceutique = "";
		codeSubstance = "";
		dosageSubstance = "";
		natureComposant = "";
		referenceDosage = "";
		numeroLiaisonSubstances = "";
		conditionPrescriptionDelivrance = "";
	}

	public WebGouvMedic() {
		super();
	}

	@Override
	public String toString() {
		return "WebGouvMedic [id=" + id + ", nom=" + nom + ", formePharmaceutique=" + formePharmaceutique
				+ ", voieAdministration=" + voieAdministration + ", autorisation=" + autorisation + "\n"
				+ ", procedureAdministrative=" + procedureAdministrative + ", commercialise=" + commercialise
				+ ", dateCommercialisation=" + dateCommercialisation + ", statutBdm=" + statutBdm + "\n"
				+ ", numeroAutorisationEuropeenne=" + numeroAutorisationEuropeenne + ", titulaire=" + titulaire
				+ ", surveillanceRenforcee=" + surveillanceRenforcee + ", libellePresentation=" + libellePresentation + "\n"
				+ ", etatCommercialisation=" + etatCommercialisation + ", tauxRemboursement=" + tauxRemboursement
				+ ", prix=" + prix + ", indicationDroitRemboursement=" + indicationDroitRemboursement + "\n"
				+ ", elementPharmaceutique=" + elementPharmaceutique + ", codeSubstance=" + codeSubstance
				+ ", dosageSubstance=" + dosageSubstance + ", natureComposant=" + natureComposant + ", referenceDosage="
				+ referenceDosage + "\n" + ", numeroLiaisonSubstances=" + numeroLiaisonSubstances
				+ ", conditionPrescriptionDelivrance=" + conditionPrescriptionDelivrance + "]";
	}

}
