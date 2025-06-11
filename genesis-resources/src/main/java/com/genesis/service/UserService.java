package com.genesis.service;

import com.genesis.dto.BasicUserDTO;
import com.genesis.dto.DetailedUserDTO;
import com.genesis.dto.UserCreateDTO;
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
    private final UserMapper userMapper;
    private final PersonIdValidator personIdValidator;

    public DetailedUserDTO createUser(UserCreateDTO dto) {
        personIdValidator.validate(dto.getPersonId());
        if (userRepository.existsByPersonId(dto.getPersonId())) {
            throw new PersonIdAlreadyExistsException("PersonId already exists");
        }
        User user = userMapper.toEntity(dto);
        user.setUuid(UUID.randomUUID().toString());
        user = userRepository.save(user);
        return userMapper.toDetailedDto(user);
    }


    public List<BasicUserDTO> getAllBasicUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toBasicDto)
                .collect(Collectors.toList());
    }


    public List<DetailedUserDTO> getAllDetailedUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDetailedDto)
                .collect(Collectors.toList());
    }

    public BasicUserDTO getBasicUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toBasicDto(user);
    }

    public DetailedUserDTO getDetailedUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDetailedDto(user);
    }

    public DetailedUserDTO updateUser(DetailedUserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        return userMapper.toDetailedDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
