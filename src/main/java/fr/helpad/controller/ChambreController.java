package fr.helpad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Chambre;
import fr.helpad.service.ChambreService;

@Controller
public class ChambreController {
	@Autowired
	ChambreService chambreService;
	@GetMapping("/admin/addChambre")
	public ModelAndView showChambreForm(ModelAndView mav) {
		mav.addObject("chambre", new Chambre());
		mav.addObject("title", "Ajouter des Chambre");
		mav.setViewName("backoffice/Add-Chambre");
		return mav;
	}
}
