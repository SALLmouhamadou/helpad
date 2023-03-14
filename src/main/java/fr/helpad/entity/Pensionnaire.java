package fr.helpad.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Pensionnaire extends Personne implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Autowired
	@ManyToOne
	private Chambre chambre;
	@Column(name = "NO_SECU")
	private String noSecu;
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Medecin> medecins;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Visite> visites;
	@OneToOne
	private Personne contactUrgence;

	public Pensionnaire(String nom, String prenom, String telephone, Adresse adresse, String email, String password,
			Chambre chambre, String noSecu, List<Medecin> medecins, List<Visite> visites, Personne contactUrgence) {
		super(nom, prenom, telephone, adresse, email, password);
		this.chambre = chambre;
		this.noSecu = noSecu;
		this.medecins = medecins;
		this.visites = visites;
		this.contactUrgence = contactUrgence;
	}

	public Pensionnaire() {
		super();
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public String getNoSecu() {
		return noSecu;
	}

	public void setNoSecu(String noSecu) {
		this.noSecu = noSecu;
	}

	public List<Medecin> getMedecins() {
		return medecins;
	}

	public void setMedecins(List<Medecin> medecins) {
		this.medecins = medecins;
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(chambre, medecins, noSecu, visites);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pensionnaire other = (Pensionnaire) obj;
		return Objects.equals(chambre, other.chambre) && Objects.equals(medecins, other.medecins)
				&& Objects.equals(noSecu, other.noSecu) && Objects.equals(visites, other.visites);
	}

	@Override
	public String toString() {
		return "Pensionnaire [chambre=" + chambre + ", noSecu=" + noSecu + ", medecins=" + medecins + ", visites="
				+ visites + "]";
	}

}
