//package dev.rinesarusinovci.online_quizzes.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class QuizCreatedDto {
//    @NotBlank(message = "Title is required")
//    @Size(min = 10, max = 50, message = "Title must be between 10 and 50 characters")
//    private String title;
//
//    @NotBlank(message = "Category is required")
//    @Size(min = 5, max = 20, message = "Category must be between 5 and 20 characters")
//    private String category;
//
//    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
//    private String description;
//
//    @NotNull(message = "Time limit is required")
//    @Positive(message = "Time limit must be a positive number")
//    private int timeLimit;
//}
