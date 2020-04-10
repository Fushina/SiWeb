package tpnote.dto;

import tpnote.model.Utilisateur;

public class UtilisateurDTOFactory {

	public static UtilisateurDTO buildUtilisateurDTO(Utilisateur utilisateur) {
		return new UtilisateurDTO(utilisateur.getLogin(), utilisateur.isAdministrateur());
	}

}