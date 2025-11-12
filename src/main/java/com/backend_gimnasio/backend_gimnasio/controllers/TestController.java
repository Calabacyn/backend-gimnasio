package com.backend_gimnasio.backend_gimnasio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("✅ Endpoint ADMIN accesible");
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> testClient() {
        return ResponseEntity.ok("✅ Endpoint CLIENT accesible");
    }

    @GetMapping("/staff")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<String> testStaff() {
        return ResponseEntity.ok("✅ Endpoint STAFF accesible");
    }

    @GetMapping("/public")
    public ResponseEntity<String> testPublic() {
        return ResponseEntity.ok("🌍 Endpoint público accesible sin autenticación");
    }
}
