package tpnote.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tpnote.service.ConnexionService;


@Controller
public class Accueil {
    @Autowired
    ConnexionService connexionService;

    @GetMapping("/")
    public String accueil(Model model) {
        model.addAttribute("utilisateur", connexionService.getUtilisateur());
        return "index";
    }
    
}