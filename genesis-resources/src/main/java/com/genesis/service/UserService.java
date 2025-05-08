package com.genesis.service;

import com.genesis.dto.UserCreateDto;
import com.genesis.dto.UserDto;
import com.genesis.exception.PersonIdAlreadyExistsException;
import com.genesis.exception.UserNotFoundException;
import com.genesis.model.User;
import com.genesis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto create(UserCreateDto dto) {
        if (repository.existsByPersonId(dto.getPersonId())) {
            throw new PersonIdAlreadyExistsException("PersonID už existuje.");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonId(dto.getPersonId());
        user.setUuid(UUID.randomUUID().toString()); // ✅ Zajištění neprázdného UUID

        user = repository.save(user);
        return UserDto.fromEntity(user);
    }

    public List<UserDto> getAll(boolean detail) {
        return repository.findAll().stream()
                .map(UserDto::fromEntity)
                .toList();
    }

    public UserDto getOne(Long id, boolean detail) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Uživatel s ID " + id + " nebyl nalezen."));
        return UserDto.fromEntity(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("Uživatel s ID " + id + " nebyl nalezen.");
        }
        repository.deleteById(id);
    }

    public UserDto update(Long id, UserCreateDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Uživatel s ID " + id + " nebyl nalezen."));

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonId(dto.getPersonId());

        user = repository.save(user);
        return UserDto.fromEntity(user);
    }
}
