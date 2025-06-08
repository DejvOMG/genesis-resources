package com.genesis.service;

import com.genesis.dto.*;
import com.genesis.exception.*;
import com.genesis.mapper.UserMapper;
import com.genesis.model.User;
import com.genesis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public DetailedUserDTO createUser(UserCreateDTO dto) {
        if (userRepository.existsByPersonId(dto.getPersonId())) {
            throw new PersonIdAlreadyExistsException("PersonId already exists");
        }

        User user = userMapper.toEntity(dto);
        user.setUuid(UUID.randomUUID().toString());
        user = userRepository.save(user);
        return userMapper.toDetailedDto(user);
    }

    public List<BasicUserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toBasicDto)
                .toList();
    }

    public DetailedUserDTO updateUser(DetailedUserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return userMapper.toDetailedDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public DetailedUserDTO getDetailedUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDetailedDto(user);
    }

    public BasicUserDTO getBasicUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toBasicDto(user);
    }
}
