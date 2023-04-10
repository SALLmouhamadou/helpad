package fr.helpad.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Medicament;
import fr.helpad.entity.Medicament.typeMedicament;
import fr.helpad.service.MedicamentServiceI;
import fr.helpad.service.PrescriptionServiceI;

@Controller
public class InfirmerieController {

	@Autowired
	MedicamentServiceI service;
	@Autowired
	PrescriptionServiceI pres;

	@GetMapping("/inventaire")
	public String inventory(Model model) {
		model.addAttribute("medicaments", service.listerTout());
		return "backoffice/stock";
	}

	@GetMapping("/ajouter-medicament")
	public String addMedic() {
		return "backoffice/add-medic";
	}
	
	@GetMapping("/prevision-medicament")
	public String verifStock(Model model) {
		
		List<Medicament> medocs = service.listerTout();
		List<Medicament> renouveler = new ArrayList<>();
		
		for (Medicament medic : medocs) {
			if (medic.getStock() < pres.getConsoMois(medic))
				renouveler.add(medic);
		}
		
		model.addAttribute("medicaments", renouveler);
		
		return "backoffice/verifier-stock";
	}

	@PostMapping("/rechercher-medicament")
	public String searchMedic(Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String nom = request.getParameter("nom");
		System.out.println("Nom : " + nom);

		if (!(nom.length() > 2 && nom.length() <= 30)) {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : Le nom recherché doit faire entre 3 et 30 caractères.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/inventaire";
		}

		List<Medicament> medicaments = new ArrayList<>();
		try {
			medicaments = service.getByNom(nom);
		} catch (NoSuchElementException e) {
			redirectAttributes.addFlashAttribute("message", "Erreur : Aucun élément trouvé.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-warning alert-dismissible fade show");
			return "redirect:/inventaire";
		}

		if (medicaments.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Erreur : Aucun élément ne correspond à votre recherche.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-warning alert-dismissible fade show");
			return "redirect:/inventaire";
		}

		model.addAttribute("medicaments", medicaments);
		return "backoffice/stock-search";
	}

	@PostMapping("/ajout-medicament")
	public String saveMedic(@ModelAttribute Medicament medicament, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		System.out.println("entree dans le controlleur saveMedic");

		String quantiteEnStock = request.getParameter("stock");
		String quantiteParBoite = request.getParameter("quantiteParBoite");
		String nom = request.getParameter("nom");
		String fonction = request.getParameter("fonction");
		String typeStock = request.getParameter("typeStock");

		System.out.println("Quantité en stock (string) : " + quantiteEnStock);
		System.out.println("Quantité par boîte (string) : " + quantiteParBoite);
		System.out.println("Nom : " + nom);
		System.out.println("Fonction : " + fonction);
		System.out.println("typeStock (string) : " + typeStock);

		if (nom.length() > 2 && nom.length() < 30)
			medicament.setNom(nom);
		else {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : Le nom du médicament doit contenir entre 3 et 30 caractères.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}

		List<Medicament> medoc = new ArrayList<Medicament>();

		try {
			medoc = service.getByNom(nom);
		} catch (NoSuchElementException e1) {

		}

		if (!medoc.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Erreur : Ce médicament est déjà renseigné.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}

		if (fonction.length() > 2 && fonction.length() < 40)
			System.out.println("TODO");
			//TODO
			//medicament.setFonction(fonction);
		else {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : La fonction du médicament doit contenir entre 3 et 40 caractères.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}

		if (typeStock.equalsIgnoreCase("LIQUIDE"))
			medicament.setTypeStock(typeMedicament.LIQUIDE);
		else if (typeStock.equalsIgnoreCase("UNITAIRE"))
			medicament.setTypeStock(typeMedicament.UNITAIRE);
		else if (typeStock.equalsIgnoreCase("INHALATION"))
			medicament.setTypeStock(typeMedicament.INHALATION);
		else {
			redirectAttributes.addFlashAttribute("message", "Erreur : Type de stockage indéfini.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}

		int stock = -1;
		try {
			stock = Integer.parseInt(quantiteEnStock);
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("message", "Erreur : Format du stock invalide.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}
		if (stock >= 0 && stock <= 999)
			medicament.setStock(stock);
		else {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : La quantité en stock doit être un nombre positif entre 0 et 999");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}

		int intParBoite = -1;
		try {
			intParBoite = Integer.parseInt(quantiteParBoite);
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : Le format de la quantité par boîte est invalide.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}
		if (intParBoite > 0 && intParBoite <= 10000)
			medicament.setQuantiteParBoite(intParBoite);
		else {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : Quantité par boîte incorrecte, elle doit être comprise entre 1 et 10000");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/ajouter-medicament";
		}

		System.out.println("Traitement effectué");
		service.sauvegarder(medicament);
		System.out.println("envoyé");
		redirectAttributes.addFlashAttribute("message", "Succès de l'ajout du médicament.");
		redirectAttributes.addFlashAttribute("alertClass", "alert alert-success alert-dismissible fade show");
		return "redirect:/inventaire";
	}

	@PostMapping("/modifier-stock")
	public String saveStock(@ModelAttribute Medicament medicament, @ModelAttribute Adresse adresse,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		System.out.println("entree dans le controlleur saveStock");

		String quantiteEnStock = request.getParameter("stock");
		String id = request.getParameter("id");

		System.out.println("Quantité en stock (string) : " + quantiteEnStock);
		System.out.println("ID (string) : " + id);

		long idReel = -1;

		try {
			idReel = Long.parseLong(id);
			System.out.println("ID réel : " + idReel);
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("message", "Erreur de format sur l'ID.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/inventaire";
		}

		Medicament medic;

		if (idReel >= 0) {
			try {
				medic = service.get(idReel);
			} catch (NoSuchElementException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur : Aucun élément n'a cet ID.");
				redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
				return "redirect:/inventaire";
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Erreur : l'ID doit être supérieur à 0.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/inventaire";
		}

		int stock = -1;
		try {
			stock = Integer.parseInt(quantiteEnStock);
			System.out.println("Stock :" + stock);
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("message", "Erreur : Format de stock invalide");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/inventaire";
		}
		if (stock >= 0)
			medic.setStock(stock);
		else {
			redirectAttributes.addFlashAttribute("message", "Erreur : Le stock doit être supérieur à 0.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/inventaire";
		}

		System.out.println("Traitement effectué");
		service.sauvegarder(medic);
		System.out.println("envoyé");
		redirectAttributes.addFlashAttribute("message", "Modification effectuée");
		redirectAttributes.addFlashAttribute("alertClass", "alert alert-success alert-dismissible fade show");
		return "redirect:/inventaire";
	}
}
