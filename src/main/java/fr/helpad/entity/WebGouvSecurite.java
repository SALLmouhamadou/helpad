package fr.helpad.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class WebGouvSecurite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long codeCis;
	@Column(name = "DEBUT_INFO")
	private LocalDate debutInfoSecurite;
	@Column(name = "FIN_INFO")
	private LocalDate finInfoSecurite;
	@Column(name = "INFORMATION_SECURITE", length = 9999)
	private String informationSecurite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the codeCis
	 */
	public Long getCodeCis() {
		return codeCis;
	}

	/**
	 * @param codeCis the codeCis to set
	 */
	public void setCodeCis(Long codeCis) {
		this.codeCis = codeCis;
	}

	public LocalDate getDebutInfoSecurite() {
		return debutInfoSecurite;
	}

	public void setDebutInfoSecurite(LocalDate debutInfoSecurite) {
		this.debutInfoSecurite = debutInfoSecurite;
	}

	public LocalDate getFinInfoSecurite() {
		return finInfoSecurite;
	}

	public void setFinInfoSecurite(LocalDate finInfoSecurite) {
		this.finInfoSecurite = finInfoSecurite;
	}

	public String getInformationSecurite() {
		return informationSecurite;
	}

	public void setInformationSecurite(String informationSecurite) {
		this.informationSecurite = informationSecurite;
	}

	public WebGouvSecurite(Long codeCis, LocalDate debutInfoSecurite, LocalDate finInfoSecurite,
			String informationSecurite) {
		super();
		this.codeCis = codeCis;
		this.debutInfoSecurite = debutInfoSecurite;
		this.finInfoSecurite = finInfoSecurite;
		this.informationSecurite = informationSecurite;
	}

	public WebGouvSecurite() {
		super();
	}

}
