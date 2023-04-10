package fr.helpad.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class PrincipeActif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	private String nom;
	@ManyToMany
	private List<Medicament> medicaments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Medicament> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	public PrincipeActif(Long id, String nom, List<Medicament> medicaments) {
		super();
		this.id = id;
		this.nom = nom;
		this.medicaments = medicaments;
	}

	public PrincipeActif(String nom, List<Medicament> medicaments) {
		super();
		this.nom = nom;
		this.medicaments = medicaments;
	}

	public PrincipeActif() {
		super();
	}

	@Override
	public String toString() {
		return "PrincipeActif [id=" + id + ", nom=" + nom;
	}

}
