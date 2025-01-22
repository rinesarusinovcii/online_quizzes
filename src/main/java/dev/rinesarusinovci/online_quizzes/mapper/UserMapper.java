package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.RegisterUserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import dev.rinesarusinovci.online_quizzes.dto.UserListingDto;
import dev.rinesarusinovci.online_quizzes.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserMapper extends BaseMapper<User, UserDto> {


    UserListingDto toUserListingDto(User user);

    User userRequestDtoToUser(RegisterUserDto registerUserRequestDto);


}
