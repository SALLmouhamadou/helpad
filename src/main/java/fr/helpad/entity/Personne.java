package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONNE")
@Inheritance(strategy = InheritanceType.JOINED)

public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_personne")
	private Long idPersonne;
	private String nom;
	private String prenom;
	private String telephone;
	@ManyToOne
	@JoinColumn(name = "ADRESSE")
	private Adresse adresse;
	private String email;
	private String password;

	/**
	 * @return the idPersonne
	 */
	public Long getIdPersonne() {
		return idPersonne;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param idPersonne the idPersonne to set
	 */
	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, email, idPersonne, nom, password, prenom, telephone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(email, other.email)
				&& Objects.equals(idPersonne, other.idPersonne) && Objects.equals(nom, other.nom)
				&& Objects.equals(password, other.password) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(telephone, other.telephone);
	}

	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone
				+ ", adresse=" + adresse + ", email=" + email + ", password=" + password + "]";
	}

	public Personne(Long idPersonne, String nom, String prenom, String telephone, Adresse adresse, String email,
			String password) {
		super();
		this.idPersonne = idPersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.email = email;
		this.password = password;
	}

	public Personne(String nom, String prenom, String telephone, Adresse adresse, String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.email = email;
		this.password = password;
	}

	public Personne() {
		super();
	}

}