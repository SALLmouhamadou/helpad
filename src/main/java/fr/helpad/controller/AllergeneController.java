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
import fr.helpad.service.AllergeneService;

@Controller
@RequestMapping("/allergenes")
public class AllergeneController {

    @Autowired
    private AllergeneService allergeneService;

    @GetMapping("")
    public String showAllergeneList(Model model) {
        List<Allergene> allergeneList = allergeneService.getAllAllergenes();
        model.addAttribute("allergeneList", allergeneList);
        return "allergenes/list";
    }

    @GetMapping("/add")
    public String showAddAllergeneForm(Model model) {
        model.addAttribute("allergene", new Allergene());
        return "allergenes/add";
    }

    @PostMapping("/add")
    public String addAllergene(@ModelAttribute("allergene") Allergene allergene, BindingResult result) {
        if (result.hasErrors()) {
            return "allergenes/add";
        }

        allergeneService.saveAllergene(allergene);
        return "redirect:/allergenes";
    }
    
    // autres méthodes pour éditer et supprimer les allergènes
}
