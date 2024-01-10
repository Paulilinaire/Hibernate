package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "comment_date")
    private LocalDate commentDate;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}