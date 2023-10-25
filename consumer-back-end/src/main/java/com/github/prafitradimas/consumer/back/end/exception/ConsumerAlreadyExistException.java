package com.github.prafitradimas.consumer.back.end.exception;

public class ConsumerAlreadyExistException extends RuntimeException {

    public ConsumerAlreadyExistException() {
        super("Consumer already exists.");
    }

}
