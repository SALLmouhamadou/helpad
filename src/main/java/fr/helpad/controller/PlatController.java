package fr.helpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.helpad.entity.Plat;
import fr.helpad.service.PlatService;

@Controller
public class PlatController {

	@Autowired
	PlatService platService;
	
	@GetMapping("/plat")
	public String plat(Model model, Plat plat) {
		List<Plat> listPlats = platService.listerTout();
		model.addAttribute("listPlats", listPlats);
		return "frontoffice/plats";
	}
}
