package fr.helpad;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import fr.helpad.service.WebGouvMedicService;
import fr.helpad.service.WebGouvMedicServiceI;

import org.springframework.context.annotation.Bean;

import fr.helpad.entity.Plat;
import fr.helpad.service.PlatService;

@SpringBootApplication
public class HelpadApplication {
	
	public static void main(String[] args) {

		ApplicationContext appContext = SpringApplication.run(HelpadApplication.class, args);

		System.out.println("Initialisation complétée.");
		
		WebGouvMedicServiceI webMedic = appContext.getBean(WebGouvMedicService.class);
		
		try {
			System.out.println(webMedic.setMedicaments());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
