package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Infirmiere extends Employe {
	@Column(name = "NO_RPPS")
	private String noRpps;

	public String getNoRpps() {
		return noRpps;
	}

	public void setNoRpps(String noRpps) {
		this.noRpps = noRpps;
	}

	

	public Infirmiere(String nom, String prenom, String telephone, String email, Adresse adresse, String noSecu,
			Fonction fonction, String noRpps) {
		super(nom, prenom, telephone, email, adresse, noSecu, fonction);
		this.noRpps = noRpps;
	}

	public Infirmiere() {
		super();
	}

	@Override
	public String toString() {
		return "Infirmiere [noRpps=" + noRpps + "]";
	}
}
