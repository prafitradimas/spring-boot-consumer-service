package com.github.prafitradimas.consumer.front.end.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(HttpServerErrorException.class)
    public String handleHttpServerError(HttpServerErrorException ex, Model model) {
        model.addAttribute("error_title", "INTERNAL SERVER ERROR");
        model.addAttribute("error_message", ex.getMessage());
        return "error";
    }

}
