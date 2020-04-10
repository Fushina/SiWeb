package tpnote.service;


import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpnote.model.Utilisateur;
import tpnote.repository.UtilisateurRepository;

/**
 * Devrait être un vrai service...
 * Ici, ne sert qu'à initialiser la liste des utilisateur.
 */
@Service
@Transactional
public class UtilisateurServiceImpl {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Crée trois utilisateurs, "admin" (qui est administrateur), "tech1" et "tech2".
     */
    @PostConstruct
    public void initDB() {
        creer(new Utilisateur("admin", true));
        creer(new Utilisateur("tech1", false));
        creer(new Utilisateur("tech2", false));
    }

    public void creer(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }
}