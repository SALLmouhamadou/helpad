package fr.helpad.entity;

import java.io.Serializable;
import java.util.List;
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
	private List<CategorieMedicament> fonctions;
	private List<PrincipeActif> principesActifs;
	private int stock;
	private typeMedicament typeStock;
	private int consoMois;
	@Column(name = "QUANTITE_PAR_BOITE", unique = false, nullable = false)
	private int quantiteParBoite;

	public enum typeMedicament {
		LIQUIDE, UNITAIRE, INHALATION
	}

	public Medicament(Long idMedicament, String nom, List<CategorieMedicament> fonctions,
			List<PrincipeActif> principesActifs, int stock, typeMedicament typeStock, int consoMois,
			int quantiteParBoite) {
		super();
		this.idMedicament = idMedicament;
		this.nom = nom;
		this.fonctions = fonctions;
		this.principesActifs = principesActifs;
		this.stock = stock;
		this.typeStock = typeStock;
		this.consoMois = consoMois;
		this.quantiteParBoite = quantiteParBoite;
	}

	public Medicament(String nom, List<CategorieMedicament> fonctions, List<PrincipeActif> principesActifs, int stock,
			typeMedicament typeStock, int consoMois, int quantiteParBoite) {
		super();
		this.nom = nom;
		this.fonctions = fonctions;
		this.principesActifs = principesActifs;
		this.stock = stock;
		this.typeStock = typeStock;
		this.consoMois = consoMois;
		this.quantiteParBoite = quantiteParBoite;
	}

	public Medicament() {
		super();
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

	public List<CategorieMedicament> getFonctions() {
		return fonctions;
	}

	public void setFonctions(List<CategorieMedicament> fonctions) {
		this.fonctions = fonctions;
	}

	public List<PrincipeActif> getPrincipesActifs() {
		return principesActifs;
	}

	public void setPrincipesActifs(List<PrincipeActif> principesActifs) {
		this.principesActifs = principesActifs;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public typeMedicament getTypeStock() {
		return typeStock;
	}

	public void setTypeStock(typeMedicament typeStock) {
		this.typeStock = typeStock;
	}

	public int getConsoMois() {
		return consoMois;
	}

	public void setConsoMois(int consoMois) {
		this.consoMois = consoMois;
	}

	public int getQuantiteParBoite() {
		return quantiteParBoite;
	}

	public void setQuantiteParBoite(int quantiteParBoite) {
		this.quantiteParBoite = quantiteParBoite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
