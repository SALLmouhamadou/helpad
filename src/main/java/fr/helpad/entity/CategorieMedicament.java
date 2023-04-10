package fr.helpad.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class CategorieMedicament {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	private String name;
	@ManyToMany
	private List<Medicament> medicaments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Medicament> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	public CategorieMedicament(Long id, String name, List<Medicament> medicaments) {
		super();
		this.id = id;
		this.name = name;
		this.medicaments = medicaments;
	}

	public CategorieMedicament(String name, List<Medicament> medicaments) {
		super();
		this.name = name;
		this.medicaments = medicaments;
	}

	public CategorieMedicament() {
		super();
	}

	@Override
	public String toString() {
		return "CategorieMedicament [id=" + id + ", name=" + name + ", medicaments=" + medicaments + "]";
	}

}
