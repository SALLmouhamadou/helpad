package fr.helpad.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;
import fr.helpad.service.CandidatService;
import fr.helpad.service.CandidatureServiceImpl;
import fr.helpad.service.StorageService;

@Controller

public class AdmissionController {
	@Autowired
	CandidatService candidatService;
	@Autowired
	StorageService storageService;
	@Autowired
	CandidatureServiceImpl candidatureServiceImpl;

	@GetMapping("/getAdmission")
	public String getAdmission() {
		return "frontoffice/admission";
	}

	@GetMapping("/mesCandidatures/{id}")
	public ModelAndView getAllCandiduturesById(@PathVariable("id") Long id, ModelAndView mav) {
		List<Candidature> candidaturesById = candidatureServiceImpl.getCandidaturesById(id);
		mav.addObject("candidatures", candidaturesById);
		mav.setViewName("frontoffice/mesCandidatures");
		return mav;
	}

	@GetMapping("/confirmation")
	public String getConfirmationAdmission() {
		return "frontoffice/confirmationAdmission";
	}

	@PostMapping("/sendAdmission")
	public ModelAndView saveCandidature(ModelAndView mav,@ModelAttribute Candidat candidat, @ModelAttribute Candidature candidature,
			@ModelAttribute Adresse adresse, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		
		try{
		String revenu = request.getParameter("revenu");
		double revenuAnnuelle = Double.parseDouble(revenu);
		candidat.setRevenu(revenuAnnuelle);
		candidat.setAdresse(adresse);
		candidature.setFileName(storageService.store(file));
		List<Candidature> candidatures = new ArrayList<Candidature>();
		candidatures.add(candidature);
		candidat.setMesCandidatures(candidatures);
		candidatService.sauveCandidat(candidat);
		mav.setViewName("redirect:/confirmation");
		return mav;
		
		}catch(Exception e) {
			mav.addObject("candidat", candidat);
			mav.addObject("error", "formulaire invalide merci de verifier votre saisi");
			mav.setViewName("frontoffice/admission");
		}
		
		return mav;
	}

	@GetMapping("/consulter/{id}")
	public ModelAndView getDetailCandidature(@PathVariable("id") Long id, ModelAndView mav) {
		Candidat candidat = candidatService.get(id);
		mav.addObject("candidat", candidat);
		mav.addObject("title", "Recapitilatif");
		mav.setViewName("frontoffice/recapitilatif");
		return mav;
	}
}
