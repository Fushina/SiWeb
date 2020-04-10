package tpnote.service;

import tpnote.dto.UtilisateurDTO;

/**
 * Le service qui gère la connexion.
 * 
 * <p>
 * Noter que le "scope" de ce service est la session.
 * 
 * Dans une "vraie" application Spring, on utilisera plutôt, soit Spring
 * security, soit des fonctionnalités liées au Web comme l'injection de
 * "Principal".
 */
public interface ConnexionService {
    /**
     * Essaie de connecter un utilisateur. échoue ssi l'utilisateur n'existe pas.
     * 
     * @param login
     * @return l'utilisateur connecté, ou null si échec.
     */
    UtilisateurDTO connecter(String login);

    /**
     * Retourne l'utilisateur actuellement connecté, ou null si aucun ne l'est.
     * 
     * @return
     */
    UtilisateurDTO getUtilisateur();

    /**
     * Retourne vrai si un utilisateur est connecté.
     */
    boolean estConnecte();

      /**
     * Retourne vrai si un administrateur est connecté.
     */
    boolean estAdmin();

    /**
     * Déconnecte l'utilisateur courant.
     */
    void deconnecter();

}