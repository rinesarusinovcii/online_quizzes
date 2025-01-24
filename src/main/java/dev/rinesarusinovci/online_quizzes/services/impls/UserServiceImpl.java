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

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



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




}
