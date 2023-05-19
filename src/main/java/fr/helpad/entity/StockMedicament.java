package fr.helpad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class StockMedicament {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long codeCis;
	private Long quantite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodeCis() {
		return codeCis;
	}

	public void setCodeCis(Long codeCis) {
		this.codeCis = codeCis;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public StockMedicament(Long id, Long codeCis, Long quantite) {
		super();
		this.id = id;
		this.codeCis = codeCis;
		this.quantite = quantite;
	}

	public StockMedicament(Long codeCis, Long quantite) {
		super();
		this.codeCis = codeCis;
		this.quantite = quantite;
	}

	public StockMedicament() {
		super();
	}

}
