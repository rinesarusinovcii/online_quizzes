package dev.rinesarusinovci.online_quizzes.dto;

import dev.rinesarusinovci.online_quizzes.enums.Role;
import dev.rinesarusinovci.online_quizzes.infrastructure.AgeBetween;
import dev.rinesarusinovci.online_quizzes.infrastructure.Contains;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;

    @AgeBetween(min = 16, max = 110, message = "You should be between 16 and 110 years old")
    private LocalDate birthdate;
    private String username;
    private String email;
    private Role role;


}
