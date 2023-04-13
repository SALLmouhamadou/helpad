package fr.helpad.entity;

import java.time.LocalDate;

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
    private String numeroRef="HELP1002";
    private String fileName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_status")
    private Status status;
    @OneToOne
    //@JoinColumn(name="id_personne")
    private Candidat candidat;
    public Candidature() {
    }

    public Candidature( String pathologie, String informationComplementaire, LocalDate jourDeCandidature) {
        this.pathologie = pathologie;
        this.informationComplementaire = informationComplementaire;
        this.jourDeCandidature = LocalDate.now();
    }
    
    

	public Candidature(String pathologie, String informationComplementaire, LocalDate jourDeCandidature,
			String conditionGeneral, String declarationExactitudeDesInformations, String numeroRef, String fileName,
			Candidat candidat) {
		super();
		this.pathologie = pathologie;
		this.informationComplementaire = informationComplementaire;
		this.jourDeCandidature = jourDeCandidature;
		this.conditionGeneral = conditionGeneral;
		this.declarationExactitudeDesInformations = declarationExactitudeDesInformations;
		this.numeroRef = numeroRef;
		this.fileName = fileName;
		this.candidat = candidat;
	}

	

	public Candidature(String pathologie, String informationComplementaire, LocalDate jourDeCandidature,
			String conditionGeneral, String declarationExactitudeDesInformations, String numeroRef, String fileName,
			Status status, Candidat candidat) {
		super();
		this.pathologie = pathologie;
		this.informationComplementaire = informationComplementaire;
		this.jourDeCandidature = jourDeCandidature;
		this.conditionGeneral = conditionGeneral;
		this.declarationExactitudeDesInformations = declarationExactitudeDesInformations;
		this.numeroRef = numeroRef;
		this.fileName = fileName;
		this.status = status;
		this.candidat = candidat;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
