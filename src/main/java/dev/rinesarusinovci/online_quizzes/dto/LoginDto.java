package dev.rinesarusinovci.online_quizzes.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @Email(message = "Emaili duhet te jete i sakte!")
    @NotBlank(message = "Emaili nuk mund te jete i zbrazet!")
    @NotNull(message = "Emaili nuk mund te jete null!")
    @Size(message = "Emaili duhet te jete me shkruajt 3 deri ne 50 karaktere!", min = 3, max = 50)
    private String email;


    private String password;


    private boolean rememberMe;
}
