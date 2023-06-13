package fr.helpad;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import fr.helpad.service.WebGouvMedicService;
import fr.helpad.service.WebGouvMedicServiceI;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import fr.helpad.entity.Plat;
import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.service.PlatService;
import fr.helpad.service.StockMedicamentServiceI;

@SpringBootApplication
public class HelpadApplication {

	public static void main(String[] args) {

		ApplicationContext appContext = SpringApplication.run(HelpadApplication.class, args);

		System.out.println("Initialisation complétée.");

		WebGouvMedicServiceI webMedic = appContext.getBean(WebGouvMedicService.class);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				return webMedic.setMedicaments();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Erreur lors de l'exécution de la procédure d'enregistrement des médicaments.";
		});
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Bean
	CommandLineRunner commandLineRunner(StockMedicamentServiceI stockService, WebGouvMedicServiceI medicServ) {
		return args -> {
			Short s = 90;
			// On ajoute un stock de 90 Doliranes.
			try {
			WebGouvMedic med1 = medicServ.get(60234100l);
			stockService.sauvegarder(new StockMedicament(med1.getId(), s));
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			s = 50;
			// 50 Homeoplasmines
			try {
			WebGouvMedic med2 = medicServ.get(61237035l);
			stockService.sauvegarder(new StockMedicament(med2.getId(), s));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			short sss = 0;
			stockService.findByQuantiteGreaterThan(sss, PageRequest.of(0, 50)).forEach(m -> System.out.println(m.getCodeCis() + " : " + m.getQuantite() + " en stock."));
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(PlatService platService) {
		return args -> {
			platService.sauvegarder(new Plat(null, null, "thieb"));
			platService.sauvegarder(new Plat(null, null, "choucroute"));
			platService.sauvegarder(new Plat(null, null, "couscous"));
			platService.sauvegarder(new Plat(null, null, "poulet frit"));
			platService.sauvegarder(new Plat(null, null, "souris d'agneau"));

			platService.listerTout().forEach(p -> System.out.println(p.getNom()));

		};
	}
}
