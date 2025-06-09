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
    public ResponseEntity<DetailedUserDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllUsers(@RequestParam(defaultValue = "false") boolean detail) {
        if (detail) {
            return ResponseEntity.ok(userService.getAllDetailedUsers());
        } else {
            return ResponseEntity.ok(userService.getAllBasicUsers());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean detail) {
        if (detail) {
            return ResponseEntity.ok(userService.getDetailedUserById(id));
        } else {
            return ResponseEntity.ok(userService.getBasicUserById(id));
        }
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
