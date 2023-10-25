package com.github.prafitradimas.consumer.back.end.controller;

import com.github.prafitradimas.consumer.back.end.dto.WebErrorResponse;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerAlreadyExistException;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ConsumerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebErrorResponse<Object>> handleGlobalException(Exception ex, WebRequest request) {

        var response = new WebErrorResponse<Object>(
            500,
            "INTERNAL SERVER ERROR",
            LocalDateTime.now(),
            "Oops! something went wrong",
            request.getContextPath(),
            null
        );
        log.error(String.format("Level: ERROR, Cause: %s", ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConsumerNotFoundException.class)
    public ResponseEntity<WebErrorResponse<Map<String, Object>>> handleConsumerNotFoundException(ConsumerNotFoundException ex, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("id", ex.CONSUMER_ID);

        var response = new WebErrorResponse<Map<String, Object>>(
            404,
            "NOT FOUND",
            LocalDateTime.now(),
            ex.getMessage(),
            request.getContextPath(),
            errors
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsumerAlreadyExistException.class)
    public ResponseEntity<WebErrorResponse<Object>> handleConsumerAlreadyExistException(ConsumerAlreadyExistException ex, WebRequest request) {
        var response = new WebErrorResponse<Object>(
            400,
            "BAD REQUEST",
            LocalDateTime.now(),
            ex.getMessage(),
            request.getContextPath(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();

        var response = new WebErrorResponse<List<?>>(
            400,
            "BAD REQUEST",
            LocalDateTime.now(),
            "Validation errors",
            request.getContextPath(),
            errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
