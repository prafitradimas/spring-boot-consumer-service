package com.github.prafitradimas.consumer.back.end.controller;

import com.github.prafitradimas.consumer.back.end.dto.ConsumerDto;
import com.github.prafitradimas.consumer.back.end.dto.CreateConsumerRequest;
import com.github.prafitradimas.consumer.back.end.dto.UpdateConsumerRequest;
import com.github.prafitradimas.consumer.back.end.dto.WebResponse;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerAlreadyExistException;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerNotFoundException;
import com.github.prafitradimas.consumer.back.end.service.ConsumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consumers")
@RequiredArgsConstructor
public class ConsumerV1Controller {

    @Autowired
    public final ConsumerService consumerService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Integer id) throws ConsumerNotFoundException {
        var consumer = consumerService.findConsumerById(id);
        var response = new WebResponse<ConsumerDto>(200, "OK", "successfully retrieve consumer information.", LocalDateTime.now(), consumer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        var consumers = consumerService.findAllConsumer();
        var response = new WebResponse<List<ConsumerDto>>(200, "OK", "successfully retrieve consumer information.", LocalDateTime.now(), consumers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateConsumerRequest request) throws ConsumerAlreadyExistException {
        var consumer = consumerService.createConsumer(request);
        var response = new WebResponse<ConsumerDto>(200, "OK", "successfully creating new consumer", LocalDateTime.now(), consumer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody UpdateConsumerRequest request) throws ConsumerNotFoundException {
        var consumer = consumerService.updateConsumer(id, request);
        var message = String.format("successfully update consumer with id: %s", id);
        var response = new WebResponse<ConsumerDto>(200, "OK", "successfully creating new consumer", LocalDateTime.now(), consumer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ConsumerNotFoundException {
        consumerService.deleteConsumer(id);
        var message = String.format("successfully delete consumer with id: %s", id);
        var response = new WebResponse<Object>(200, "OK", message, LocalDateTime.now(), null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
