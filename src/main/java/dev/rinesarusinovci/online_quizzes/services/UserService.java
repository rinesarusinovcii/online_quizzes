package dev.rinesarusinovci.online_quizzes.services;

import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.entities.User;

public interface UserService {
    UserDto login(String email, String password);

    UserDto register(RegisterUserDto userRegDto);
}
