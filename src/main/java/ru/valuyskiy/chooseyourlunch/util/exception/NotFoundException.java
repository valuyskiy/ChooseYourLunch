package ru.valuyskiy.chooseyourlunch.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {

    public NotFoundException(String arg) {
        super(ErrorType.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, arg);
    }
}