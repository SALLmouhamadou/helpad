package fr.helpad.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	@Column(name="nom", nullable = false, unique = true)
	private String nom;
	@Column(name="prenom", nullable = false, unique = true)
	private String prenom;
	private String telephone;
	@Column(name="email", nullable = false, unique = true)
	private String email;
	private String password;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "ADRESSE")
	private Adresse adresse;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Role> roles;

	

	public Personne(String nom, String prenom, String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	public Personne(String nom, String prenom, String password, Set<Role> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		roles = new HashSet<>();
	}

	public Personne(String nom, String prenom, String telephone, String email, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
	}

	public Personne() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(telephone, email, idPersonne, nom, password, prenom);
	}
	
	

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
		return Objects.equals(telephone, other.telephone) && Objects.equals(email, other.email)
				&& Objects.equals(idPersonne, other.idPersonne) && Objects.equals(nom, other.nom)
				&& Objects.equals(password, other.password) && Objects.equals(prenom, other.prenom);

	}

}