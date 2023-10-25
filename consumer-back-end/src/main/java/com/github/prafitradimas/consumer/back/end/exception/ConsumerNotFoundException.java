package com.github.prafitradimas.consumer.back.end.exception;

public class ConsumerNotFoundException extends RuntimeException {

    public final Integer CONSUMER_ID;

    public ConsumerNotFoundException(Integer id) {
        super(String.format("Consumer with id: %s not found.", id));
        this.CONSUMER_ID = id;
    }

}
