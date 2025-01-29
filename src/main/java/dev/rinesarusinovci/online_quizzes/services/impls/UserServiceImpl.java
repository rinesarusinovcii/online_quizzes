package dev.rinesarusinovci.online_quizzes.services.impls;


import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.entities.User;
import dev.rinesarusinovci.online_quizzes.exception.EmailExistsException;
import dev.rinesarusinovci.online_quizzes.exception.UserNotFoundException;
import dev.rinesarusinovci.online_quizzes.exception.UsernameExistsException;
import dev.rinesarusinovci.online_quizzes.exception.WrongPasswordException;
import dev.rinesarusinovci.online_quizzes.mapper.UserMapper;

import dev.rinesarusinovci.online_quizzes.repositories.UserRepository;
import dev.rinesarusinovci.online_quizzes.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapperImpl;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapper userMapperImpl, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapperImpl = userMapperImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }

        return userMapperImpl.toDto(user);
    }

    @Override
    public boolean register(RegisterUserDto registerUserRequestDto) {
        if (repository.findByUsername(registerUserRequestDto.getUsername()).isPresent()) {
            throw new UsernameExistsException();
        }
        if (repository.findByEmail(registerUserRequestDto.getEmail()).isPresent()) {
            throw new EmailExistsException();
        }

        User user = userMapperImpl.userRequestDtoToUser(registerUserRequestDto);

        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));

        repository.save(user);

        return true;
    }


    @Override
    public List<UserDto> findAll() {
        return userMapperImpl.toDtos(repository.findAll());
    }

    @Override
    public UserDto findById(Long aLong) {
        return userMapperImpl.toDto(repository.findById(aLong).orElse(null));
    }

    @Override
    public UserDto add(UserDto model) {
        return save(model);
    }

    @Override
    public UserDto modify(Long id, UserDto model) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(model.getName());
        user.setSurname(model.getSurname());
        user.setEmail(model.getEmail());
        user.setBirthdate(model.getBirthdate());
        user.setUsername(model.getUsername());

        // Ruaj të dhënat pa prekur fjalëkalimin
        User updatedUser = repository.save(user);

        return userMapperImpl.toDto(updatedUser);
    }

    @Override
    public void removeById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        repository.delete(user);
    }

    private UserDto save(UserDto model) {
        var userDto = userMapperImpl.toEntity(model);
        var savedUser = repository.save(userDto);
        return userMapperImpl.toDto(savedUser);
    }
}
