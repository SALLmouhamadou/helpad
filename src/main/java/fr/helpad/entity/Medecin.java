package fr.helpad.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Medecin extends Personne {
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Pensionnaire> pensionnaires;
	private String fonction;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Visite> visites;

	

	public Medecin(String nom, String prenom, String telephone, String email, Adresse adresse,
			List<Pensionnaire> pensionnaires, String fonction, List<Visite> visites) {
		super(nom, prenom, telephone, email, adresse);
		this.pensionnaires = pensionnaires;
		this.fonction = fonction;
		this.visites = visites;
	}

	public Medecin() {
		super();
	}

	@Override
	public String toString() {
		return "Medecin [pensionnaires=" + pensionnaires + ", fonction=" + fonction + ", visites=" + visites + "]";
	}

	public List<Pensionnaire> getPensionnaires() {
		return pensionnaires;
	}

	public void setPensionnaires(List<Pensionnaire> pensionnaires) {
		this.pensionnaires = pensionnaires;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
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
		result = prime * result + Objects.hash(fonction, pensionnaires, visites);
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
		Medecin other = (Medecin) obj;
		return Objects.equals(fonction, other.fonction) && Objects.equals(pensionnaires, other.pensionnaires)
				&& Objects.equals(visites, other.visites);
	}

}
