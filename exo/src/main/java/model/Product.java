package model;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    private String brand;

    private LocalDate saleDate;

    private double price;

    private int storage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Product(String reference, String brand, LocalDate saleDate, double price, int storage) {
        this.reference = reference;
        this.brand = brand;
        this.saleDate = saleDate;
        this.price = price;
        this.storage = storage;
    }
}
