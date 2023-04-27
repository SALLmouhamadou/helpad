package fr.helpad.entity;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebGouvMedic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String formePharmaceutique;
	private String voieAdministration;
	private String autorisation;
	private String procedure;
	private boolean commercialise;
	private LocalDate dateCommercialisation;
	private String statutBdm;
	private String numeroAutorisationEuropeenne;
	private String societe;
	private boolean surveillanceRenforcee;
	private static LocalDate dateMiseAJour;

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
		return procedure;
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
		return societe;
	}

	/**
	 * @return the surveillanceRenforcee
	 */
	public boolean isSurveillanceRenforcee() {
		return surveillanceRenforcee;
	}

	/**
	 * @return the dateMiseAJour
	 */
	public static LocalDate getDateMiseAJour() {
		return dateMiseAJour;
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
		this.procedure = procedure;
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
		this.societe = societe;
	}

	/**
	 * @param surveillanceRenforcee the surveillanceRenforcee to set
	 */
	public void setSurveillanceRenforcee(boolean surveillanceRenforcee) {
		this.surveillanceRenforcee = surveillanceRenforcee;
	}

	/**
	 * @param dateMiseAJour the dateMiseAJour to set
	 */
	public static void setDateMiseAJour(LocalDate dateMiseAJour) {
		WebGouvMedic.dateMiseAJour = dateMiseAJour;
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
		this.procedure = procedure;
		this.commercialise = commercialise;
		this.dateCommercialisation = dateCommercialisation;
		this.statutBdm = statutBdm;
		this.numeroAutorisationEuropeenne = numeroAutorisationEuropeenne;
		this.societe = societe;
		this.surveillanceRenforcee = surveillanceRenforcee;
	}

	public WebGouvMedic(String nom, String formePharmaceutique, String voieAdministration, String autorisation,
			String procedure, boolean commercialise, LocalDate dateCommercialisation, String statutBdm,
			String numeroAutorisationEuropeenne, String societe, boolean surveillanceRenforcee) {
		super();
		this.nom = nom;
		this.formePharmaceutique = formePharmaceutique;
		this.voieAdministration = voieAdministration;
		this.autorisation = autorisation;
		this.procedure = procedure;
		this.commercialise = commercialise;
		this.dateCommercialisation = dateCommercialisation;
		this.statutBdm = statutBdm;
		this.numeroAutorisationEuropeenne = numeroAutorisationEuropeenne;
		this.societe = societe;
		this.surveillanceRenforcee = surveillanceRenforcee;
	}

	public WebGouvMedic() {
		super();
	}

}
