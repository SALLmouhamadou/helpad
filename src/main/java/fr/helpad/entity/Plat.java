package fr.helpad.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Plat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLAT")
	private Long idPlat;
	@ManyToMany
	private List<Allergene> allergenes;
	private String nom;
	@ManyToMany
	private List<Repas> repas;

	public Long getIdPlat() {
		return idPlat;
	}

	public void setIdPlat(Long idPlat) {
		this.idPlat = idPlat;
	}

	public List<Allergene> getAllergenes() {
		return allergenes;
	}

	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergenes, idPlat, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plat other = (Plat) obj;
		return Objects.equals(allergenes, other.allergenes) && Objects.equals(idPlat, other.idPlat)
				&& Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Plat [idPlat=" + idPlat + ", allergenes=" + allergenes + ", nom=" + nom + "]";
	}

	public Plat(Long idPlat, List<Allergene> allergenes, String nom) {
		super();
		this.idPlat = idPlat;
		this.allergenes = allergenes;
		this.nom = nom;
	}

	public Plat(List<Allergene> allergenes, String nom) {
		super();
		this.allergenes = allergenes;
		this.nom = nom;
	}

	public Plat() {
		super();
	}

}
