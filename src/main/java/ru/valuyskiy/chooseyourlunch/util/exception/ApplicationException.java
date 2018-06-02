package ru.valuyskiy.chooseyourlunch.util.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ApplicationException extends RuntimeException {

    private final ErrorType type;
    private final HttpStatus httpStatus;
    private final String[] args;

    public ApplicationException(HttpStatus httpStatus) {
        this(ErrorType.APP_ERROR, httpStatus);
    }

    public ApplicationException(ErrorType type, HttpStatus httpStatus, String... args) {
        super(Arrays.toString(args));
        this.type = type;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public ErrorType getType() {
        return type;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String[] getArgs() {
        return args;
    }

}
