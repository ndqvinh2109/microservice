package com.nab.assignment.shopping.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.nab.assignment.shopping.util.ErrorMessageBundle.byStatus;


@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Malformed JSON request";
        return byStatus(HttpStatus.BAD_REQUEST).withError(error).whichException(ex).buildMessage().thenResponseEntity();
    }

    @ExceptionHandler({ShoppingException.class})
    public ResponseEntity<Object> handleInvalidParameters(ShoppingException ex) {
        String error = "Invalid request parameter";
        return byStatus(HttpStatus.BAD_REQUEST).withError(error).whichException(ex).buildMessage().thenResponseEntity();
    }

}
