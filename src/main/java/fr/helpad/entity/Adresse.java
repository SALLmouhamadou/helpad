package fr.helpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ADRESSE")
public class Adresse {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String rue;
    private String Ville;
    @Column(name="code_postale")
    private String codePostale;
    public Adresse() {
        super();
    }
    public Adresse( String numero, String rue, String ville, String codePostale) {
        super();
        this.numero = numero;
        this.rue = rue;
        Ville = ville;
        this.codePostale = codePostale;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
        return Ville;
    }
    public void setVille(String ville) {
        Ville = ville;
    }
    public String getCodePostale() {
        return codePostale;
    }
    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }



}
