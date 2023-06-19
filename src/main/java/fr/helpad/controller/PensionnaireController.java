package fr.helpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Etage;
import fr.helpad.entity.Pensionnaire;
import fr.helpad.service.ChambreService;
import fr.helpad.service.EtageService;
import fr.helpad.service.PensionnaireService;

@Controller
public class PensionnaireController {
	
	@Autowired
	PensionnaireService pensionnaireService;
	@Autowired
	EtageService etageService;
	
	@GetMapping("/add/pensionnaire")
	public ModelAndView showPensionnaire(ModelAndView mav) {
		List<Etage> etages = etageService.listerTout();
 		mav.addObject("pensionnaire", new Pensionnaire());
		mav.addObject("etages", etages );
		mav.addObject("title", "Enregistrer un pensionnaire");
		mav.setViewName("backoffice/pensionnaire");
		return mav;
	}
}
