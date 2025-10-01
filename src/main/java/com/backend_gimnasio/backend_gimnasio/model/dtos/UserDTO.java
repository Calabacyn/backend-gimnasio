package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * DTO for transferring user information.
 * Represents the main data of a system user.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String email;
    private String password;      // optional, used for creation or update
    private String role;          // e.g., ADMIN, STAFF, CLIENT
    private String status;        // 'active' or 'inactive'
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
