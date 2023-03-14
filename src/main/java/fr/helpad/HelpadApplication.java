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
		
		ArrayList<Candidature> mesCandidatures= new ArrayList<Candidature>();
		mesCandidatures.add(new Candidature("Asthmatique", "Complétement con" , LocalDate.now()));
		
		Candidat c1 =new Candidat("SALL", "Mouhamadou", "0753051721","salllmouha10@gmail.com",new Adresse("6", "avenue de la gare", "Longjumeau ","9300"), 
				LocalDate.of(2000,05,31),LocalDate.of(2023,03,13), "1000599341125", "278654W", 40000, mesCandidatures);
		
		candidat.sauveCandidat(c1);
	}

}
