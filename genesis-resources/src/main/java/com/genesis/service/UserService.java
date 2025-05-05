package com.genesis.service;

import com.genesis.dto.UserCreateDto;
import com.genesis.dto.UserDto;
import com.genesis.exception.InvalidPersonIdException;
import com.genesis.exception.PersonIdAlreadyExistsException;
import com.genesis.exception.UserNotFoundException;
import com.genesis.model.User;
import com.genesis.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final Set<String> validPersonIds = new HashSet<>();

    @Value("classpath:dataPersonId.txt")
    private Resource personIdFile;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadValidPersonIds() throws Exception {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(personIdFile.getInputStream(), StandardCharsets.UTF_8))) {
            validPersonIds.addAll(reader.lines().collect(Collectors.toSet()));
        }
    }

    public UserDto create(UserCreateDto dto) {
        if (!validPersonIds.contains(dto.getPersonId())) {
            throw new InvalidPersonIdException("Neplatné personID.");
        }

        if (repository.findByPersonId(dto.getPersonId()).isPresent()) {
            throw new PersonIdAlreadyExistsException("PersonID již existuje.");
        }

        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .personId(dto.getPersonId())
                .uuid(UUID.randomUUID().toString())
                .build();

        repository.save(user);
        return toDto(user, true);
    }

    public List<UserDto> getAll(boolean detail) {
        return repository.findAll().stream()
                .map(user -> toDto(user, detail))
                .toList();
    }

    public UserDto getOne(Long id, boolean detail) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Uživatel nenalezen."));
        return toDto(user, detail);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("Uživatel nenalezen.");
        }
        repository.deleteById(id);
    }

    public UserDto toDto(User user, boolean detail) {
        if (detail) {
            return UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .personId(user.getPersonId())
                    .uuid(user.getUuid())
                    .build();
        } else {
            return UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .build();
        }
    }
}
