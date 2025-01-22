package dev.rinesarusinovci.online_quizzes.exeption;

public class EmailExistsExeption extends RuntimeException {
    public EmailExistsExeption(String message) {
        super(message);
    }

    public EmailExistsExeption() {
        super("Email already exists");
    }
}
