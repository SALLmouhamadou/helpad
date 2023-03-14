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
public class Fonction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERSONNE")
	private Long idFonction;
	private String nom;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Employe> employes;

	public Long getIdFonction() {
		return idFonction;
	}

	public void setIdFonction(Long idFonction) {
		this.idFonction = idFonction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employes, idFonction, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fonction other = (Fonction) obj;
		return Objects.equals(employes, other.employes) && Objects.equals(idFonction, other.idFonction)
				&& Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Fonction [idFonction=" + idFonction + ", nom=" + nom + ", employes=" + employes + "]";
	}

	public Fonction(Long idFonction, String nom, List<Employe> employes) {
		super();
		this.idFonction = idFonction;
		this.nom = nom;
		this.employes = employes;
	}

	public Fonction(String nom, List<Employe> employes) {
		super();
		this.nom = nom;
		this.employes = employes;
	}

	public Fonction() {
		super();
	}

}
