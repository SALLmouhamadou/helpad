package fr.helpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;
import fr.helpad.entity.Repas;
import fr.helpad.service.AllergeneService;
import fr.helpad.service.PlatService;
import fr.helpad.service.RepasService;

@Controller
@RequestMapping("/repas")
public class RepasController {

    @Autowired
    private RepasService repasService;

    @Autowired
    private PlatService platService;

    @Autowired
    private AllergeneService allergeneService;

    @GetMapping("/list")
    public String showRepasList(Model model) {
        List<Repas> repasList = repasService.getAllRepas();
        model.addAttribute("repasList", repasList);
        return "frontoffice/listRepas";
    }

    @GetMapping("/addRepas")
    public String showAddRepasForm(Model model) {
        List<Plat> platList = platService.getAllPlats();
        List<Allergene> allergeneList = allergeneService.getAllAllergenes();
        model.addAttribute("repas", new Repas());
        model.addAttribute("platList", platList);
        model.addAttribute("allergeneList", allergeneList);
        return "frontoffice/addRepas";
    }

    @PostMapping("/addRepas")
    public String addRepas(@ModelAttribute("repas") Repas repas, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            List<Plat> platList = platService.getAllPlats();
//            List<Allergene> allergeneList = allergeneService.getAllAllergenes();
//            model.addAttribute("platList", platList);
//            model.addAttribute("allergeneList", allergeneList);
//            return "frontoffice/addRepas";
//        }

        repasService.saveRepas(repas);
        return "redirect:/repas/list";
    }
    
    // autres méthodes pour éditer et supprimer les repas
}