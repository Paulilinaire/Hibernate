package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @Column(name = "personne_id", nullable = false)
    private Integer id;

    @Column(name = "salaire", precision = 10, scale = 2)
    private BigDecimal salaire;

    @Column(name = "poste", length = 100)
    private String poste;

}