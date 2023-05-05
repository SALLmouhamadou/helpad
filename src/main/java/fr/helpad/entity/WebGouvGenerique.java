package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity()
public class WebGouvGenerique {
	@Id
	private Long id;
	@Column(name = "LIBELLE_GENERIQUE")
	private String libelleGenerique;
	@Column(name = "TYPE_GENERIQUE")
	private String typeGenerique;
	@Column(name = "NUMERO_TRI")
	private String numeroTri;
	@OneToOne
	private WebGouvMedic medicament;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelleGenerique() {
		return libelleGenerique;
	}

	public void setLibelleGenerique(String libelleGenerique) {
		this.libelleGenerique = libelleGenerique;
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
			WebGouvMedic medicament) {
		super();
		this.id = id;
		this.libelleGenerique = libelleGenerique;
		this.typeGenerique = typeGenerique;
		this.numeroTri = numeroTri;
		this.medicament = medicament;
	}

	public WebGouvGenerique() {
		super();
	}

}
