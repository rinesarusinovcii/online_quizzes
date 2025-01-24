package dev.rinesarusinovci.online_quizzes.mapper.impls;

import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserListingDto;
import dev.rinesarusinovci.online_quizzes.entities.User;
import dev.rinesarusinovci.online_quizzes.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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
       user.setBirthdate(registerUserRequestDto.getBirthdate());
        user.setRole(registerUserRequestDto.getRole());

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
        user.setBirthdate(userDto.getBirthdate());
        user.setRole(userDto.getRole());

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
        userDto.setBirthdate(user.getBirthdate());
        userDto.setRole(user.getRole());

        return userDto;
    }

    @Override
    public List<User> toEntities(List<UserDto> userDtos) {
        return List.of();
    }

    @Override
    public List<UserDto> toDtos(List<User> users) {
        return List.of();
    }
}
