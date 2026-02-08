package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.UserNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.UserCreateDTO;
import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }


    @GetMapping("/{email:.+}")
    public ResponseEntity<UserDTO> getBy(@PathVariable String email) {
        UserDTO user = userService.getBy(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserCreateDTO user) {
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UserDTO user) {
        userService.update(user);
    }


    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String email) {
        userService.delete(email);
    }
}
