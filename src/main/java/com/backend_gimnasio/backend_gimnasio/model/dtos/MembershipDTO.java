package com.backend_gimnasio.backend_gimnasio.model.dtos;

import com.backend_gimnasio.backend_gimnasio.enums.MembershipTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipDTO {

    private Long id;

    private String name;

    private Double price;

    private MembershipTypeEnum membershipType;

    private Integer durationDays;

    private String description;
}
