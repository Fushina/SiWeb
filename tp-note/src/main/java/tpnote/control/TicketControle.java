package tpnote.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Ce contrôleur vous est fourni comme squelette possible pour votre travail.
 * Vous pouvez l'utiliser, ou le supprimer pour créer votre propre code... à vous de choisir.
 */
@Controller
public class TicketControle {

   
    @GetMapping("/ticket/creer")
    public String creerTicketForm() {
        return null;
    }

    @PostMapping("/ticket/creer")
    public String postTicketForm(String description) {
        return null;
    }

    @GetMapping("/ticket/liste")
    public String listeTickets(Model model) {
        return null;
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