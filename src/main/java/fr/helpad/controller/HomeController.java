package fr.helpad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "./frontoffice/index";
	}

	@GetMapping("/admin")
	public ModelAndView showDashboard(ModelAndView mav) {
		mav.addObject("title", "Dashboard");
		mav.setViewName("backoffice/dashboard");
		return mav;
	}
	@GetMapping("/about")
	public String showAbout() {
		return "frontoffice/about";
	}
	
	@GetMapping("/contact")
	public String showContactPage() {
		return "frontoffice/contact";
	}
}
