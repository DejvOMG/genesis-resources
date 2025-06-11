package com.genesis.controller;

import com.genesis.dto.BasicUserDTO;
import com.genesis.dto.DetailedUserDTO;
import com.genesis.dto.UserCreateDTO;
import com.genesis.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<DetailedUserDTO> createUser(@RequestBody @Valid UserCreateDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllUsers(@RequestParam(defaultValue = "false") boolean detail) {
        return detail
                ? ResponseEntity.ok(userService.getAllDetailedUsers())
                : ResponseEntity.ok(userService.getAllBasicUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean detail) {
        return detail
                ? ResponseEntity.ok(userService.getDetailedUserById(id))
                : ResponseEntity.ok(userService.getBasicUserById(id));
    }

    @PutMapping
    public ResponseEntity<DetailedUserDTO> updateUser(@RequestBody @Valid DetailedUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
