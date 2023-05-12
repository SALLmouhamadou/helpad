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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
		if (userDetails != null) {
			Optional<Candidat> user = candidatService.findByUsername(userDetails.getUsername());
			mav.addObject("user", user.get());
			mav.setViewName("frontoffice/admission");
		} else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView showDashbord(ModelAndView mav,@AuthenticationPrincipal UserDetails userDetails ) {
		if (userDetails !=null) {		
			Optional<Candidat> candidat = candidatService.findByUsername(userDetails.getUsername());
			mav.addObject("candidat", candidat.get());
			mav.addObject("title", "Espace usager");
			mav.setViewName("frontoffice/espacepersonnel");
		}else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}

	@GetMapping("/dashboard/candidature")
	public ModelAndView getAllCandiduturesById( ModelAndView mav, @AuthenticationPrincipal UserDetails userDetails) {
		if(userDetails !=null) {
			Optional<Candidat> candidat= candidatService.findByUsername(userDetails.getUsername());
			mav.addObject("candidat", candidat.get());
		//mav.addObject("candidature", candidatures.get());
		mav.addObject("title", "Mes candidatures");
		mav.setViewName("frontoffice/candidature");
		}
		else {
		mav.setViewName("redirect:/login");
		}
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
			@RequestParam("file1") MultipartFile[] file1,
			@RequestParam("file2") MultipartFile[] file2,
			@RequestParam("file3") MultipartFile[] file3,
			@RequestParam("file4") MultipartFile[] file4,
			@RequestParam("file5") MultipartFile[] file5,
			@RequestParam("file6") MultipartFile[] file6,
			@RequestParam("file7") MultipartFile[] file7,
			@RequestParam("file8") MultipartFile[] file8,
			@RequestParam("file9") MultipartFile[] file9,
			
			BindingResult errors, @AuthenticationPrincipal UserDetails userDetails) {
		if (errors.hasErrors()) {
			mav.setViewName("redirect:/getAdmission");
		}
		try {
			Optional<Candidat> user = candidatService.findByUsername(userDetails.getUsername());
			Candidat candidat1 = user.get();
			candidat.setPassword(candidat1.getPassword());
			String revenu = request.getParameter("revenu");
			double revenuAnnuelle = Double.parseDouble(revenu);
			candidat.setRevenu(revenuAnnuelle);
			candidat.setAdresse(adresse);
			candidature.setStatus(status);
			candidature.setCandidat(candidat);
			candidature.setFileName1(storageService.store(file1));
			candidature.setFileName2(storageService.store(file2));
			candidature.setFileName3(storageService.store(file3));
			candidature.setFileName4(storageService.store(file4));
			candidature.setFileName5(storageService.store(file5));
			candidature.setFileName6(storageService.store(file6));
			candidature.setFileName7(storageService.store(file7));
			candidature.setFileName8(storageService.store(file8));
			candidature.setFileName9(storageService.store(file9));
			List<Candidature> candidatures = new ArrayList<Candidature>();
			candidatures.add(candidature);
			candidat.setMesCandidatures(candidatures);
			candidatService.sauveCandidat(candidat);
			mav.setViewName("redirect:/confirmation");
			return mav;

		} catch (Exception e) {
			mav.addObject("candidat", candidat);
			mav.addObject("error", "formulaire invalide merci de verifier votre saisi");
			mav.setViewName("frontoffice/admission");
		}

		return mav;
	}

	@SuppressWarnings("unused")
	@GetMapping("/consulter")
	public ModelAndView getDetailCandidature( ModelAndView mav,@AuthenticationPrincipal UserDetails userDetails) {
		if(userDetails !=null) {
		Optional<Candidat> candidatD= candidatService.findByUsername(userDetails.getUsername());
		mav.addObject("candidat", candidatD.get());
//		mav.addObject("files", storageService.loadAll()).map(
//				path -> MvcUriComponentsBuilder.fromMethodName(AdmissionController.class,
//						"serveFile", path.getFileName().toString()).build().toUri().toString()).collect(Collectors.toList()));
		mav.addObject("title", "Recapitilatif");
		mav.setViewName("frontoffice/recapitilatif");
		}else {
			mav.setViewName("redirect:/login");
		}
		return mav;

	}
	
	@GetMapping("/admin/getAllCandidatures")
	public ModelAndView showAdmissionCandidature(ModelAndView mav) {
		List<Candidat> candidats = candidatService.findAllCandidats();
		List<Candidature> candidatures = candidatureServiceImpl.findAllCandidatures();
		mav.addObject("candidats", candidatures);
		mav.addObject("title", "Gérer les candidatures");
		mav.setViewName("backoffice/admissioncandidat");
		return mav;
	}
}
