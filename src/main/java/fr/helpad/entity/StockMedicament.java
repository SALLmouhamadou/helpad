package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockMedicament {
	@Id
	private Long codeCis;
	@Column(nullable = false, length = 3)
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

	public void setQuantite(short quantite) throws NumberFormatException {
		if (quantite < 0 || quantite > 999)
			throw new NumberFormatException();
		else
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
