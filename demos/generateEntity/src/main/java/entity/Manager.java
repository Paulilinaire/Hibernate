package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @Column(name = "personne_id", nullable = false)
    private Integer id;

    @Column(name = "niveau")
    private Integer niveau;

}