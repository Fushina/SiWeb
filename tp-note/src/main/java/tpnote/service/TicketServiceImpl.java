package tpnote.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpnote.dto.TicketDTO;
import tpnote.model.Ticket;
import tpnote.model.Utilisateur;
import tpnote.repository.TicketRepository;
import tpnote.repository.UtilisateurRepository;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
  
    @Override
    public TicketDTO creerTicket(String createurLogin, String description) {      
        return null;
    }

    @Override
    public List<TicketDTO> listeTickets() {
        return null;
    }

    @Override
    public TicketDTO trouverTicket(Long id) {
        return null;
    }

    @Override
    public List<String> listeUtilisateurs() {
        return null;
    }

    @Override
    public void assigner(Long ticketId, String responsable) {
        
    }
}