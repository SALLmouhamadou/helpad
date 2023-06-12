package fr.helpad.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.service.StockMedicamentServiceI;
import fr.helpad.service.WebGouvMedicServiceI;
import fr.helpad.service.WebGouvSecuriteServiceI;

@Controller
public class InfirmerieControllerV2 {
	@Autowired
	WebGouvMedicServiceI medicService;
	@Autowired
	WebGouvSecuriteServiceI infoService;
	@Autowired
	StockMedicamentServiceI stockService;

	@GetMapping("/infirmerie/inventaire")
	public String inventory(Model model) {
		Page<WebGouvMedic> medoc = medicService.findLimited(PageRequest.of(0, 10));
		model.addAttribute("medicaments", medoc);
		model.addAttribute("nombrePage", medicService.count() / 10);
		model.addAttribute("page", 0);
		model.addAttribute("nombre", 10);
		return "backoffice/infirmerie/stock";
	}

	@GetMapping("/infirmerie/inventaire/{page}")
	public String inventoryPage(@PathVariable int page, Model model) {
		System.out.println("[Inventaire Médicament] Page : " + page);
		long medicCount = medicService.count();
		int elementLimit = 10;
		model.addAttribute("page", page);
		model.addAttribute("nombrePage", medicCount / elementLimit);
		model.addAttribute("nombre", elementLimit);
		if (medicCount >= page * elementLimit + elementLimit && page >= 0) {
			Page<WebGouvMedic> medoc = medicService.findLimited(PageRequest.of(page, elementLimit));
			model.addAttribute("medicaments", medoc);
			model.addAttribute("nombre", medoc.getSize());
		} else {
			model.addAttribute("nombre", 0);
			model.addAttribute("alertClass", "alert");
			model.addAttribute("message", " Echec de la récupération, vous avez spécifié une page hors limite.");
		}
		return "backoffice/infirmerie/stock";
	}

	static int addition(int x, int y) {
		return x + y;
	}

	@GetMapping("/infirmerie/inventaire/rechercher")
	public String chercher(Model model, @RequestParam String nom) {
		String recherche = nom;
		System.out.println(recherche);

		List<WebGouvMedic> medicaments = new ArrayList<>();
		if (nom.length() > 0 && recherche.length() < 50) {
			medicaments = medicService.findByNameLimited(recherche, PageRequest.of(0, 50));
			model.addAttribute("medicaments", medicaments);
		} else {
			model.addAttribute("alertClass", "alert");
			model.addAttribute("message", " Echec de la récupération, vous recherchez un nom trop court ou trop long.");
		}

		model.addAttribute("nombre", medicaments.size());

		model.addAttribute("page", 0);
		model.addAttribute("nombrePage", 0);

		return "backoffice/infirmerie/stock";
	}

//	@GetMapping("/prevision-medicament")
//	public String verifStock(Model model) {
//		
//		List<Medicament> medocs = service.listerTout();
//		List<Medicament> renouveler = new ArrayList<>();
//		
//		for (Medicament medic : medocs) {
//			if (medic.getStock() < pres.getConsoMois(medic))
//				renouveler.add(medic);
//		}
//		
//		model.addAttribute("medicaments", renouveler);
//		
//		return "backoffice/verifier-stock";
//	}
//
//	@PostMapping("/rechercher-medicament")
//	public String searchMedic(Model model, HttpServletRequest request, HttpServletResponse response,
//			RedirectAttributes redirectAttributes) {
//		String nom = request.getParameter("nom");
//		System.out.println("Nom : " + nom);
//
//		if (!(nom.length() > 2 && nom.length() <= 30)) {
//			redirectAttributes.addFlashAttribute("message",
//					"Erreur : Le nom recherché doit faire entre 3 et 30 caractères.");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//
//		List<Medicament> medicaments = new ArrayList<>();
//		try {
//			medicaments = service.getByNom(nom);
//		} catch (NoSuchElementException e) {
//			redirectAttributes.addFlashAttribute("message", "Erreur : Aucun élément trouvé.");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-warning alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//
//		if (medicaments.isEmpty()) {
//			redirectAttributes.addFlashAttribute("message", "Erreur : Aucun élément ne correspond à votre recherche.");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-warning alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//
//		model.addAttribute("medicaments", medicaments);
//		return "backoffice/stock-search";
//	}

