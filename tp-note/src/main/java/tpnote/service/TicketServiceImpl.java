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

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public TicketDTO creerTicket(String createurLogin, String description) {
        if(createurLogin==null)
        {
            throw  new TicketException();
        }
        Utilisateur utilisateur =utilisateurRepository.findByLogin(createurLogin);

        Ticket t= new Ticket(description,LocalDate.now(),utilisateur);
        ticketRepository.save(t);
        return new TicketDTO(t.getId(),t.getDescription(),t.getCreationDate(), utilisateur.getLogin(),null);
    }
    private static TicketDTO buildDTO(Ticket a) {

        return new TicketDTO(a.getId(), a.getDescription(), a.getCreationDate(), a.getCreateur().getLogin(), a.getResponsable().getLogin());
    }

    @Override
    public List<TicketDTO> listeTickets() {
        List<Ticket> mesTickets=ticketRepository.findAll();
        List<TicketDTO>mesTicketsDTO= new ArrayList<>();
        for(Ticket t:mesTickets)
        {
            mesTicketsDTO.add(new TicketDTO(t.getId(),t.getDescription(),t.getCreationDate(),t.getCreateur().getLogin(),null));
        }
        return mesTicketsDTO;
    }

    @Override
    public TicketDTO trouverTicket(Long id) {
        Ticket t=ticketRepository.findById(id).get();
        Utilisateur u=t.getResponsable();
        String s=null;
        if(t.getResponsable()!=null)
        {
            s=t.getResponsable().getLogin();
        }
        TicketDTO tdto=new TicketDTO(t.getId(),t.getDescription(),t.getCreationDate(),t.getCreateur().getLogin(),s);
        return tdto;
    }

    @Override
    public List<String> listeUtilisateurs() {
        List<Utilisateur> listUtilisateur=utilisateurRepository.findAll();
        List<String> listString= new ArrayList<>();
        for(Utilisateur u:listUtilisateur)
        {
            listString.add(u.getLogin());
        }
        return listString;
    }

  @Override
    public void assigner(Long ticketId, String responsable) {
        Ticket t = ticketRepository.findById(ticketId).get();
        Utilisateur res= utilisateurRepository.findByLogin(responsable);
        t.setResponsable(res);
        if(res==null || t==null)
        {
            throw  new TicketException();
        }

        ticketRepository.save(t);
        
    }
}