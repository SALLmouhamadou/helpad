package fr.helpad.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Pilulier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PILULIER")
	private Long idPululier;
	private Jour jour;
	private Horaire horaire;
	@Autowired
	@OneToOne
	private Pensionnaire pensionnaire;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Medicament> medicaments;

	enum Horaire {
		MATIN, MIDI, SOIR;
	}

	enum Jour {
		LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
	}

	@Override
	public String toString() {
		return "Pilulier [idPululier=" + idPululier + ", jour=" + jour + ", horaire=" + horaire + "]";
	}

	public Pilulier() {
		super();
	}

	public Pilulier(Jour jour, Horaire horaire, Pensionnaire pensionnaire, List<Medicament> medicaments) {
		super();
		this.jour = jour;
		this.horaire = horaire;
		this.pensionnaire = pensionnaire;
		this.medicaments = medicaments;
	}

	public Pilulier(Long idPululier, Jour jour, Horaire horaire, Pensionnaire pensionnaire,
			List<Medicament> medicaments) {
		super();
		this.idPululier = idPululier;
		this.jour = jour;
		this.horaire = horaire;
		this.pensionnaire = pensionnaire;
		this.medicaments = medicaments;
	}

	/**
	 * @return the idPululier
	 */
	public Long getIdPululier() {
		return idPululier;
	}

	/**
	 * @return the pensionnaire
	 */
	public Pensionnaire getPensionnaire() {
		return pensionnaire;
	}

	/**
	 * @return the medicaments
	 */
	public List<Medicament> getMedicaments() {
		return medicaments;
	}

	/**
	 * @param idPululier the idPululier to set
	 */
	public void setIdPululier(Long idPululier) {
		this.idPululier = idPululier;
	}

	/**
	 * @param pensionnaire the pensionnaire to set
	 */
	public void setPensionnaire(Pensionnaire pensionnaire) {
		this.pensionnaire = pensionnaire;
	}

	/**
	 * @param medicaments the medicaments to set
	 */
	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	/**
	 * @return the jour
	 */
	public Jour getJour() {
		return jour;
	}

	/**
	 * @return the horaire
	 */
	public Horaire getHoraire() {
		return horaire;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(Jour jour) {
		this.jour = jour;
	}

	/**
	 * @param horaire the horaire to set
	 */
	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

}
