package fr.helpad.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicament implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MEDICAMENT")
	private Long idMedicament;
	private String nom;
	private String fonction;
	private int stock;
	private typeMedicament typeStock;
	private int consoMois;
	@Column(name = "QUANTITE_PAR_BOITE", unique = false, nullable = false)
	private int quantiteParBoite;

	enum typeMedicament {
		LIQUIDE, UNITAIRE, INHALATION
	}

	public Medicament(String nom, String fonction, int stock, typeMedicament typeStock, int consoMois) {
		super();
		this.nom = nom;
		this.fonction = fonction;
		this.stock = stock;
		this.typeStock = typeStock;
		this.consoMois = consoMois;
	}

	public Medicament(Long idMedicament, String nom, String fonction, int stock, typeMedicament typeStock,
			int consoMois) {
		super();
		this.idMedicament = idMedicament;
		this.nom = nom;
		this.fonction = fonction;
		this.stock = stock;
		this.typeStock = typeStock;
		this.consoMois = consoMois;
	}

	public Medicament() {
		super();
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the consoMois
	 */
	public int getConsoMois() {
		return consoMois;
	}

	/**
	 * @param consoMois the consoMois to set
	 */
	public void setConsoMois(int consoMois) {
		this.consoMois = consoMois;
	}

	/**
	 * @return the typeStock
	 */
	public typeMedicament getTypeStock() {
		return typeStock;
	}

	/**
	 * @param typeStock the typeStock to set
	 */
	public void setTypeStock(typeMedicament typeStock) {
		this.typeStock = typeStock;
	}

	public Long getIdMedicament() {
		return idMedicament;
	}

	public void setIdMedicament(Long idMedicament) {
		this.idMedicament = idMedicament;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Medicament [idMedicament=" + idMedicament + ", nom=" + nom + ", fonction=" + fonction + ", stock="
				+ stock + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fonction, idMedicament, nom, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicament other = (Medicament) obj;
		return Objects.equals(fonction, other.fonction) && Objects.equals(idMedicament, other.idMedicament)
				&& Objects.equals(nom, other.nom) && stock == other.stock;
	}

}
