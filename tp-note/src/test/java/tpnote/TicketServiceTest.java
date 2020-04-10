package tpnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tpnote.dto.TicketDTO;
import tpnote.service.TicketException;
import tpnote.service.TicketServiceImpl;

@SpringBootTest
@Transactional
public class TicketServiceTest {

    @Autowired
    TicketServiceImpl service;

    @Test
    public void testCreerTicket() {
        TicketDTO res = service.creerTicket("tech1", "description");
        assertEquals("tech1", res.getCreateur());
        assertEquals("description", res.getDescription());
        assertNull(res.getResponsable());
        TicketDTO res1 = service.trouverTicket(res.getId());
        assertEquals(res.getId(), res1.getId());
        assertEquals(res.getDescription(), res1.getDescription());
        assertEquals(res.getCreationDate(), res1.getCreationDate());
        assertEquals(res.getCreateur(), res1.getCreateur());
        assertNull(res1.getResponsable());
    }

    @Test
    public void pasDeCreateur() {
        assertThrows(TicketException.class, () -> {
            service.creerTicket(null, "demo");
        });
    }

    @Test
    public void testListeTickets() {
        TicketDTO res1 = service.creerTicket("tech1", "description");
        TicketDTO res2 = service.creerTicket("tech1", "autre");
        assertEquals(2, service.listeTickets().size());

    }

    @Test
    public void testAssignation() {
        TicketDTO dto = service.creerTicket("tech1", "un ticket");
        service.assigner(dto.getId(), "tech2");
        TicketDTO dto1 = service.trouverTicket(dto.getId());
        assertEquals("tech1", dto1.getCreateur());
        assertEquals("un ticket", dto1.getDescription());
        assertEquals("tech2", dto1.getResponsable());
    }


    @Test
    public void testAssignationNonTrouvee() {
        assertThrows(TicketException.class, () -> {
            TicketDTO dto = service.creerTicket("tech1", "un ticket");
            service.assigner(dto.getId(), "tech3");
        });
    }

    @Test
    public void testListeUtilisateurs() {
        List<String> liste = service.listeUtilisateurs();
        assertEquals(3, liste.size());
    }

}