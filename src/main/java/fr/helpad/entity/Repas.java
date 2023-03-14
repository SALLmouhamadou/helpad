package fr.helpad.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Repas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REPAS")
	private Long idRepas;
	private Horaire horaire;
	@ManyToMany
	private List<Plat> plats;
	@Temporal(TemporalType.DATE)
	private Date date;

	public enum Horaire {
		PETIT_DEJEUNER, DEJEUNER, GOUTER, SOUPER;
	}

	public Long getIdRepas() {
		return idRepas;
	}

	public void setIdRepas(Long idRepas) {
		this.idRepas = idRepas;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, horaire, idRepas, plats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repas other = (Repas) obj;
		return Objects.equals(date, other.date) && horaire == other.horaire && Objects.equals(idRepas, other.idRepas)
				&& Objects.equals(plats, other.plats);
	}

	@Override
	public String toString() {
		return "Repas [idRepas=" + idRepas + ", horaire=" + horaire + ", plats=" + plats + ", date=" + date + "]";
	}

	public Repas(Long idRepas, Horaire horaire, List<Plat> plats, Date date) {
		super();
		this.idRepas = idRepas;
		this.horaire = horaire;
		this.plats = plats;
		this.date = date;
	}

	public Repas(Horaire horaire, List<Plat> plats, Date date) {
		super();
		this.horaire = horaire;
		this.plats = plats;
		this.date = date;
	}

	public Repas() {
		super();
	}

}