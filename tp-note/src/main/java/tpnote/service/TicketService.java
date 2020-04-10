package tpnote.service;

import java.util.List;

import tpnote.dto.TicketDTO;

public interface TicketService {

    /**
     * Crée dans la base de donnée un nouveau ticket.
     * 
     * Enregistre un nouveau ticket, à la date actuelle.
     * @param createurLogin le créateur du ticket. S'il est null, on lève une TicketException.
     * @param description
     * @throws TicketException si le créateur du ticket n'est pas indiqué
     */
	TicketDTO creerTicket(String createurLogin, String description);

    /**
     * Retourne la liste des tickets
     * @return
     */
	List<TicketDTO> listeTickets();

    /**
     * Retourne un ticket donné.
     * @param id
     * @return
     */
	TicketDTO trouverTicket(Long id);

    /**
     * Liste les logins de tous les utilisateurs disponibles.
     * @return
     */
	List<String> listeUtilisateurs();

    /**
     * Assigne le ticket ticketId au responsable listé.
     * @param ticketId
     * @param responsable le nom d'un utilisateur
     * @throws TicketException si aucun  de ce nom n'est trouvé.
     */
	void assigner(Long ticketId, String responsable);

}