package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Chambre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_chambre")
	private Long idChambre;
	@Column(name = "NO_CHAMBRE", nullable = false, unique = true, length = 40)
	private Long noChambre;
	@Column(name = "CHAMBRE_DOUBLE")
	private String chambreDouble;
	private String disponibilite;
	@Column(name="metre_carre")
	private double metreCarre;
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="no_etage")
	private Etage etage;

	
	
	//Les getters et les setteurs
	
	public Long getNoChambre() {
		return noChambre;
	}

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}

	public void setNoChambre(Long noChambre) {
		this.noChambre = noChambre;
	}
	
	public double getMetreCarre() {
		return metreCarre;
	}

	public void setMetreCarre(double metreCarre) {
		this.metreCarre = metreCarre;
	}

	public String getChambreDouble() {
		return chambreDouble;
	}

	public void setChambreDouble(String chambreDouble) {
		this.chambreDouble = chambreDouble;
	}

	public Etage getEtage() {
		return etage;
	}

	public void setEtage(Etage etage) {
		this.etage = etage;
	}
	
	public Long getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(Long idChambre) {
		this.idChambre = idChambre;
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

	
	
	public Chambre(Long noChambre, String chambreDouble, String disponibilite, double metreCarre, Etage etage) {
		super();
		this.noChambre = noChambre;
		this.chambreDouble = chambreDouble;
		this.disponibilite = disponibilite;
		this.metreCarre = metreCarre;
		this.etage = etage;
	}

	public Chambre() {
		super();
	}

}
