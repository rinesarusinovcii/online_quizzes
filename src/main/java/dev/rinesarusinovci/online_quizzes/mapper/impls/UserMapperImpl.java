package dev.rinesarusinovci.online_quizzes.mapper.impls;

import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserListingDto;
import dev.rinesarusinovci.online_quizzes.entities.User;
import dev.rinesarusinovci.online_quizzes.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserListingDto toUserListingDto(User user) {
        UserListingDto dto = new UserListingDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        return dto;
    }

    @Override
    public User userRequestDtoToUser(RegisterUserDto registerUserRequestDto) {
        User user = new User();

        user.setUsername(registerUserRequestDto.getUsername());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setName(registerUserRequestDto.getName());
        user.setSurname(registerUserRequestDto.getSurname());
        user.setDateOfBirth(registerUserRequestDto.getBirthdate());

        return user;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRole(userDto.getRole());
        user.setDateOfBirth(userDto.getDateOfBirth());
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setRole(user.getRole());
        userDto.setDateOfBirth(user.getDateOfBirth());
        return userDto;
    }
}
