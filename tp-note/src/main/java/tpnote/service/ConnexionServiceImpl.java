package tpnote.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import tpnote.dto.UtilisateurDTO;
import tpnote.dto.UtilisateurDTOFactory;
import tpnote.model.Utilisateur;
import tpnote.repository.UtilisateurRepository;

@Service
@Transactional
@SessionScope
public class ConnexionServiceImpl implements ConnexionService {
    /**
     * L'utilisateur actuellement connecté. Comme on est stateful, on préfère se
     * protéger en passant par des classes thread-safe.
     */
    private AtomicReference<UtilisateurDTO> utilisateurActuel = new AtomicReference<>();

    @Autowired
    UtilisateurRepository repository;

    /**
     * Essaie de connecter un utilisateur. échoue ssi l'utilisateur n'existe pas.
     * 
     * @param login
     * @return l'utilisateur connecté, ou null si échec.
     */
    public UtilisateurDTO connecter(String login) {
        Optional<Utilisateur> optionalUser = repository.findById(login);
        if (optionalUser.isPresent()) {
            UtilisateurDTO dto = UtilisateurDTOFactory.buildUtilisateurDTO(optionalUser.get());
            utilisateurActuel.set(dto);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Retourne l'utilisateur actuellement connecté, ou null si aucun ne l'est.
     * 
     * @return
     */
    public UtilisateurDTO getUtilisateur() {
        return utilisateurActuel.get();
    }

    /**
     * Retourne vrai si un utilisateur est connecté.
     */
    public boolean estConnecte() {
        return utilisateurActuel.get() != null;
    }

    @Override
    public boolean estAdmin() {
        return utilisateurActuel.get() != null 
            && utilisateurActuel.get().isAdministrateur();
    }

    public void deconnecter() {
        utilisateurActuel.set(null);
    }
}