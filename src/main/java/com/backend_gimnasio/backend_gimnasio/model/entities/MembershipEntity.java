package com.backend_gimnasio.backend_gimnasio.model.entities;

import com.backend_gimnasio.backend_gimnasio.enums.MembershipTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memberships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type", nullable = false)
    private MembershipTypeEnum membershipType;

    @Column(name = "duration_days", nullable = true)
    private Integer durationDays;

    @Column(length = 255)
    private String description;

    public Long getDurationMonths() {
        if (durationDays != null) {
            return durationDays / 30L;
        }
        return null;
    }


}
