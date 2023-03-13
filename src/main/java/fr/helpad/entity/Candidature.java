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
    private Long Id;
    private String pathologie;
    @Column(name = "information_complementaire")
    private String informationComplementaire;
    @Column(name="Jour_de_candidature")
    private LocalDate jourDeCandidature;

    public Candidature() {
    }

    public Candidature( String pathologie, String informationComplementaire, LocalDate jourDeCandidature) {
        this.pathologie = pathologie;
        this.informationComplementaire = informationComplementaire;
        this.jourDeCandidature = jourDeCandidature;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPosologie() {
        return pathologie;
    }

    public void setPosologie(String posologie) {
        this.pathologie = posologie;
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
