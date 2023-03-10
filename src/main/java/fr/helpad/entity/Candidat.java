package fr.helpad.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CANDIDAT")
@PrimaryKeyJoinColumn(name = "id")
public class Candidat  extends Personne {
    @Column(name = "date_Naissance")

    private LocalDate dateNaissance;
    private String telephone;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Candidature> MesCandidatures;
    public Candidat() {
    }

    public Candidat(String nom, String prenom, Adresse adresse, String email, LocalDate dateNaissance, String telephone, List<Candidature> mesCandidatures) {
        super(nom, prenom, adresse, email);
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        MesCandidatures = mesCandidatures;
    }


    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Candidature> getMesCandidatures() {
        return MesCandidatures;
    }

    public void setMesCandidatures(List<Candidature> mesCandidatures) {
        MesCandidatures = mesCandidatures;
    }
}
