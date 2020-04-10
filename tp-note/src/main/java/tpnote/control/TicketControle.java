package tpnote.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tpnote.repository.TicketRepository;
import tpnote.service.ConnexionService;
import tpnote.service.TicketService;
import tpnote.service.TicketServiceImpl;


/**
 * Ce contrôleur vous est fourni comme squelette possible pour votre travail.
 * Vous pouvez l'utiliser, ou le supprimer pour créer votre propre code... à vous de choisir.
 */
@Controller
public class TicketControle {
    @Autowired
    TicketService tsi;
    @Autowired
    ConnexionService connexionService;
   
    @GetMapping("/ticket/creer")
    public String creerTicketForm() {
        if(connexionService.estConnecte()){
        return "creerTicket";
        }
        else
        {
            return "redirect:/";
        }
    }

    @PostMapping("/ticket/creer")
    public String postTicketForm(String description) {
        tsi.creerTicket(connexionService.getUtilisateur().getLogin(),description);
        return "redirect:/";
    }

    @GetMapping("/ticket/liste")
    public String listeTickets(Model model) {
        if(connexionService.estAdmin()) {
            model.addAttribute("listeTicket", tsi.listeTickets());
            return "liste";
        }
        else
        {
            return "redirect:/";
        }
    }

    @GetMapping("/ticket/gerer/{id}")
    public String getGererTicket(@PathVariable("id") Long id, Model model) {
        return null;        
    }

    @PostMapping("/ticket/assigner")
    public String assigner(Long id, String responsable, Model model) {
        return null;
    }

}