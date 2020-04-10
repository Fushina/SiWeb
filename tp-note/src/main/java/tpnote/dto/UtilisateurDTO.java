package tpnote.dto;

public class UtilisateurDTO {
    private String login;
    private boolean administrateur;

    public UtilisateurDTO(String login, boolean administrateur) {
        this.login = login;
        this.administrateur = administrateur;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdministrateur() {
        return this.administrateur;
    }

    public boolean getAdministrateur() {
        return this.administrateur;
    }

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