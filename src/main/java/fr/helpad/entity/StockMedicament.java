package fr.helpad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class StockMedicament {
	@Id
	private Long codeCis;
	private short quantite;

	public Long getCodeCis() {
		return codeCis;
	}

	public void setCodeCis(Long codeCis) {
		this.codeCis = codeCis;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(short quantite) {
		this.quantite = quantite;
	}

	public StockMedicament(Long codeCis, short quantite) {
		super();
		this.codeCis = codeCis;
		this.quantite = quantite;
	}

	public StockMedicament() {
		super();
	}

}
