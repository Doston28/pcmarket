package uz.pdp.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double incomePrice;

    private double salePrice;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @OneToOne
    private Attachment photo;

    private int code;

    private boolean active;

    private int amount;
}
