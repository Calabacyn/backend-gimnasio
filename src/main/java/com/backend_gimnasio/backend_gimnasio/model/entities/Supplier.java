package com.backend_gimnasio.backend_gimnasio.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a supplier.
 */
@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 500)
    private String description;
}
