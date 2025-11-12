package com.backend_gimnasio.backend_gimnasio.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;


    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 255)
    private String description;


    @Column(name = "sale_price", nullable = false)
    private Double salePrice;

    @Column(nullable = false)
    private Integer stock;
}
