package tpnote.dto;

import java.time.LocalDate;

/**
 * TicketDTO
 */
public class TicketDTO {
    Long id;
    private String description;
    private LocalDate creationDate;
    private String createur;
    private String responsable;


    public TicketDTO() {
    }

    /**
     * Crée un DTO pour un ticket
     * @param id id du ticket
     * @param description court texte le décrivant
     * @param creationDate date de création
     * @param createur créateur du ticket
     * @param responsable technicien auquel le ticket est assigné (ou null si non assigné)
     */
    public TicketDTO(Long id, String description,
        LocalDate creationDate, String createur, String responsable) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.createur = createur;
        this.responsable = responsable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the createur
     */
    public String getCreateur() {
        return createur;
    }

    /**
     * @param createur the createur to set
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", createur='" + getCreateur() + "'" +
            ", responsable='" + getResponsable() + "'" +
            "}";
    }
    
}