package ru.valuyskiy.chooseyourlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.valuyskiy.chooseyourlunch.util.ValidationUtil;
import ru.valuyskiy.chooseyourlunch.util.exception.ApplicationException;
import ru.valuyskiy.chooseyourlunch.util.exception.ErrorInfo;
import ru.valuyskiy.chooseyourlunch.util.exception.ErrorType;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import static ru.valuyskiy.chooseyourlunch.util.exception.ErrorType.*;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
        return logAndGetErrorInfo(req, e, false, DATA_ERROR, rootMsg);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo notFound(HttpServletRequest req, ApplicationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
        return logAndGetErrorInfo(req, e, false, DATA_NOT_FOUND, rootMsg);
    }


    // TODO поверить все эксепшены и допилить ErrorInfo
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ErrorInfo bindValidationError(HttpServletRequest req, Exception e) {
        BindingResult result = e instanceof BindException ?
                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();

        String[] details = result.getSuppressedFields();
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, details);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return new ErrorInfo(req.getRequestURL(),
                errorType,
                details.length != 0 ? details : new String[]{rootCause.getClass().getName()});
    }
}