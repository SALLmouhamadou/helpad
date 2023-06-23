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
import fr.helpad.service.EmailService;
import fr.helpad.service.PersonneService;
import fr.helpad.service.StatusService;
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
	@Autowired
	StatusService statusService;
	@Autowired
	EmailService emailService;

	@GetMapping("/getAdmission")
	public ModelAndView showAdmissionForm(ModelAndView mav, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			Optional<Candidat> user = candidatService.findByUsername(userDetails.getUsername());
			mav.addObject("user", user);
			mav.setViewName("frontoffice/admission");
		} else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}

	@GetMapping("/dashboard")
	public ModelAndView showDashbord(ModelAndView mav, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			Optional<Candidat> candidat = candidatService.findByUsername(userDetails.getUsername());
			System.out.println(userDetails.getUsername());
			System.out.println(userDetails);
			mav.addObject("user", candidat.get());
			mav.addObject("title", "Espace usager");
			mav.setViewName("frontoffice/espacepersonnel");
		} else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}

	@GetMapping("/dashboard/candidature")
	public ModelAndView getAllCandiduturesById(ModelAndView mav, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			Optional<Candidat> candidat = candidatService.findByUsername(userDetails.getUsername());
			mav.addObject("candidat", candidat.get());
			mav.addObject("title", "Mes candidatures");
			mav.setViewName("frontoffice/candidature");
		} else {
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
			@ModelAttribute Candidature candidature, @ModelAttribute Adresse adresse, String libelle,
			HttpServletRequest request, HttpServletResponse response, @RequestParam("file1") MultipartFile[] file1,
			@RequestParam("file2") MultipartFile[] file2, @RequestParam("file3") MultipartFile[] file3,
			@RequestParam("file4") MultipartFile[] file4, @RequestParam("file5") MultipartFile[] file5,
			@RequestParam("file6") MultipartFile[] file6, @RequestParam("file7") MultipartFile[] file7,
			@RequestParam("file8") MultipartFile[] file8, @RequestParam("file9") MultipartFile[] file9,

			BindingResult errors, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
		if (errors.hasErrors()) {
			mav.setViewName("redirect:/getAdmission");
		}
		try {
			Optional<Candidat> user = candidatService.findByUsername(userDetails.getUsername());
			
			candidat.setPassword(user.get().getPassword());
			String revenu = request.getParameter("revenu");
			double revenuAnnuelle = Double.parseDouble(revenu);
			candidat.setIdPersonne(user.get().getIdPersonne());
			candidat.setRevenu(revenuAnnuelle);
			candidat.setAdresse(adresse);
			Status status = statusService.findStatusByLibelle(libelle);
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
			mav.addObject("error", "Formulaire invalide merci de verifier votre saisi");
			mav.setViewName("frontoffice/admission");
			
		}
		return mav;
	}
	
	@GetMapping("/consulter")
	public ModelAndView getDetailCandidature(ModelAndView mav, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			Optional<Candidat> candidatD = candidatService.findByUsername(userDetails.getUsername());
			mav.addObject("candidat", candidatD.get());
			mav.addObject("title", "Recapitilatif");
			mav.setViewName("frontoffice/recapitilatif");
		} else {
			mav.setViewName("redirect:/login");
		}
		return mav;

	}

	@GetMapping("/admin/getAllCandidatures")
	public ModelAndView showAdmissionCandidature(ModelAndView mav, String numeroRef) {
		if (numeroRef != null) {
			Candidature findCandidature = candidatureServiceImpl.findCandidature(numeroRef);
			mav.addObject("candidats", findCandidature);
			mav.setViewName("backoffice/admissioncandidat");
		} else {
			mav.addObject("candidats", candidatureServiceImpl.findAllCandidatures());
			mav.addObject("title", "Gérer les candidatures");
			mav.setViewName("backoffice/admissioncandidat");
		}
		return mav;
	}

	@GetMapping("admin/consulter/candidature/{id}")
	public ModelAndView getCandidatDetails(ModelAndView mav, @PathVariable("id") Long id) {
		Candidat candidat = candidatService.get(id);
		mav.addObject("candidat", candidat);
		mav.setViewName("backoffice/gestioncandidature");
		return mav;
	}

	// @RequestMapping(value="admin/update/candidature" , method =
	// RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
	// @ResponseBody @RequestBody CandidatureStatusDTO can
	// String libelle =can.getStatus();
	// Long id = can.getId();

	@PostMapping("/update/candidature")
	public ModelAndView updateCandidature(ModelAndView mav,  Long id,
			 String libelle) {
		Candidature candidature = candidatureServiceImpl.getCandidaturesById(id);
		Status status = statusService.findStatusByLibelle(libelle);
		if (candidature != null && status != null) {
			candidature.setStatus(status);
			candidatureServiceImpl.saveCandidature(candidature);
			mav.addObject("messageOK", "La candidature a été bien modifié avec success");
			mav.addObject("alertClass", "alert alert-sucess alert-dismissible fade show");
			mav.setViewName("redirect:/admin/getAllCandidatures");
		} else {
			mav.addObject("messageErreur", "Un erreur est survenue au moment de la modification");
			mav.addObject("alertClass", "alert alert-danger alert-dismissible fade show");
			mav.setViewName("redirect:/admin/getAllCandidatures");
		}
		return mav;
	}

	@GetMapping("/delete/candidature/{id}")
	public ModelAndView deleteCandidature(@PathVariable("id") Long id, ModelAndView mav) {
		Candidature candidature = candidatureServiceImpl.getCandidaturesById(id);
		if (candidature != null) {

			mav.addObject("messageOK", "La candidature a été bien supprimer avec success");
			mav.addObject("alertClass", "alert alert-sucess alert-dismissible fade show");
			mav.setViewName("redirect:/admin/getAllCandidatures");
		} else {
			mav.addObject("messageOK", "Une erreur est survenue au moment de la suppression");
			mav.addObject("alertClass", "alert alert-danger alert-dismissible fade show");
			mav.setViewName("redirect:/admin/getAllCandidatures");
		}
		return mav;
	}
}
