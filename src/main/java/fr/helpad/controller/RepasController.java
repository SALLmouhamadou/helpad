package fr.helpad.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.helpad.entity.Plat;
import fr.helpad.entity.Repas;
import fr.helpad.service.PlatService;
import fr.helpad.service.RepasService;

@Controller
public class RepasController {
	
	@Autowired
	RepasService repasService;
	@Autowired
	PlatService platService;
	
	@GetMapping("/repas")
	public String repas(Model model, Repas repas) {
		List<Repas> listRepass = repasService.listerTout();
		List<Plat> listPlats = platService.listerTout();
		model.addAttribute("listRepass", listRepass);
		model.addAttribute("listPlats", listPlats);
		model.addAttribute("dateRepas", repas.getDateRepas());
		return "frontoffice/repas";
	}
	
	@GetMapping("/formRepas")
	public String formRepas(Model model) {
		model.addAttribute("repas", new Repas());
		List<Plat> plats = platService.listerTout();
		System.out.println(plats.toString());
		model.addAttribute("plats", plats);
		model.addAttribute("dateRepas", LocalDate.now());
//		model.addAttribute("dateRepas", repas.getDateRepas());
		return "frontoffice/formRepas";
	}
	
	@PostMapping("/saveRepas")
	public String saveRepas(@ModelAttribute("repas") @Valid Repas repas, BindingResult errors,
			ModelAndView mv
//		@RequestParam("dateRepas") LocalDate dateRepas
			) {
		System.out.println(repas);
//		List<Allergene> listAllergenes = new ArrayList<Allergene>();
//		List<Plat> listPlats = new ArrayList<Plat>();
//		listAllergenes.add(allergene);
//		plat.setAllergenes(listAllergenes);
//		listPlats.add(plat);
//		allergene.setPlats(listPlats);
		System.out.println("Ma date : ");
//		System.out.println(dateRepas);
		System.out.println(repas.getDateRepas());
		repasService.sauvegarder(repas);
//		model.addAttribute("allergene", allergene);
		return "redirect:/repas";
	}
	
	@GetMapping("/rechercherepas")
	private String recherche(Model model, @RequestParam(defaultValue="") String repasDate) {
		LocalDate dateRepas;
		if(repasDate.isEmpty()) {
			dateRepas = LocalDate.now();
		}else {
			try{
				dateRepas = LocalDate.parse(repasDate);
				System.out.println(dateRepas);
			}catch(Exception e) {
				throw e;
			}
		}
		
		List<Repas> repas = repasService.findByDateRepas(dateRepas);
		System.out.println(repas);
		model.addAttribute("repasDate", repas);
		model.addAttribute("dateRepas", dateRepas);		
		return "frontoffice/rechercherepas";
	}
	
	
//	@PostMapping("/rechercherepas")
//	private String rechercher(Model model, 
//			@RequestParam(name = "dateRepas", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dr) {
//		List<Repas> repasDate = repasService.findByDateRepas(dr);
//		model.addAttribute("repasDate", repasDate);
//		model.addAttribute("dateRepas", dr);		
//		return "frontoffice/rechercherepas";
//	}

//    @Autowired
//    private PlatService platService;
//
//    @Autowired
//    private AllergeneService allergeneService;
//
//    @GetMapping("/list")
//    public String showRepasList(Model model) {
//        List<Repas> repasList = repasService.getAllRepas();
//        model.addAttribute("repasList", repasList);
//        return "frontoffice/listRepas";
//    }
//
//    @GetMapping("/addRepas")
//    public String showAddRepasForm(Model model) {
//        List<Plat> platList = platService.getAllPlats();
//        List<Allergene> allergeneList = allergeneService.getAllAllergenes();
//        model.addAttribute("repas", new Repas());
//        model.addAttribute("platList", platList);
//        model.addAttribute("allergeneList", allergeneList);
//        return "frontoffice/addRepas";
//    }
//
//    @PostMapping("/addRepas")
//    public String addRepas(@ModelAttribute("repas") Repas repas, BindingResult result, Model model) {
////        if (result.hasErrors()) {
////            List<Plat> platList = platService.getAllPlats();
////            List<Allergene> allergeneList = allergeneService.getAllAllergenes();
////            model.addAttribute("platList", platList);
////            model.addAttribute("allergeneList", allergeneList);
////            return "frontoffice/addRepas";
////        }
//
//        repasService.saveRepas(repas);
//        return "redirect:/repas/list";
//    }
    
//}
}
