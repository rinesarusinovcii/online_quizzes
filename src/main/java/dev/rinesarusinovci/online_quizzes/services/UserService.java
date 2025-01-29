package dev.rinesarusinovci.online_quizzes.services;

import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.entities.User;

public interface UserService  extends BaseService<UserDto,Long>{
    UserDto login(String email, String password);

    boolean register(RegisterUserDto userRegDto);
}
