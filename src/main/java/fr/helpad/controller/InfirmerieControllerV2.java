package fr.helpad.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMAJDate;
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
		model.addAttribute("nombre", medicService.count());
		model.addAttribute("dateMaj", WebGouvMAJDate.dateMiseAJour);
		return "backoffice/infirmerie/stock";
	}

	@GetMapping("/infirmerie/inventaire/page/{page}")
	public String inventoryPage(@PathVariable String page, Model model) {
		Integer pageInt = 0;
		long medicCount = medicService.count();
		int elementLimit = 10;
		long pageLimite = medicCount / elementLimit;

		model.addAttribute("dateMaj", WebGouvMAJDate.dateMiseAJour);
		model.addAttribute("nombrePage", pageLimite);
		model.addAttribute("nombre", medicCount);

		try {
			pageInt = Integer.parseInt(page);
		} catch (Exception e) {
			model.addAttribute("page", 0);
			model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			model.addAttribute("message", "Erreur, numéro de page invalide");
			return "backoffice/infirmerie/stock";
		}
		model.addAttribute("page", pageInt);
		if (pageInt >= 0 && pageInt <= pageLimite) {
			Page<WebGouvMedic> medoc = medicService.findLimited(PageRequest.of(pageInt, elementLimit));
			model.addAttribute("medicaments", medoc);
		} else {
			model.addAttribute("nombre", 0);
			model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			model.addAttribute("message", "Echec de la récupération, vous avez spécifié une page hors limite.");
		}
		return "backoffice/infirmerie/stock";
	}

	@GetMapping("/infirmerie/inventaire/stock/{page}")
	public String stockPage(@PathVariable String page, Model model) {
		Integer pageInt = 0;
		long medicCount = stockService.countPositive();
		int elementLimit = 10;
		long pageLimite = medicCount / elementLimit;

		model.addAttribute("dateMaj", WebGouvMAJDate.dateMiseAJour);
		model.addAttribute("nombrePage", pageLimite);
		model.addAttribute("nombre", medicCount);

		try {
			pageInt = Integer.parseInt(page);
		} catch (Exception e) {
			model.addAttribute("page", 0);
			model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			model.addAttribute("message", "Erreur, numéro de page invalide");
			return "backoffice/infirmerie/stock";
		}
		model.addAttribute("page", pageInt);
		if (pageInt >= 0 && pageInt <= pageLimite) {
			short s = 0;
			model.addAttribute("checkedStock", true);
			List<StockMedicament> stockList = stockService.findByQuantiteGreaterThan(s,
					PageRequest.of(pageInt, elementLimit));
			List<WebGouvMedic> medicaments = new ArrayList<>();
			for (StockMedicament stock : stockList) {
				medicaments.add(medicService.get(stock.getCodeCis()));
			}

			model.addAttribute("medicaments", medicaments);
		} else {
			model.addAttribute("nombre", 0);
			model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			model.addAttribute("message", "Echec de la récupération, vous avez spécifié une page hors limite.");
		}
		return "backoffice/infirmerie/stock";
	}

	static int addition(int x, int y) {
		return x + y;
	}

	@GetMapping("/infirmerie/inventaire/rechercher")
	public String chercher(Model model, @RequestParam(defaultValue = "") String nom,
			@RequestParam(defaultValue = "off") String isStock, @RequestParam(defaultValue = "0") String page) {
		model.addAttribute("dateMaj", WebGouvMAJDate.dateMiseAJour);

		model.addAttribute("recherche", nom);

		System.out.println("nom: " + nom + " isStock: " + isStock + " page: " + page);
		int elementLimit = 10;
		int pageInt = 0;

		try {
			pageInt = Integer.parseInt(page);
		} catch (Exception e) {
			model.addAttribute("alertClass", "alert");
			model.addAttribute("message", " Erreur : Requête invalide");
		}

		model.addAttribute("dateMaj", WebGouvMAJDate.dateMiseAJour);
		model.addAttribute("nombrePage", 0);

		model.addAttribute("page", pageInt);

		List<WebGouvMedic> medicaments = new ArrayList<>();
		if (nom.length() >= 0 && nom.length() < 50) {
			if (isStock != null && isStock.equals("on")) {
				if (nom.equals(""))
					return "redirect:/infirmerie/inventaire/stock/0";
				short s = 0;
				if (stockService.countPositive() / elementLimit < pageInt) {
					model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
					model.addAttribute("message", "Erreur : Requête invalide");
					return "redirect:/infirmerie/inventaire/stock/0";
				}
				model.addAttribute("checkedStock", true);
				List<StockMedicament> stockList = stockService.findByQuantiteGreaterThan(s,
						PageRequest.of(pageInt, 50));
				model.addAttribute("alertClass", "alert alert-info alert-dismissible fade show");
				model.addAttribute("message", "Cette page est limitée à 50 éléments pour des raisons techniques." + 
				" Si vous ne trouvez pas l'objet de votre recherche, veuillez préciser la recherche.");
				for (StockMedicament stock : stockList) {
					WebGouvMedic med = medicService.get(stock.getCodeCis());
					if (med.getNom().toLowerCase().contains(nom.toLowerCase()))
						medicaments.add(med);
				}
				model.addAttribute("medicaments", medicaments);
				model.addAttribute("page", 0);
				model.addAttribute("nombrePage", 0);
				model.addAttribute("nombre", medicaments.size());
			} else {
				if (medicService.count() / elementLimit < pageInt) {
					model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
					model.addAttribute("message", " Erreur : Requête invalide");
					return "redirect:/infirmerie/inventaire/stock/0";
				}
				medicaments = medicService.findByNameLimited(nom, PageRequest.of(pageInt, elementLimit));
				model.addAttribute("medicaments", medicaments);
				long elementCount = medicService.countByName(nom);
				model.addAttribute("nombrePage", elementCount / elementLimit);
				model.addAttribute("nombre", elementCount);
				System.out.println(medicService.countByName(nom));
			}
		} else {
			model.addAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			model.addAttribute("message", " Echec de la récupération, vous recherchez un nom trop court ou trop long.");
		}

		model.addAttribute("mode", "rechercher");
		model.addAttribute("page", pageInt);

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

	@Transactional
	@PostMapping("/infirmerie/modifier-stock")
	public String saveStock(final HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {

		// Section de vérification inutile et inefficace
		String requestUrl = request.getHeader("referer");
		String domainUrl = requestUrl.substring(0, requestUrl.indexOf('/', 8));
		requestUrl = requestUrl.substring(requestUrl.indexOf('/', 8), requestUrl.length());

		final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

		if (!domainUrl.equals(baseUrl)) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			redirectAttributes.addFlashAttribute("message", "Erreur : Vous avez été redirigé depuis un site tiers.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:" + "/infirmerie/inventaire";
		}
		// Fin de section

		String[] idsString = request.getParameterValues("id");
		String[] quantitesEnStock = request.getParameterValues("stock");
		String[] noms = request.getParameterValues("nomMedic");
		int nbModif = 0;

		if (idsString == null || idsString.length != quantitesEnStock.length || idsString.length != noms.length
				|| idsString.length == 0) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			redirectAttributes.addFlashAttribute("message", "Erreur : Requête invalide.");
			redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger alert-dismissible fade show");
			return "redirect:" + requestUrl;
		} else {
			int index = 0;
			for (String idString : idsString) {
				String nom = noms[index];
				String quantiteEnStock = quantitesEnStock[index];
				index++;
				Long id = -1l;

				if (idString != null)
					id = Long.parseLong(idString);
				else {
					TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
					redirectAttributes.addFlashAttribute("message",
							"Erreur : Impossible de récupérer l'ID du médicament.");
					redirectAttributes.addFlashAttribute("alertClass",
							"alert alert-danger alert-dismissible fade show");
					return "redirect:" + requestUrl;
				}

				List<WebGouvMedic> verifNom;
				WebGouvMedic verifId;
				Boolean isValide = false;

				if (nom.length() > 2 && nom.length() < 1000 && id > 0) {
					verifNom = medicService.findByNameExactLimited(nom, PageRequest.of(0, 1));
					verifId = medicService.get(id);
				} else {
					System.out.println("nom : " + nom + " id : " + id);
					TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
					redirectAttributes.addFlashAttribute("message",
							"Erreur : Le nom du médicament était hors des limites de la taille de recherche.");
					redirectAttributes.addFlashAttribute("alertClass",
							"alert alert-danger alert-dismissible fade show");
					return "redirect:" + requestUrl;
				}

				if (verifNom.size() > 0 && verifNom.get(0).equals(verifId))
					isValide = true;

				if (isValide) {
					short stock = -1;
					try {
						stock = Short.parseShort(quantiteEnStock);
					} catch (NumberFormatException e) {
						TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
						redirectAttributes.addFlashAttribute("message", "Erreur : Format du stock invalide.");
						redirectAttributes.addFlashAttribute("alertClass",
								"alert alert-danger alert-dismissible fade show");
						return "redirect:" + requestUrl;
					}

					if (stock == verifId.getStock().getQuantite())
						continue;

					if (stock >= 0 && stock <= 999) {
						verifId.getStock().setQuantite(stock);
						nbModif++;
					} else {
						TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
						redirectAttributes.addFlashAttribute("message",
								"Erreur : La quantité en stock doit être un nombre positif entre 0 et 999");
						redirectAttributes.addFlashAttribute("alertClass",
								"alert alert-danger alert-dismissible fade show");
						return "redirect:" + requestUrl;
					}
				} else {
					System.out.println("[" + index + "]" + " IsValide est faux. verifNom.size = " + verifNom.size()
							+ (verifNom.size() > 0 ? " et verifNom == verifId " + verifNom.get(0).equals(verifId)
									+ " verifNom nom et id : " + verifNom.get(0).getNom() + " "
									+ verifNom.get(0).getId() : "")
							+ " verifId nom et id : " + verifId.getNom() + " " + verifId.getId());
					TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
					redirectAttributes.addFlashAttribute("message", "Erreur : Requête invalide");
					redirectAttributes.addFlashAttribute("alertClass",
							"alert alert-danger alert-dismissible fade show");
					return "redirect:" + requestUrl;
				}

				medicService.sauvegarder(verifId);
			}
			if (nbModif > 0) {
				redirectAttributes.addFlashAttribute("message", "Succès de la modification du stock" + ".");
				redirectAttributes.addFlashAttribute("alertClass", "alert alert-success alert-dismissible fade show");
				return "redirect:" + requestUrl;
			} else {
				redirectAttributes.addFlashAttribute("message", "Aucune modification n'a été apportée au stock.");
				redirectAttributes.addFlashAttribute("alertClass", "alert alert-info alert-dismissible fade show");
				return "redirect:" + requestUrl;
			}
		}
	}
}
