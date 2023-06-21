package fr.helpad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;
import fr.helpad.service.AllergeneService;
import fr.helpad.service.PlatService;

@Controller
public class PlatController {

	@Autowired
	PlatService platService;
	@Autowired
	AllergeneService allergeneService;
	
	@GetMapping("/plat")
	public String plat(Model model, Plat plat) {
		List<Plat> listPlats = platService.listerTout();
		List<Allergene> listAllergenes = allergeneService.listerTout();
		model.addAttribute("listPlats", listPlats);
		model.addAttribute("listAllergenes", listAllergenes);
		return "frontoffice/plats";
	}
	
	@GetMapping("/formPlats")
	public String formPlats(Model model, Plat plat, Allergene allergene) {
		model.addAttribute("plat", new Plat());
		List<Allergene> allergenes = allergeneService.listerTout();
		model.addAttribute("allergenes", allergenes);
		return "frontoffice/formPlats";
	}
	
	@PostMapping("/savePlat")
	public String savePlat(@ModelAttribute("plat") @Valid Plat plat, BindingResult errors,
			ModelAndView mv) {
//		List<Allergene> listAllergenes = new ArrayList<Allergene>();
//		List<Plat> listPlats = new ArrayList<Plat>();
//		listAllergenes.add(allergene);
//		plat.setAllergenes(listAllergenes);
//		listPlats.add(plat);
//		allergene.setPlats(listPlats);
		platService.sauvegarder(plat);
//		model.addAttribute("allergene", allergene);
		return "redirect:/plat";
	}
	
	@GetMapping("/supprimer")
	public String supprimer(Long idPlat) {
		platService.supprimer(idPlat);
		return "redirect:/plat";
	}
}
