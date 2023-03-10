package fr.helpad;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;
import fr.helpad.service.CandidatService;

@SpringBootApplication
public class HelpadApplication {

	public static void main(String[] args) {
		
		ApplicationContext appContext =SpringApplication.run(HelpadApplication.class, args);
		
		CandidatService candidat = appContext.getBean(CandidatService.class);
		
		Adresse a = new Adresse();
		a.setCodePostal(91300);
		a.setNumero(20);
		a.setPays("FRANCE");
		a.setRue("rue Vaugirard");
		a.setVille("JUVISY");
		
		Adresse a1 = new Adresse();
		a1.setCodePostal(91430);
		a1.setNumero(51);
		a1.setPays("FRANCE");
		a1.setRue("rue des médecins");
		a1.setVille("MASSY");
		
		ArrayList<Candidature> mesCandidatures1= new ArrayList<Candidature>();
		mesCandidatures1.add(new Candidature("Asthmatique", "Complétement con" , LocalDate.now()));
		
		ArrayList<Candidature> mesCandidatures2= new ArrayList<Candidature>();
		mesCandidatures1.add(new Candidature("Grippe", "Vraiment con" , LocalDate.now()));
		
		Candidat c1 =new Candidat("SALL", "Mouhamadou", "0753051721","salllmouha10@gmail.com",a, 
				LocalDate.of(2000,05,31),LocalDate.of(2023,03,13), "1000599341125", "278654W", 40000, mesCandidatures1);
		
		Candidat c2 =new Candidat("ROUCHON", "Damien", "0753051721",
				"damien.rouchon@gmail.com",a1 ,LocalDate.of(1995,05,23),LocalDate.of(2023,03,13), 
				"19505342252","1234567W", 60000, mesCandidatures2);
		
		candidat.sauveCandidat(c1);
		candidat.sauveCandidat(c2);
		
//		ArrayList<Pensionnaire> pensionnaireList = new ArrayList<Pensionnaire>();
//		ArrayList<Visite> visiteList = new ArrayList<Visite>();
//		ArrayList<Medecin> medecinList = new ArrayList<>();
//		ArrayList<Employe> employeList = new ArrayList<>();
//
//		AdresseBusiness adrBusiness = (AdresseBusiness) appContext.getBean("adresseBusiness");
//		
//		a = adrBusiness.sauvegarder(a);
//		a1 = adrBusiness.sauvegarder(a1);
//
//		Personne p = new Personne();
//
//		p.setAdresse(a);
//		p.setEmail("tinkiet@pas.lol");
//		p.setNom("Poulain");
//		p.setPrenom("Bernard");
//		p.setPassword("camarcheEnthropie63");
//		p.setTelephone("0659823518");
//
//		Etage e = new Etage();
//		e.setEtageSecurise(true);
//
//		Chambre c = new Chambre();
//		c.setChambreDouble(false);
//		c.setEtage(e);
//		c.setNoChambre("101");
//
//		ArrayList<Chambre> cList = new ArrayList<Chambre>();
//		cList.add(c);
//
//		EtageBusiness etBusiness = (EtageBusiness) appContext.getBean("etageBusiness");
//		etBusiness.sauvegarder(e);
//
//		e.setChambres(cList);
//
//		ChambreBusiness chBusiness = (ChambreBusiness) appContext.getBean("chambreBusiness");
//		chBusiness.sauvegarder(c);
//
//		//Medecin m = new Medecin("Le Boucher", "Bertrand", "0652348591", a1, "leboucher.bertrand@doctissimo.fr",
//				//"lullaby", pensionnaireList, "généraliste", visiteList);
//		//medecinList.add(m);
//
//		//Pensionnaire papy = new Pensionnaire("Pitivier", "Jean", "0653026590", a, "telepathe@dudimanche.fr",
//				//"motDePasseVieux52", c, "135049190090080", medecinList, visiteList, p);
//
//		PersonneServiceImpl persService = (PersonneServiceImpl) appContext.getBean("personneBusiness");
//		
//		persService.sauvePersonne(p);
//
//		//Visite visite = new Visite(new Date(123, 2, 8), m, papy);
//
//		MédecinBusiness mBusiness = (MédecinBusiness) appContext.getBean("medecinBusiness");
//		//mBusiness.sauvegarder(m);
//
//		PensionnaireBusiness pBusiness = (PensionnaireBusiness) appContext.getBean("pensionnaireBusiness");
//		//pBusiness.sauvegarder(papy);
//
//		//visiteList.add(visite);
//
//		VisiteBusiness vBusiness = (VisiteBusiness) appContext.getBean("visiteBusiness");
//		//vBusiness.sauvegarder(visite);
//		
//		//Ordonnance o = new Ordonnance(new Date(123,1,8), m, papy, "JAVAPATH");
//		
//		OrdonnanceBusiness oBusiness = (OrdonnanceBusiness) appContext.getBean("ordonnanceBusiness");
//		//oBusiness.sauvegarder(o);
//		
//		Infirmiere inf = new Infirmiere();
//		inf.setAdresse(a1);
//		inf.setEmail("brigitte.bardot@infirmiere.com");
//		
//		employeList.add(inf);
//		
//		Fonction f = new Fonction("infirmiere", employeList);
//
//		inf.setFonction(f);
//		inf.setNom("BIRDOT");
//		inf.setNoRpps("123456789123456");
//		inf.setNoSecu("012345678912345");
//		inf.setPassword("mypasswordisWeak");
//		inf.setPrenom("Brigitte");
//		inf.setTelephone("0667895234");
//		
//		InfirmiereBusiness iBusiness = (InfirmiereBusiness) appContext.getBean("infirmiereBusiness");
//		
//		
//		FonctionBusiness fBusiness = (FonctionBusiness) appContext.getBean("fonctionBusiness");
//		fBusiness.sauvegarder(f);
//		iBusiness.sauvegarder(inf);
//		
//		Medicament medic = new Medicament();
//		medic.setFonction("antalgique");
//		medic.setNom("Doliprane");
//		medic.setStock(15);
//		
//		MédicamentBusiness medicBusiness = (MédicamentBusiness) appContext.getBean("medicamentBusiness");
//		medic = medicBusiness.sauvegarder(medic);
//		
//		PriseMedicament pM = new PriseMedicament();
//		pM.setHeure(new Date(123, 1, 8, 12, 15));
//		pM.setIdInfirmiere(inf.getIdPersonne());
//		pM.setIdMedicament(medic.getIdMedicament());
//		pM.setIdPensionnaire(papy.getIdPersonne());
//		
//		System.out.println(pM.toString());
//		
//		PriseMedicamentBusiness pMBusiness = (PriseMedicamentBusinessImpl) appContext.getBean("priseMedocBusiness");
//		pM = pMBusiness.sauvegarder(pM);
//		
//		Prescription pr = new Prescription();
//		pr.setDateDebutTraitement(new Date(123,1,8));
//		pr.setDateFinTraitement(new Date(123,1,13));
//		pr.setIdMedicament(medic.getIdMedicament());
//		pr.setIdPensionnaire(papy.getIdPersonne());
//		pr.setPosologie("1x/j; voie orale");
//		
//		System.out.println(pr.toString());
//		
//		PrescriptionBusinessI prBusiness = (PrescriptionBusiness) appContext.getBean("prescrptionBusiness");
//		pr = prBusiness.sauvegarder(pr);
	}

}
