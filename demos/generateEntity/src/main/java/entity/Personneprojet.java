package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "personneprojet")
public class Personneprojet {
    @EmbeddedId
    private PersonneprojetId id;

    @MapsId("personneId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personne_id", nullable = false)
    private Personne personne;

}