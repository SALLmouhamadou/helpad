package fr.helpad.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Candidat;
import fr.helpad.entity.Role;
import fr.helpad.service.PersonneService;
import fr.helpad.service.RoleServiceImpl;

@Controller
public class AuthController {
	
	@Autowired
	RoleServiceImpl roleServiceImpl;
	@Autowired 
	PersonneService personneService;
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("personne", new Candidat());
		model.addAttribute("title", "Connexion");		
		return "frontoffice/connexion";
	}
	
	@GetMapping("/inscription")
	public String showSignForm(Model model) {
		model.addAttribute("personne", new Candidat());
		model.addAttribute("title", "Inscription");		
		return "frontoffice/inscription";
	}
	@PostMapping("/user/add")
    public ModelAndView traiterForm(@ModelAttribute("personne") Candidat personne , BindingResult errors,
                    ModelAndView mv) {
            
            if (errors.hasErrors()) {
                    List<Role> roles = roleServiceImpl.getRoles();
                    mv.addObject("roles", roles);
                    mv.addObject("personne", personne);
                    mv.addObject("titrePage", "Ajouter un utilisateur");
                    mv.setViewName("frontoffice/inscription");
            } else {
            	try {
//            		personne.setDateNaissance(LocalDate.of(0, 0, 0));
            		personne.setNumeroSecuriteSocial("");
            		personneService.save(personne);
                    mv.setViewName("redirect:/login");
            	}catch(Exception e){
            		List<Role> roles = roleServiceImpl.getRoles();
                    mv.addObject("roles", roles);
                    mv.addObject("personne", personne);
                    mv.addObject("error", e.getMessage());
                    mv.addObject("alertClass", "alert alert-danger alert-dismissible fade show");
                    mv.setViewName("frontoffice/inscription");
            	}
                    
            }
            return mv;
    }
}
