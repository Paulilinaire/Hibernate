package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PersonneprojetId implements Serializable {
    private static final long serialVersionUID = 2015558892022126169L;
    @Column(name = "personne_id", nullable = false)
    private Integer personneId;

    @Column(name = "projet_id", nullable = false)
    private Integer projetId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersonneprojetId entity = (PersonneprojetId) o;
        return Objects.equals(this.personneId, entity.personneId) &&
                Objects.equals(this.projetId, entity.projetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personneId, projetId);
    }

}