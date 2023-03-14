package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FACTURE")
	private Long idFacture;
	private Double prix;
	@Autowired
	@OneToOne
	private Personne beneficiaire;
	@Autowired
	@OneToOne
	private Personne payeur;

	public Long getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(Long idFacture) {
		this.idFacture = idFacture;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Personne getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(Personne beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	public Personne getPayeur() {
		return payeur;
	}

	public void setPayeur(Personne payeur) {
		this.payeur = payeur;
	}

	@Override
	public int hashCode() {
		return Objects.hash(beneficiaire, idFacture, payeur, prix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		return Objects.equals(beneficiaire, other.beneficiaire) && Objects.equals(idFacture, other.idFacture)
				&& Objects.equals(payeur, other.payeur) && Objects.equals(prix, other.prix);
	}

	@Override
	public String toString() {
		return "Facture [idFacture=" + idFacture + ", prix=" + prix + ", beneficiaire=" + beneficiaire + ", payeur="
				+ payeur + "]";
	}

	public Facture(Long idFacture, Double prix, Personne beneficiaire, Personne payeur) {
		super();
		this.idFacture = idFacture;
		this.prix = prix;
		this.beneficiaire = beneficiaire;
		this.payeur = payeur;
	}

	public Facture(Double prix, Personne beneficiaire, Personne payeur) {
		super();
		this.prix = prix;
		this.beneficiaire = beneficiaire;
		this.payeur = payeur;
	}

	public Facture() {
		super();
	}

}
