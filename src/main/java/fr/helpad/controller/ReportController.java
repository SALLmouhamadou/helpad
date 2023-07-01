package fr.helpad.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {
	@PostMapping("/report")
	public void saveStock(final HttpServletRequest request) {
		System.out.println("Entrée dans un rapport : ");
		System.out.println(request.toString());
	}
}
