package fr.helpad.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Allergene implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ALLERGENE")
	private Long idAllergene;
	private String nom;
	@ManyToMany
	private List<Plat> plats;

	public Long getIdAllergene() {
		return idAllergene;
	}

	public void setIdAllergene(Long idAllergene) {
		this.idAllergene = idAllergene;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAllergene, nom, plats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Allergene other = (Allergene) obj;
		return Objects.equals(idAllergene, other.idAllergene) && Objects.equals(nom, other.nom)
				&& Objects.equals(plats, other.plats);
	}

	@Override
	public String toString() {
		return "Allergene [idAllergene=" + idAllergene + ", nom=" + nom + ", plats=" + plats + "]";
	}

	public Allergene(Long idAllergene, String nom, List<Plat> plats) {
		super();
		this.idAllergene = idAllergene;
		this.nom = nom;
		this.plats = plats;
	}

	public Allergene(String nom, List<Plat> plats) {
		super();
		this.nom = nom;
		this.plats = plats;
	}

	public Allergene() {
		super();
	}

}
