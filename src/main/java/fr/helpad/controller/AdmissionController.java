package fr.helpad.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;
import fr.helpad.service.CandidatService;

@Controller
public class AdmissionController {
	@Autowired
	CandidatService candidatService;

	@GetMapping("/admission")
	public String getAdmission() {
		return "frontoffice/admission";
	}

	@GetMapping("/mesCandidatures")
	public String getCandiduture() {
		return "frontoffice/mesCandidatures";
	}
	@GetMapping("/confirmation")
	public String getConfirmationAdmission() {
		return "frontoffice/confirmationAdmission";
	}

	@PostMapping("/sendAdmission")
	public String saveCandidature(@ModelAttribute Candidat candidat, @ModelAttribute Candidature candidature,
			@ModelAttribute Adresse adresse, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("entree");
		String revenu = request.getParameter("revenu");
		double revenuAnnuelle = Double.parseDouble(revenu);
		candidat.setRevenu(revenuAnnuelle);
		candidat.setAdresse(adresse);

		List<Candidature> candidatures = new ArrayList<Candidature>();
		candidatures.add(candidature);
		candidat.setMesCandidatures(candidatures);

		System.out.println("c'est bon");
		candidatService.sauveCandidat(candidat);
		System.out.println("envoyer");
		return "redirect:confirmation";
	}
}
