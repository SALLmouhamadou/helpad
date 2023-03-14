package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Chambre {
	@Id
	@Column(name = "NO_CHAMBRE")
	private String noChambre;
	@Column(name = "CHAMBRE_DOUBLE")
	private boolean chambreDouble;
	@Autowired
	@OneToOne
	private Etage etage;

	public String getNoChambre() {
		return noChambre;
	}

	public void setNoChambre(String noChambre) {
		this.noChambre = noChambre;
	}

	public boolean isChambreDouble() {
		return chambreDouble;
	}

	public void setChambreDouble(boolean chambreDouble) {
		this.chambreDouble = chambreDouble;
	}

	public Etage getEtage() {
		return etage;
	}

	public void setEtage(Etage etage) {
		this.etage = etage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chambreDouble, etage, noChambre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chambre other = (Chambre) obj;
		return chambreDouble == other.chambreDouble && Objects.equals(etage, other.etage)
				&& Objects.equals(noChambre, other.noChambre);
	}

	@Override
	public String toString() {
		return "Chambre [noChambre=" + noChambre + ", chambreDouble=" + chambreDouble + ", etage=" + etage + "]";
	}

	public Chambre(String noChambre, boolean chambreDouble, Etage etage) {
		super();
		this.noChambre = noChambre;
		this.chambreDouble = chambreDouble;
		this.etage = etage;
	}

	public Chambre() {
		super();
	}

}
