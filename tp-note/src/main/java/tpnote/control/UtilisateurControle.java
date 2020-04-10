package tpnote.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import tpnote.service.ConnexionService;

@Controller
public class UtilisateurControle {

    @Autowired
    ConnexionService connexionService;

    
    /**
     * Connecte un utilisateur.
     * Note : pour cet exemple, nous n'utilisons pas de mot de passe.
     * La connexion fonctionne donc dès que l'utilisateur existe.
     * @param login
     */
    @PostMapping("/connecter")
    public String connecter(String login) {
        connexionService.connecter(login);
        return "redirect:/";
    }

    @PostMapping("/deconnecter")
    public String deconnecter() {
        connexionService.deconnecter();
        return "redirect:/";
    }

}