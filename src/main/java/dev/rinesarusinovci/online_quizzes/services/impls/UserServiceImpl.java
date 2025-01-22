package dev.rinesarusinovci.online_quizzes.services.impls;

import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserListingDto;
import dev.rinesarusinovci.online_quizzes.entities.User;
import dev.rinesarusinovci.online_quizzes.exeption.EmailExistsExeption;
import dev.rinesarusinovci.online_quizzes.exeption.UserNotFoundExeption;
import dev.rinesarusinovci.online_quizzes.exeption.UsernameExistsExeption;
import dev.rinesarusinovci.online_quizzes.exeption.WrongPasswordExeption;
import dev.rinesarusinovci.online_quizzes.mapper.UserMapper;
import dev.rinesarusinovci.online_quizzes.mapper.impls.UserMapperImpl;
import dev.rinesarusinovci.online_quizzes.repositories.UserRepository;
import dev.rinesarusinovci.online_quizzes.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapperImpl userMapperImpl;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapperImpl userMapperImpl, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapperImpl = userMapperImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new UserNotFoundExeption();
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordExeption();
        }

        return userMapperImpl.toDto(user);
    }

    @Override
    public UserDto register(RegisterUserDto registerUserRequestDto) {
        if (repository.findByUsername(registerUserRequestDto.getUsername()).isPresent()) {
            throw new UsernameExistsExeption();
        }
        if (repository.findByEmail(registerUserRequestDto.getEmail()).isPresent()) {
            throw new EmailExistsExeption();
        }

        User user = userMapperImpl.userRequestDtoToUser(registerUserRequestDto);

        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));

       User savedUser = repository.save(user);

        return userMapperImpl.toDto(savedUser);
    }


}
