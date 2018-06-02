package ru.valuyskiy.chooseyourlunch.util.exception;

import org.springframework.http.HttpStatus;

public class ModificationRestrictionException extends ApplicationException {

    public ModificationRestrictionException() {
        super(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }
}