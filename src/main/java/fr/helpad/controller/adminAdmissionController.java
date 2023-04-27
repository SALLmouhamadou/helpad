package fr.helpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Candidature;
import fr.helpad.service.CandidatService;
import fr.helpad.service.CandidatureServiceImpl;

@Controller
@RequestMapping("/admin")
public class adminAdmissionController {
	@Autowired
	CandidatService candidatService;
	@Autowired
	CandidatureServiceImpl candidatureServiceImpl;
	
	@GetMapping("/getAllCandidatures")
	public ModelAndView showAdmissionCandidature(ModelAndView mav) {
		//List<Candidat> candidats = candidatService.findAll();
		//System.out.println(candidatureServiceImpl.FindAllCandidatures());
		List<Candidature> candidatures = candidatureServiceImpl.FindAllCandidatures();
		//mav.addObject("candidat", candidats);
		mav.addObject("candidature", candidatures);
		mav.addObject("title", "Gérer les candidatures");
		mav.setViewName("backoffice/admissioncandidat");
		return mav;
	}
}
