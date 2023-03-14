package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="adresse")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdresse;
	private String numero;
    private String rue;
    private String ville;
    @Column(name="code_postale")
    private String codePostale;
    
	public Adresse(String numero, String rue, String ville, String codePostale) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostale = codePostale;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}
    
    
}
