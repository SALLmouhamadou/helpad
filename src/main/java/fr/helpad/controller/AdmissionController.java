package fr.helpad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdmissionController {
	@GetMapping("/admission")
	public String getAdmission() {
		return "frontoffice/admission";
	}
}
