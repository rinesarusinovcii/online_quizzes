package dev.rinesarusinovci.online_quizzes.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("Email already exists");
    }
}
