package tpnote.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String description;
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name="CREATEUR_ID", nullable=false)
    private Utilisateur createur;

    /**
     * Le technicien qui doit prendre en charge 
     * le ticket. Initialement à null. 
     * Ce sont les administrateurs qui désignent les responsables.
     */
    @ManyToOne
    @JoinColumn(name="RESPONSABLE_ID", nullable=true, updatable=true)
    private Utilisateur responsable;


    Ticket() {
    }

    public Ticket(String description, 
        LocalDate creationDate,
        Utilisateur createur) {
        this.description = description;
        this.creationDate = creationDate;
        this.createur = createur;
        this.responsable = null;
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

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Utilisateur getCreateur() {
        return this.createur;
    }

    /**
     * @return the responsable
     */
    public Utilisateur getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", createur='" + getCreateur() + "'" +
            "}";
    }

    
}