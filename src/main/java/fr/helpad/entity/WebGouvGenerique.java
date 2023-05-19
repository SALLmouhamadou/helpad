package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity()
public class WebGouvGenerique {
	@Id
	private Long id;
	@Column(length = 999)
	private String nom;
	@Column(name = "TYPE_GENERIQUE")
	private String typeGenerique;
	@Column(name = "NUMERO_TRI")
	private String numeroTri;
	@Column(name = "IDENTIFIANT_GROUPE_GENERIQUE")
	private String identifiantGroupeGenerique;

	public String getIdentifiantGroupeGenerique() {
		return identifiantGroupeGenerique;
	}

	public void setIdentifiantGroupeGenerique(String identifiantGroupeGenerique) {
		this.identifiantGroupeGenerique = identifiantGroupeGenerique;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelleGenerique() {
		return nom;
	}

	public void setLibelleGenerique(String libelleGenerique) {
		this.nom = libelleGenerique;
	}

	public String getTypeGenerique() {
		return typeGenerique;
	}

	public void setTypeGenerique(String typeGenerique) {
		this.typeGenerique = typeGenerique;
	}

	public String getNumeroTri() {
		return numeroTri;
	}

	public void setNumeroTri(String numeroTri) {
		this.numeroTri = numeroTri;
	}

	public WebGouvGenerique(Long id, String libelleGenerique, String typeGenerique, String numeroTri,
			String identifiantGroupeGenerique) {
		super();
		this.id = id;
		this.nom = libelleGenerique;
		this.typeGenerique = typeGenerique;
		this.numeroTri = numeroTri;
		this.identifiantGroupeGenerique = identifiantGroupeGenerique;
	}

	public WebGouvGenerique() {
		super();
	}

}
