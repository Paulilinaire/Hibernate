package model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
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
