package ru.kpfu.itis.exceptions;

public class NotValidProductException extends Exception {

    public NotValidProductException() {
        super();
    }

    public NotValidProductException(String message) {
        super(message);
    }

    public NotValidProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidProductException(Throwable cause) {
        super(cause);
    }
}
