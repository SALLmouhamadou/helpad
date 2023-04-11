package fr.helpad.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Nutritionniste extends Personne{
	
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Repas> repas;

	

	public Nutritionniste(String nom, String prenom, String email, String password, List<Repas> repas) {
		super(nom, prenom, email, password);
		this.repas = repas;
	}

	public Nutritionniste() {
		super();
	}

	@Override
	public String toString() {
		return "Nutritionniste [repas=" + repas +  "]";
	}

	public List<Repas> getRepas() {
		return repas;
	}

	public void setRepas(List<Repas> repas) {
		this.repas = repas;
	}

}
