package fr.helpad.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employe extends Personne {
	private String noSecu;
	@Autowired
	@OneToOne(cascade = CascadeType.MERGE)
	private Fonction fonction;

	public String getNoSecu() {
		return noSecu;
	}

	public void setNoSecu(String noSecu) {
		this.noSecu = noSecu;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fonction, noSecu);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employe other = (Employe) obj;
		return Objects.equals(fonction, other.fonction) && Objects.equals(noSecu, other.noSecu);
	}

	@Override
	public String toString() {
		return "Employe [noSecu=" + noSecu + ", fonction=" + fonction + "]";
	}

	public Employe(String nom, String prenom, String telephone, Adresse adresse, String email, String password,
			String noSecu, Fonction fonction) {
		super(nom, prenom, telephone, adresse, email, password);
		this.noSecu = noSecu;
		this.fonction = fonction;
	}

	public Employe() {
		super();
	}

}
