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
	@ManyToOne
	private WebGouvMedic medicament;
	@Column(name="DEBUT_INFO")
	private LocalDate debutInfoSecurite;
	@Column(name="FIN_INFO")
	private LocalDate finInfoSecurite;
	@Column(name="INFORMATION_SECURITE", length=9999)
	private String informationSecurite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WebGouvMedic getMedicament() {
		return medicament;
	}

	public void setMedicament(WebGouvMedic medicament) {
		this.medicament = medicament;
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

	public WebGouvSecurite(WebGouvMedic medicament, LocalDate debutInfoSecurite, LocalDate finInfoSecurite,
			String informationSecurite) {
		super();
		this.medicament = medicament;
		this.debutInfoSecurite = debutInfoSecurite;
		this.finInfoSecurite = finInfoSecurite;
		this.informationSecurite = informationSecurite;
	}

	public WebGouvSecurite() {
		super();
	}

}
