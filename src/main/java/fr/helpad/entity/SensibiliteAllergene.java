package fr.helpad.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@IdClass(SensibiliteAllergene.class)
@Table(name="SENSIBILITE_ALLERGENE")
public class SensibiliteAllergene implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ID_PERSONNE", unique = true, nullable = false)
	@Id
	@Autowired
	private Pensionnaire pensionnaire;
	@Autowired
	@Id
	@Column(name = "ID_ALLERGENE", unique = true, nullable = false)
	private Allergene allergene;
	@Column(name = "EN_CAS_DE_CRISE")
	private String enCasDeCrise;

	public Pensionnaire getPensionnaire() {
		return pensionnaire;
	}

	public void setPensionnaire(Pensionnaire pensionnaire) {
		this.pensionnaire = pensionnaire;
	}

	public Allergene getAllergene() {
		return allergene;
	}

	public void setAllergene(Allergene allergene) {
		this.allergene = allergene;
	}

	public String getEnCasDeCrise() {
		return enCasDeCrise;
	}

	public void setEnCasDeCrise(String enCasDeCrise) {
		this.enCasDeCrise = enCasDeCrise;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergene, enCasDeCrise, pensionnaire);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensibiliteAllergene other = (SensibiliteAllergene) obj;
		return Objects.equals(allergene, other.allergene) && Objects.equals(enCasDeCrise, other.enCasDeCrise)
				&& Objects.equals(pensionnaire, other.pensionnaire);
	}

	@Override
	public String toString() {
		return "SensibiliteAllergene [pensionnaire=" + pensionnaire + ", allergene=" + allergene + ", enCasDeCrise="
				+ enCasDeCrise + "]";
	}

	public SensibiliteAllergene(Pensionnaire pensionnaire, Allergene allergene, String enCasDeCrise) {
		super();
		this.pensionnaire = pensionnaire;
		this.allergene = allergene;
		this.enCasDeCrise = enCasDeCrise;
	}

	public SensibiliteAllergene() {
		super();
	}
}

