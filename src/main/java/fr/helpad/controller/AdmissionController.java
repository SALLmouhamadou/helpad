package fr.helpad.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			@ModelAttribute Adresse adresse, 
			HttpServletRequest request, 
			HttpServletResponse response
			//@RequestParam("file") MultipartFile file
			)throws IOException{
		
		String revenu = request.getParameter("revenu");
		double revenuAnnuelle = Double.parseDouble(revenu);
		candidat.setRevenu(revenuAnnuelle);
		candidat.setAdresse(adresse);
		List<Candidature> candidatures = new ArrayList<Candidature>();
		candidatures.add(candidature);
		candidat.setMesCandidatures(candidatures);
//		if (!file.isEmpty()) {
//	        byte[] bytes = file.getBytes();
//	     // Générer un nom de fichier unique
//	        String originalFilename = file.getOriginalFilename();
//	        String filename = UUID.randomUUID().toString() + "-" + originalFilename;
//	     // Enregistrer l'image dans le répertoire de stockage des images
//	        Path filePath = Paths.get("/home/gnahiet/images/").resolve(filename);
//	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//		}
		candidatService.sauveCandidat(candidat);
		
		return "redirect:confirmation";
	}
}
