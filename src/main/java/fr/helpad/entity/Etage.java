package fr.helpad.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Etage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ETAGE")
	private Long idEtage;
	@Column(name = "ETAGE_SECURISE")
	private boolean etageSecurise;
	@OneToMany
	private List<Chambre> chambres;

	public Long getIdEtage() {
		return idEtage;
	}

	public void setIdEtage(Long idEtage) {
		this.idEtage = idEtage;
	}

	public boolean isEtageSecurise() {
		return etageSecurise;
	}

	public void setEtageSecurise(boolean etageSecurise) {
		this.etageSecurise = etageSecurise;
	}

	public List<Chambre> getChambres() {
		return chambres;
	}

	public void setChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chambres, etageSecurise, idEtage);
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
		return Objects.equals(chambres, other.chambres) && etageSecurise == other.etageSecurise
				&& Objects.equals(idEtage, other.idEtage);
	}

	@Override
	public String toString() {
		return "Etage [idEtage=" + idEtage + ", etageSecurise=" + etageSecurise + ", chambres=" + chambres + "]";
	}

	public Etage(Long idEtage, boolean etageSecurise, List<Chambre> chambres) {
		super();
		this.idEtage = idEtage;
		this.etageSecurise = etageSecurise;
		this.chambres = chambres;
	}

	public Etage(boolean etageSecurise, List<Chambre> chambres) {
		super();
		this.etageSecurise = etageSecurise;
		this.chambres = chambres;
	}

	public Etage() {
		super();
	}

}