	@PostMapping("/infirmerie/modifier-stock")
	public String saveStock(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		System.out.println("Entrée dans le controlleur saveStock");

		String idString = request.getParameter("id");
		String quantiteEnStock = request.getParameter("stock");
		String nom = request.getParameter("nom");

		System.out.println("Paramètres : ");
		System.out.println("Nom : " + nom);
		System.out.println("ID : " + idString);
		System.out.println("Stock : " + quantiteEnStock);

		Long id = -1l;

		if (idString != null)
			id = Long.parseLong(idString);
		else {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : Impossible de récupérer l'ID du médicament.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/infirmerie/inventaire";
		}

		List<WebGouvMedic> verifNom;
		WebGouvMedic verifId;
		Boolean isValide = false;

		if (nom.length() > 2 && nom.length() < 200 && id > 0) {
			verifNom = medicService.findByNameLimited(nom, PageRequest.of(0, 1));
			verifId = medicService.get(id);
		} else {
			redirectAttributes.addFlashAttribute("message",
					"Erreur : Le nom du médicament était hors des limites de la taille de recherche.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:/infirmerie/inventaire";
		}

		if (verifNom.size() > 0 && verifNom.get(0).equals(verifId))
			isValide = true;

		if (isValide) {
			short stock = -1;
			try {
				stock = Short.parseShort(quantiteEnStock);
			} catch (NumberFormatException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur : Format du stock invalide.");
				redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
				return "redirect:/infirmerie/inventaire";
			}

			StockMedicament stockMed = stockService.get(id);

			if (stock >= 0 && stock <= 999 && (stock + 100) < stockMed.getQuantite()) {
				stockMed.setQuantite(stock);
				verifId.setStock(stockMed);
			} else {
				redirectAttributes.addFlashAttribute("message",
						"Erreur : La quantité en stock doit être un nombre positif entre 0 et 999");
				redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
				return "redirect:/infirmerie/inventaire";
			}
		}

		System.out.println("Traitement effectué");
		// TODO
		System.out.println("envoyé");
		redirectAttributes.addFlashAttribute("message",
				"Succès de la modification du stock pour : " + verifId.getNom() + ".");
		redirectAttributes.addFlashAttribute("alertClass", "alert alert-success alert-dismissible fade show");
		return "redirect:/inventaire";
	}

//	@PostMapping("/modifier-stock")
//	public String saveStock(@ModelAttribute Medicament medicament, @ModelAttribute Adresse adresse,
//			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		System.out.println("entree dans le controlleur saveStock");
//
//		String quantiteEnStock = request.getParameter("stock");
//		String id = request.getParameter("id");
//
//		System.out.println("Quantité en stock (string) : " + quantiteEnStock);
//		System.out.println("ID (string) : " + id);
//
//		long idReel = -1;
//
//		try {
//			idReel = Long.parseLong(id);
//			System.out.println("ID réel : " + idReel);
//		} catch (NumberFormatException e) {
//			redirectAttributes.addFlashAttribute("message", "Erreur de format sur l'ID.");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//
//		Medicament medic;
//
//		if (idReel >= 0) {
//			try {
//				medic = service.get(idReel);
//			} catch (NoSuchElementException e) {
//				redirectAttributes.addFlashAttribute("message", "Erreur : Aucun élément n'a cet ID.");
//				redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
//				return "redirect:/inventaire";
//			}
//		} else {
//			redirectAttributes.addFlashAttribute("message", "Erreur : l'ID doit être supérieur à 0.");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//
//		int stock = -1;
//		try {
//			stock = Integer.parseInt(quantiteEnStock);
//			System.out.println("Stock :" + stock);
//		} catch (NumberFormatException e) {
//			redirectAttributes.addFlashAttribute("message", "Erreur : Format de stock invalide");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//		if (stock >= 0)
//			medic.setStock(stock);
//		else {
//			redirectAttributes.addFlashAttribute("message", "Erreur : Le stock doit être supérieur à 0.");
//			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
//			return "redirect:/inventaire";
//		}
//
//		System.out.println("Traitement effectué");
//		service.sauvegarder(medic);
//		System.out.println("envoyé");
//		redirectAttributes.addFlashAttribute("message", "Modification effectuée");
//		redirectAttributes.addFlashAttribute("alertClass", "alert alert-success alert-dismissible fade show");
//		return "redirect:/inventaire";
//	}
}
