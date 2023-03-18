package fr.helpad.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CANDIDATURE")
public class Candidature {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_candidature")
    private Long idCandidature;
    private String pathologie;
    @Column(name = "information_complementaire")
    private String informationComplementaire;
    @Column(name="Jour_de_candidature")
    private LocalDate jourDeCandidature= LocalDate.now();

    public Candidature() {
    }

    public Candidature( String pathologie, String informationComplementaire, LocalDate jourDeCandidature) {
        this.pathologie = pathologie;
        this.informationComplementaire = informationComplementaire;
        this.jourDeCandidature = LocalDate.now();
    }

    public Long getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(Long idCandidature) {
    	this.idCandidature = idCandidature;
    }

    public String getPathologie() {
        return pathologie;
    }

    public void setPathologie(String pathologie) {
        this.pathologie = pathologie;
    }

    public String getInformationComplementaire() {
        return informationComplementaire;
    }

    public void setInformationComplementaire(String informationComplementaire) {
        this.informationComplementaire = informationComplementaire;
    }

    public LocalDate getJourDeCandidature() {
        return jourDeCandidature;
    }

    public void setJourDeCandidature(LocalDate jourDeCandidature) {
        this.jourDeCandidature = jourDeCandidature;
    }
}
