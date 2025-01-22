package dev.rinesarusinovci.online_quizzes.exeption;

public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption() {
        super("User not found");
    }

    public UserNotFoundExeption(String message) {
        super(message);
    }
}
