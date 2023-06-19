package fr.helpad.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
	private String nomAllergene;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Plat> plats;

	public Long getIdAllergene() {
		return idAllergene;
	}

	public void setIdAllergene(Long idAllergene) {
		this.idAllergene = idAllergene;
	}

	public String getNomAllergene() {
		return nomAllergene;
	}

	public void setNomAllergene(String nomAllergene) {
		this.nomAllergene = nomAllergene;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAllergene, nomAllergene, plats);
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
		return Objects.equals(idAllergene, other.idAllergene) && Objects.equals(nomAllergene, other.nomAllergene)
				&& Objects.equals(plats, other.plats);
	}

	@Override
	public String toString() {
		return "Allergene [idAllergene=" + idAllergene + ", nom=" + nomAllergene + ", plats=" + plats + "]";
	}

	public Allergene(Long idAllergene, String nomAllergene, List<Plat> plats) {
		super();
		this.idAllergene = idAllergene;
		this.nomAllergene = nomAllergene;
		this.plats = plats;
	}

	public Allergene(String nomAllergene, List<Plat> plats) {
		super();
		this.nomAllergene = nomAllergene;
		this.plats = plats;
	}
	
	public Allergene(String nomAllergene) {
		super();
		this.nomAllergene = nomAllergene;
	}

	public Allergene() {
		super();
	}

}
