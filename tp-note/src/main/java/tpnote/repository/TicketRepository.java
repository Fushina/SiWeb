package tpnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpnote.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

}