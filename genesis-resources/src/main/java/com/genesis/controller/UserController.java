package com.genesis.controller;

import com.genesis.dto.UserCreateDto;
import com.genesis.dto.UserDto;
import com.genesis.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(defaultValue = "false") boolean detail) {
        return ResponseEntity.ok(service.getAll(detail));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable Long id,
                                          @RequestParam(defaultValue = "false") boolean detail) {
        return ResponseEntity.ok(service.getOne(id, detail));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
