package fr.helpad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Personne;
import fr.helpad.service.CandidatService;
import fr.helpad.service.PersonneService;

@SpringBootApplication
public class HelpadApplication {

	public static void main(String[] args) {
		
		ApplicationContext appContext =SpringApplication.run(HelpadApplication.class, args);
		PersonneService personneService = appContext.getBean(PersonneService.class);
		Personne personne1 = new Personne("SALL", "Mouhamadou", new Adresse("12" ,"avenue de la gare", "Longjumeau", "91160"), "sallmouhamadou.pro@gmail.com", "azerty" );
		Personne personne2 = new Personne("Ba", "Moussa", new Adresse("12" ,"rue de l'observatoire", "Trappes", "78190"), "ba.moussa@gmail.com", "qwerty" );
		Personne personne3 = new Personne("Plasse", "Michel", new Adresse("37" ,"rue de l'elise", "Paris", "75016"), "m.plasse@free.fr", "qsdfghj" );
		//Personne personne4 = new Personne("SALL", "Mouhamadou", new Adresse("12" ,"avenue de la gare", "Longjumeau", "91160"), "sallmouhamadou.pro@gmail.com", "azerty" );
		personneService.sauvePersonne(personne1);
		personneService.sauvePersonne(personne2);
		personneService.sauvePersonne(personne3);

	CandidatService candidatService = appContext.getBean(CandidatService.class);
		//Candidat candidat = new Candidat("SALL", "Mouhamadou", new Adresse("12" ,"avenue de la gare", "Longjumeau", "91160"), "sallmouhamadou.pro@gmail.com", "azerty",new LocalDate(2000-05-31),)
	}

}
