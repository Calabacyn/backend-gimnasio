package com.backend_gimnasio.backend_gimnasio.model.entities;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "national_id", nullable = false, unique = true, length = 20)
    private String nationalId;

    @Column(length = 20)
    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_by", nullable = false)
    private UserEntity registeredByEmail;



    public void updateFromDto(ClientDTO dto, UserEntity registeredByEmail) {
        if (dto == null) return;

        if (dto.getFirstName() != null) this.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) this.setLastName(dto.getLastName());
        if (dto.getEmail() != null) this.setEmail(dto.getEmail());
        if (dto.getNationalId() != null) this.setNationalId(dto.getNationalId());
        if (dto.getPhone() != null) this.setPhone(dto.getPhone());
        if (dto.getBirthDate() != null) this.setBirthDate(dto.getBirthDate());
        if (dto.getRegistrationDate() != null) this.setRegistrationDate(dto.getRegistrationDate());
        if (registeredByEmail != null && registeredByEmail.getEmail() != null) {
            this.setRegisteredByEmail(registeredByEmail);
        }
    }

}
