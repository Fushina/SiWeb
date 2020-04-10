package tpnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpnote.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {

}