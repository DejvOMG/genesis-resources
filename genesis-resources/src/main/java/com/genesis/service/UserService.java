package com.genesis.service;

import com.genesis.dto.UserCreateDto;
import com.genesis.dto.UserDto;
import com.genesis.exception.InvalidPersonIdException;
import com.genesis.exception.PersonIdAlreadyExistsException;
import com.genesis.exception.UserNotFoundException;
import com.genesis.model.User;
import com.genesis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.InputStream;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    private final Set<String> validPersonIds = new HashSet<>();

    public UserService(UserRepository repository) {
        this.repository = repository;
        loadValidPersonIds();
    }

    private void loadValidPersonIds() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("dataPersonId.txt");
             Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                validPersonIds.add(scanner.nextLine().trim());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to load dataPersonId.txt", e);
        }
    }

    public UserDto create(UserCreateDto dto) {
        if (!validPersonIds.contains(dto.getPersonId())) {
            throw new InvalidPersonIdException("Person ID is not valid: " + dto.getPersonId());
        }

        if (repository.findByPersonId(dto.getPersonId()).isPresent()) {
            throw new PersonIdAlreadyExistsException("Person ID already exists: " + dto.getPersonId());
        }

        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .personId(dto.getPersonId())
                .uuid(UUID.randomUUID().toString())
                .build();

        return UserDto.fromEntity(repository.save(user));
    }

    public List<UserDto> getAll(boolean detail) {
        return repository.findAll().stream()
                .map(user -> UserDto.fromEntity(user, detail))
                .collect(Collectors.toList());
    }

    public UserDto getOne(Long id, boolean detail) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return UserDto.fromEntity(user, detail);
    }

    public UserDto update(Long id, UserCreateDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonId(dto.getPersonId());

        return UserDto.fromEntity(repository.save(user));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        repository.deleteById(id);
    }
}
