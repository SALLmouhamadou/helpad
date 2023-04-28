package fr.helpad.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import fr.helpad.entity.Personne;
import fr.helpad.entity.Status;
import fr.helpad.service.CandidatService;
import fr.helpad.service.CandidatureServiceImpl;
import fr.helpad.service.PersonneService;
import fr.helpad.service.StorageService;

@Controller

public class AdmissionController {
	@Autowired
	PersonneService personneService;
	@Autowired
	CandidatService candidatService;
	@Autowired
	StorageService storageService;
	@Autowired
	CandidatureServiceImpl candidatureServiceImpl;

	@GetMapping("/getAdmission")
	public ModelAndView showAdmissionForm(ModelAndView mav, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails !=null) {
		Optional<Candidat> user = candidatService.findByUsername(userDetails.getUsername());
		mav.addObject("user", user.get());
		mav.setViewName("frontoffice/admission");
		}
		else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}

	@GetMapping("/dashboard/{id}")
	public ModelAndView showDashbord(@PathVariable("id") Long id, ModelAndView mav) {
		List<Candidature> candidaturesById = candidatureServiceImpl.getCandidaturesById(id);
		Candidat candidat = candidatService.get(id);
		mav.addObject("candidatures", candidaturesById);
		mav.addObject("candidat", candidat);
		mav.addObject("title", "Espace usager");
		mav.setViewName("frontoffice/espacepersonnel");
		return mav;
	}

	@GetMapping("/dashboard/candidature/{id}")
	public ModelAndView getAllCandiduturesById(@PathVariable("id") Long id, ModelAndView mav) {
		List<Candidature> candidatures = candidatureServiceImpl.getCandidaturesById(id);
		Candidat candidat = candidatService.get(id);
		mav.addObject("candidatures", candidatures);
		mav.addObject("candidat", candidat);
		mav.addObject("title", "Mes candidatures");
		mav.setViewName("frontoffice/candidature");
		return mav;
	}

	@GetMapping("/confirmation")
	public String getConfirmationAdmission() {
		return "frontoffice/confirmationAdmission";
	}

	@GetMapping("/justificatifAFournir")
	public String ShowJustificatif() {
		return "frontoffice/justificatif";
	}

	@PostMapping("/sendAdmission")
	public ModelAndView saveCandidature(ModelAndView mav, @ModelAttribute Candidat candidat,
			@ModelAttribute Candidature candidature, @ModelAttribute Adresse adresse, @ModelAttribute Status status,
			HttpServletRequest request, HttpServletResponse response, 
			//@RequestParam("file") MultipartFile[] file,
			BindingResult errors, @AuthenticationPrincipal UserDetails userDetails) {
		if (errors.hasErrors()) {
			mav.setViewName("redirect:/getAdmission");
		}
		//try {
			Optional<Candidat> user = candidatService.findByUsername(userDetails.getUsername());
			Candidat candidat1=  user.get();
			candidat.setPassword(candidat1.getPassword());
			String revenu = request.getParameter("revenu");
			double revenuAnnuelle = Double.parseDouble(revenu);
			candidat.setRevenu(revenuAnnuelle);
			candidat.setAdresse(adresse);
			candidature.setStatus(status);
			candidature.setCandidat(candidat);
//			candidature.setFileName1(storageService.store(file));
//			candidature.setFileName2(storageService.store(file));
//			candidature.setFileName3(storageService.store(file));
//			candidature.setFileName4(storageService.store(file));
//			candidature.setFileName5(storageService.store(file));
//			candidature.setFileName6(storageService.store(file));
//			candidature.setFileName7(storageService.store(file));
//			candidature.setFileName8(storageService.store(file));
//			candidature.setFileName9(storageService.store(file));
			List<Candidature> candidatures = new ArrayList<Candidature>();
			candidatures.add(candidature);
			candidat.setMesCandidatures(candidatures);
			candidatService.sauveCandidat(candidat);
			mav.setViewName("redirect:/confirmation");
			return mav;

//		} catch (Exception e) {
////			mav.addObject("candidat", candidat);
////			mav.addObject("error", "formulaire invalide merci de verifier votre saisi");
////			mav.setViewName("frontoffice/admission");
//		}

		//return mav;
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
