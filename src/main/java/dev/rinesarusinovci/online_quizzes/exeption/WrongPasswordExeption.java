package dev.rinesarusinovci.online_quizzes.exeption;

public class WrongPasswordExeption extends RuntimeException {
    public WrongPasswordExeption() {
        super("Wrong password");
    }

    public WrongPasswordExeption(String message) {
        super(message);
    }
}
