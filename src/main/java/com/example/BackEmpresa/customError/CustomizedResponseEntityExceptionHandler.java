package com.example.BackEmpresa.customError;

import com.example.BackEmpresa.exception.NotFoundException;
import com.example.BackEmpresa.exception.UnprocesableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<customError> handleNotFoundException(NotFoundException ex, WebRequest request) {
        customError exceptionResponse = new customError(new Date(), "Error ", HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<customError>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<customError> handleUnprocesableException(NotFoundException ex, WebRequest request) {
        customError exceptionResponse = new customError(new Date(), "Error ", HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<customError>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
