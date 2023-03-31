package fr.helpad.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Medicament;
import fr.helpad.service.MedicamentService;

@Controller
public class InfirmerieController {
	
	@Autowired
	MedicamentService service;
	
	@GetMapping("/inventaire")
	public String inventory() {
		return "backoffice/stock";
	}
	
	@PostMapping("/modifier-stock")
	public String saveStock(@ModelAttribute Medicament medicament,
			@ModelAttribute Adresse adresse, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("entree dans le controlleur saveStock");
		
		Medicament medic = service.get(medicament.getIdMedicament());
		if (!(medicament.equals(medic))){
			System.out.println("Le médicament entré a subi des modifications hors de l'application.");
			return "Erreur de traitement, veuillez réitérer votre demande.";
		}
		
		String quantite = request.getParameter("quantite");
		System.out.println("Quantité à stocker (string) : " + quantite);
		int stock = -1;
		try {
			stock = Integer.parseInt(quantite);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "La quantité entrée doit être un nombre entier positif.";
		}
		if (stock >= 0)
			medicament.setStock(stock);
		else
			return "La quantité en stock doit être un nombre entier positif.";

		System.out.println("Traitement effectué");
		service.sauvegarder(medicament);
		System.out.println("envoyé");
		return "redirect:confirmation";
	}
}
