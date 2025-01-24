package dev.rinesarusinovci.online_quizzes.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException() {
        super("Username already exists");
    }

    public UsernameExistsException(String message) {
        super(message);
    }
}
