package ru.kpfu.itis.exceptions;

public class JsonValidationException extends Exception {

    public JsonValidationException() {
        super();
    }

    public JsonValidationException(String message) {
        super(message);
    }

    public JsonValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonValidationException(Throwable cause) {
        super(cause);
    }
}
