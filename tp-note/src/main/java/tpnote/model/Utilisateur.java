package tpnote.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Un utilisateur.
 */
@Entity
public class Utilisateur {
    @Id
    String login;
    
    /**
     * Statut de cet utilisateur. Est-il administrateur ?
     */
    boolean administrateur;

    Utilisateur() {}


    public Utilisateur(String login, boolean administrateur) {
        this.login = login;
        this.administrateur = administrateur;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the administrateur
     */
    public boolean isAdministrateur() {
        return administrateur;
    }

    /**
     * @param administrateur the administrateur to set
     */
    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }
   
    @Override
    public String toString() {
        return "{" +
            " login='" + getLogin() + "'" +
            ", administrateur='" + isAdministrateur() + "'" +
            "}";
    }

}