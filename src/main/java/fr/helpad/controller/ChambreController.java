package fr.helpad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Chambre;
import fr.helpad.entity.Etage;
import fr.helpad.service.ChambreService;
import fr.helpad.service.EtageService;

@Controller
public class ChambreController {
	@Autowired
	ChambreService chambreService;
	@Autowired
	EtageService etageService;

	@GetMapping("/admin/addChambre")
	public ModelAndView showChambreForm(ModelAndView mav) {
		mav.addObject("chambre", new Chambre());
		mav.addObject("etage", new Etage());
		mav.addObject("title", "Ajouter des Chambre");
		mav.setViewName("backoffice/Add-Chambre");
		return mav;
	}

	@PostMapping("/add/newChambre")
	public ModelAndView addNewChambre(ModelAndView mav, @ModelAttribute("chambre") Chambre chambre,
			@ModelAttribute("etage") Etage etage) {

		try {
			chambre.setEtage(etage);
			List<Chambre> chambres = new ArrayList<Chambre>();
			chambres.add(chambre);
			etage.setChambres(chambres);
			etageService.sauvegarder(etage);
			mav.addObject("message", "La chambre a bien été ajouter" );
			mav.addObject("alertClass", "alert alert-success alert-success fade show");
			mav.setViewName("backoffice/add-chambre");
		} catch (Exception e) {
			mav.addObject("message", "Merci de verifer votre saisi");
			mav.addObject("alertClass", "alert alert-danger alert-danger fade show");
			mav.setViewName("backoffice/add-chambre");
		}
		return mav;
	}
}
