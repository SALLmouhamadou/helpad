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

import fr.helpad.entity.Plat;
import fr.helpad.service.PlatService;

@Controller
@RequestMapping("/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @GetMapping("")
    public String showPlatList(Model model) {
        List<Plat> platList = platService.getAllPlats();
        model.addAttribute("platList", platList);
        return "plats/listPlat";
    }

    @GetMapping("/addPlat")
    public String showAddPlatForm(Model model) {
        model.addAttribute("plat", new Plat());
        return "plats/addPlat";
    }

    @PostMapping("/addPlat")
    public String addPlat(@ModelAttribute("plat") Plat plat, BindingResult result) {
        if (result.hasErrors()) {
            return "plats/addPlat";
        }

        platService.savePlat(plat);
        return "redirect:/plats";
    }
    
    // autres méthodes pour éditer et supprimer les plats
	
}
