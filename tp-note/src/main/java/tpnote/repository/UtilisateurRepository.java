package tpnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpnote.model.Utilisateur;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {
    public Utilisateur  findByLogin(String login);
}