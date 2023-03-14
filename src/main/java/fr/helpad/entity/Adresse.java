package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADRESSE")
	private Long idAdresse;
	private int numero;
	private String rue;
	private String ville;
	@Column(name = "CODE_POSTAL", length=6)
	private int codePostal;
	private String pays;
	//@OneToMany(cascade=CascadeType.MERGE)
	//private List<Personne> personnes;

	public Long getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(Long idAdresse) {
		this.idAdresse = idAdresse;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

//	public List<Personne> getPersonnes() {
//		return personnes;
//	}
//
//	public void setPersonnes(List<Personne> personnes) {
//		this.personnes = personnes;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(codePostal, idAdresse, numero, pays, rue, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		return codePostal == other.codePostal && Objects.equals(idAdresse, other.idAdresse) && numero == other.numero
				&& Objects.equals(pays, other.pays)
				&& Objects.equals(rue, other.rue) && Objects.equals(ville, other.ville);
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", numero=" + numero + ", rue=" + rue + ", ville=" + ville
				+ ", codePostal=" + codePostal + ", pays=" + pays;
	}

	public Adresse(Long idAdresse, int numero, String rue, String ville, int codePostal, String pays) {
		super();
		this.idAdresse = idAdresse;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}

	public Adresse(int numero, String rue, String ville, int codePostal, String pays) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}

	public Adresse() {
		super();
	}

}
