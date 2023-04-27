package fr.helpad.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @Column(name="condition_general")
    private String conditionGeneral;
    @Column(name="declaration_exactitude_des_informations")
    private String declarationExactitudeDesInformations;
    private String numeroRef;
    private String fileName1;
    private String fileName2;
    private String fileName3;
    private String fileName4;
    private String fileName5;
    private String fileName6;
    private String fileName7;
    private String fileName8;
    private String fileName9;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_status")
    private Status status;
    @OneToOne
    private Candidat candidat;
    public Candidature() {
    }

    public Candidature( String pathologie, String informationComplementaire, LocalDate jourDeCandidature) {
        this.pathologie = pathologie;
        this.informationComplementaire = informationComplementaire;
        this.jourDeCandidature = LocalDate.now();
    }

	public Candidature(String pathologie, String informationComplementaire, LocalDate jourDeCandidature,
			String conditionGeneral, String declarationExactitudeDesInformations, String numeroRef, String fileName1,
			String fileName2, String fileName3, String fileName4, String fileName5, String fileName6, String fileName7, String fileName8,
			String fileName9, Status status, Candidat candidat) {
		super();
		this.pathologie = pathologie;
		this.informationComplementaire = informationComplementaire;
		this.jourDeCandidature = LocalDate.now();;
		this.conditionGeneral = conditionGeneral;
		this.declarationExactitudeDesInformations = declarationExactitudeDesInformations;
		this.numeroRef = generateNumeroReference();
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.fileName3 = fileName3;
		this.fileName4 = fileName4;
		this.fileName5 = fileName5;
		this.fileName6 = fileName6;
		this.fileName7 = fileName7;
		this.fileName8 = fileName8;
		this.fileName9 = fileName9;
		this.status = status;
		this.candidat = candidat;
	}

	private String generateNumeroReference() {
	      // Obtenez le mois et l'année actuels
	      LocalDateTime maintenant = LocalDateTime.now();
	      String mois = String.format("%02d", maintenant.getMonthValue());
	      String annee = String.format("%02d", maintenant.getYear()).substring(2);

	      // Générez quatre chiffres aléatoires
	      Random rand = new Random();
	      String chiffresAleatoires = String.format("%04d", rand.nextInt(10000));

	      // Combinez les parties pour former le numéro de référence
	      String numeroReference = "help" + mois + annee + chiffresAleatoires;

	      return numeroRef;
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

	public String getConditionGeneral() {
		return conditionGeneral;
	}

	public void setConditionGeneral(String conditionGeneral) {
		this.conditionGeneral = conditionGeneral;
	}

	public String getDeclarationExactitudeDesInformations() {
		return declarationExactitudeDesInformations;
	}

	public void setDeclarationExactitudeDesInformations(String declarationExactitudeDesInformations) {
		this.declarationExactitudeDesInformations = declarationExactitudeDesInformations;
	}

	public String getNumeroRef() {
		return numeroRef;
	}

	public void setNumeroRef(String numeroRef) {
		this.numeroRef = numeroRef;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	
	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

	public String getFileName4() {
		return fileName4;
	}

	public void setFileName4(String fileName4) {
		this.fileName4 = fileName4;
	}

	public String getFileName5() {
		return fileName5;
	}

	public void setFileName5(String fileName5) {
		this.fileName5 = fileName5;
	}

	public String getFileName6() {
		return fileName6;
	}

	public void setFileName6(String fileName6) {
		this.fileName6 = fileName6;
	}

	public String getFileName7() {
		return fileName7;
	}

	public void setFileName7(String fileName7) {
		this.fileName7 = fileName7;
	}

	public String getFileName8() {
		return fileName8;
	}

	public void setFileName8(String fileName8) {
		this.fileName8 = fileName8;
	}

	public String getFileName9() {
		return fileName9;
	}

	public void setFileName9(String fileName9) {
		this.fileName9 = fileName9;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
    
}
