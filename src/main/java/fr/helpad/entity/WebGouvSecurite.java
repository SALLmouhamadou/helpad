package fr.helpad.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WebGouvSecurite {
	@Id
	@ManyToOne
	private WebGouvMedic medicament;
	private LocalDate debutInfoSecurite;
	private LocalDate finInfoSecurite;
	private String informationSecurite;

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
