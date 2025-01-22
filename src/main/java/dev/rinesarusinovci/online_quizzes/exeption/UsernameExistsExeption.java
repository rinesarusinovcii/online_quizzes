package dev.rinesarusinovci.online_quizzes.exeption;

public class UsernameExistsExeption extends RuntimeException{
    public UsernameExistsExeption() {
        super("Username already exists");
    }

    public UsernameExistsExeption(String message) {
        super(message);
    }
}
