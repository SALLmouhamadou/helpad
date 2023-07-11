package fr.helpad;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;
import fr.helpad.entity.StockMedicament;
import fr.helpad.service.AllergeneService;
import fr.helpad.service.PlatService;
import fr.helpad.service.RepasService;
import fr.helpad.service.StockMedicamentServiceI;
import fr.helpad.service.WebGouvMedicService;
import fr.helpad.service.WebGouvMedicServiceI;


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
			stockService.sauvegarder(new StockMedicament(60234100l, s));
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			s = 50;
			// 50 Homeoplasmines
			try {
			stockService.sauvegarder(new StockMedicament(60234100l, s));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			short sss = 0;
			stockService.findByQuantiteGreaterThan(sss, PageRequest.of(0, 50)).forEach(m -> System.out.println(m.getCodeCis() + " : " + m.getQuantite() + " en stock."));
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(PlatService platService, AllergeneService allergeneService, RepasService repasService) {
		return args->{
			
			
//			allergeneService.sauvegarder(new Allergene("gluten"));
//			allergeneService.sauvegarder(new Allergene("crustacés"));
//			allergeneService.sauvegarder(new Allergene("oeufs"));
//			allergeneService.sauvegarder(new Allergene("poissons"));
//			allergeneService.sauvegarder(new Allergene("arachides"));
//			allergeneService.sauvegarder(new Allergene("soja"));
//			allergeneService.sauvegarder(new Allergene("lait"));
//			allergeneService.sauvegarder(new Allergene("fruits à coques"));
//			allergeneService.sauvegarder(new Allergene("céleri"));
//			allergeneService.sauvegarder(new Allergene("moutarde"));
//			allergeneService.sauvegarder(new Allergene("graines de sésame"));
//			allergeneService.sauvegarder(new Allergene("sulfites"));
//			allergeneService.sauvegarder(new Allergene("lupin"));
//			allergeneService.sauvegarder(new Allergene("mollusques"));
			
			Allergene al = new Allergene("gluten");
			Allergene aL1 = new Allergene("crustacés");
			List<Allergene> allergenes = new ArrayList<Allergene>();
			allergenes.add(aL1);
			allergenes.add(al);
			allergeneService.sauvegarder(al);
			allergeneService.sauvegarder(aL1);
			
			Plat plat = new Plat( allergenes, "thieb");
			Plat plat1 = new Plat( allergenes, "poulets");
			
			List<Plat> plats = new ArrayList<Plat>();
			plats.add(plat);
			plats.add(plat1);
			platService.sauvegarder(plat1);
			platService.sauvegarder(plat);
			
//			List<Allergene> listAllergene = new ArrayList<Allergene>();
//			listAllergene.add(allergeneService.get((long) 1));
//			listAllergene.add(allergeneService.get((long) 3));
//			listAllergene.add(allergeneService.get((long) 5));
//			platService.sauvegarder(new Plat(null, listAllergene, "thieb"));
//	CommandLineRunner commandLineRunner(PlatService platService) {
//		return args -> {
//			platService.sauvegarder(new Plat(null, null, "thieb"));
//
//			platService.sauvegarder(new Plat(null, null, "choucroute"));
//			platService.sauvegarder(new Plat(null, null, "couscous"));
//			platService.sauvegarder(new Plat(null, null, "poulet frit"));
//			platService.sauvegarder(new Plat(null, null, "souris d'agneau"));

			
//			List<Plat> listPlats1 = new ArrayList<Plat>();
//			listPlats1.add(platService.get((long)1));
//			listPlats1.add(platService.get((long)2));
//			List<Plat> listPlats2 = new ArrayList<Plat>();
//			listPlats1.add(platService.get((long)3));
			
//			repasService.sauvegarder(new Repas("dejeuner", listPlats1, LocalDate.of(2023, 06, 01)));
//			repasService.sauvegarder(new Repas("diner", listPlats2, LocalDate.of(2023, 06, 01)));
//			Repas repas1 = new Repas();
//			repas1.setDateRepas(LocalDate.of(2023, 01, 01));
//			
//			platService.listerTout().forEach(p->
//			System.out.println(p.getNom()));
			
			allergeneService.listerTout().forEach(a->
			System.out.println(a.getNomAllergene()));


			platService.listerTout().forEach(p -> System.out.println(p.getNom()));


		};
	}
}
