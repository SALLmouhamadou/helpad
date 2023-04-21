package fr.helpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.helpad.entity.Repas;
import fr.helpad.service.RepasService;

@Controller
public class RepasController {
	
	@Autowired
	RepasService repasService;
	
	@GetMapping("/repas")
	public String repas(Model model, Repas repas) {
		List<Repas> listRepas = repasService.listerTout();
		model.addAttribute("listRepas", listRepas);
		return "frontoffice/repas";
	}
	

}
