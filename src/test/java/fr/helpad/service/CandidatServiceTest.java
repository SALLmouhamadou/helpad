package fr.helpad.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;
import fr.helpad.entity.Status;
import fr.helpad.repository.CandidatRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidatServiceTest {
	private static final Long id = 1L;
	private static final String  name = "Ba";
	private static final String lastName ="Lamine";
	private static final String email ="lamine.ba@gmaill.com";
	private static final String phone = "0652755715";
	private static final LocalDate birthDay = LocalDate.of(1959, 10, 02);
	private static final int number = 04;
	private static final String street = "square du clos de villaine";
	private static final String city ="Massy";
	private static final int postCode =91300;
	private static final String countrie ="France";
	private static final LocalDate dayStart = LocalDate.of(2023, 06, 20);
	private static final LocalDate dayCandidature = LocalDate.now();
	private static final String pathologie= "oui";
	private static final String libelle ="En cours de traitement";
	private static final Long idCandidature = 1L;
	private static final String infos = "A besoin de surveillance";
	private static final String genre = "Monsieur";
	private static final String nCaf = "2305473W";
	private static final String numSecu = "1591099341251";
	private static final double revenu= 400000;
	
	@Autowired
	CandidatService candidatService;
	
	@MockBean
	private CandidatRepository candidatRepository;
	
	
	@Test
	public void shouldSaveCandidatWithSuccess() {
		// creation d'un candidat test
		Adresse adresse = new Adresse();
		adresse.setNumero(number);
		adresse.setRue(street);
		adresse.setVille(city);
		adresse.setCodePostal(postCode);
		adresse.setPays(countrie);
		
		
		Status status = new Status();
		status.setLibelle(libelle);
		
		Candidature candidature =new Candidature();
		candidature.setStatus(status);
		candidature.setIdCandidature(idCandidature);
		candidature.setJourDeCandidature(dayCandidature);
		candidature.setPathologie(pathologie);
		candidature.setInformationComplementaire(infos);
		candidature.setConditionGeneral("accepter");
		candidature.setDeclarationExactitudeDesInformations("accepter");
		
		List<Candidature> candidatures = new ArrayList<Candidature>();
		candidatures.add(candidature);
		
		final Candidat candidat =new Candidat();
		candidat.setIdPersonne(id);
		candidat.setNom(name);
		candidat.setPrenom(lastName);
		candidat.setEmail(email);
		candidat.setTelephone(phone);
		candidat.setDateNaissance(birthDay);
		candidat.setAdresse(adresse);
		candidat.setDateEntree(dayStart);
		candidat.setCivilite(genre);
		candidat.setNumeroDeCaf(nCaf);
		candidat.setNumeroSecuriteSocial(numSecu);
		candidat.setRevenu(revenu);
		candidat.setMesCandidatures(candidatures);
		
		
		Mockito.when(this.candidatRepository.save(candidat)).thenReturn(candidat);
		
	
		final Long idPersonne =id;
		final String nom = name;
		final String prenom = lastName;
		final String emailCandidat = email;
		final Long idcandidature=idCandidature;
		final String numref =candidature.getNumeroRef();
		final LocalDate dayOfCandidature = candidature.getJourDeCandidature();
		//WHEN
		final Candidat saveCandidat = candidatService.sauveCandidat(candidat);
		
		
		//THEN
		assertNotNull(saveCandidat);
		assertNotNull(idPersonne);
		assertEquals(idPersonne, saveCandidat.getIdPersonne());
		assertEquals(nom, saveCandidat.getNom());
		assertEquals(prenom,saveCandidat.getPrenom());
		assertEquals(emailCandidat,saveCandidat.getEmail());
		assertEquals(LocalDate.of(1959, 10, 02), saveCandidat.getDateNaissance());
		assertEquals("Monsieur",saveCandidat.getCivilite());
		assertEquals("0652755715",saveCandidat.getTelephone());
		assertEquals(LocalDate.of(2023, 06, 20),saveCandidat.getDateEntree());
		assertEquals("2305473W",saveCandidat.getNumeroDeCaf());
		assertEquals("1591099341251",saveCandidat.getNumeroSecuriteSocial());
		//Verifier les candidatures
		List<Candidature> can=saveCandidat.getMesCandidatures();
		for (Candidature c : can) {
			assertEquals(idcandidature, c.getIdCandidature());
			assertEquals("oui", c.getPathologie());
			assertEquals("En cours de traitement", c.getStatus().getLibelle());
			assertEquals("accepter", c.getConditionGeneral());
			assertEquals("accepter", c.getDeclarationExactitudeDesInformations());
			assertEquals(dayOfCandidature, c.getJourDeCandidature());
			assertEquals(idcandidature, c.getIdCandidature());
			assertEquals(numref, c.getNumeroRef());
			assertEquals("A besoin de surveillance", c.getInformationComplementaire());
		}
		// verifer le adresse
		assertEquals(04, saveCandidat.getAdresse().getNumero());
		assertEquals("square du clos de villaine", saveCandidat.getAdresse().getRue());
		assertEquals("Massy", saveCandidat.getAdresse().getVille());
		assertEquals(91300, saveCandidat.getAdresse().getCodePostal());
		assertEquals("France", saveCandidat.getAdresse().getPays());
	}
	 
	//stream().map(Candidature ::getIdCandidature).anyMatch(numref -> ref.equals(candidature.getNumeroRef()))
}
