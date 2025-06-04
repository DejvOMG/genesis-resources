package com.genesis.service;

import com.genesis.dto.UserCreateDTO;
import com.genesis.dto.UserDTO;
import com.genesis.exception.InvalidPersonIdException;
import com.genesis.exception.PersonIdAlreadyExistsException;
import com.genesis.exception.UserNotFoundException;
import com.genesis.mapper.UserMapper;
import com.genesis.model.User;
import com.genesis.repository.UserRepository;
import com.genesis.validator.PersonIdValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PersonIdValidator personIdValidator;
    private final UserMapper userMapper;

    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        if (!personIdValidator.isValid(userCreateDTO.getPersonId())) {
            throw new InvalidPersonIdException("Invalid person ID.");
        }

        if (userRepository.existsByPersonId(userCreateDTO.getPersonId())) {
            throw new PersonIdAlreadyExistsException("Person ID already exists.");
        }

        User user = userMapper.fromCreateDto(userCreateDTO);
        user.setUuid(UUID.randomUUID().toString());

        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        return userMapper.toDto(user);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());

        return userMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found.");
        }
        userRepository.deleteById(id);
    }
}