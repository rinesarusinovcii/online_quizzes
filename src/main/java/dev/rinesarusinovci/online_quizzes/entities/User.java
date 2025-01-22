package dev.rinesarusinovci.online_quizzes.entities;

import dev.rinesarusinovci.online_quizzes.enums.Role;
import dev.rinesarusinovci.online_quizzes.infrastructure.AgeBetween;
import dev.rinesarusinovci.online_quizzes.infrastructure.Contains;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "Id must be a positive number")
    private long id;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters")
    private String name;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname is required")
    @Size(min = 4, max = 25, message = "Surname must be between 2 and 25 characters")
    private String surname;

    @Column(nullable = false)
    @NotNull(message = "Date of birth is required")
    @NotBlank(message = "Date of birth is required")
    @AgeBetween(min = 16, max = 110, message = "You should be at least 16 years old")
    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true, length = 50)
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @Column(nullable = false, length = 50)
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Contains(value = "@", message = "Email should contain @ symbol")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one number")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Role is required")
    @NotBlank(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "createdBy")
    private List<Quiz> quizzes;


}
