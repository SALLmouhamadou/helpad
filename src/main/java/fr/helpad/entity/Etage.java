package fr.helpad.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Etage {
	@Id
	@Column(name = "no_ETAGE")
	private Long noEtage;
	@Column(name = "type")
	private String type;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Chambre> chambres;

	public Long getNoEtage() {
		return noEtage;
	}

	public void setNoEtage(Long noEtage) {
		this.noEtage = noEtage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Chambre> getChambres() {
		return chambres;
	}

	public void setChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chambres, type, noEtage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etage other = (Etage) obj;
		return Objects.equals(chambres, other.chambres) && type == other.type
				&& Objects.equals(noEtage, other.noEtage);
	}

	@Override
	public String toString() {
		return "Etage [idEtage=" + noEtage + ", etageSecurise=" + type + ", chambres=" + chambres + "]";
	}

	public Etage(Long noEtage, String type, List<Chambre> chambres) {
		super();
		this.noEtage = noEtage;
		this.type = type;
		this.chambres = chambres;
	}

	public Etage(String type, List<Chambre> chambres) {
		super();
		this.type = type;
		this.chambres = chambres;
	}

	public Etage() {
		super();
	}

}
