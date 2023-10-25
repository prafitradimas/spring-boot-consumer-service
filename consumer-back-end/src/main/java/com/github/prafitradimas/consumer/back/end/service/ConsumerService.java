package com.github.prafitradimas.consumer.back.end.service;

import com.github.prafitradimas.consumer.back.end.dto.ConsumerDto;
import com.github.prafitradimas.consumer.back.end.dto.CreateConsumerRequest;
import com.github.prafitradimas.consumer.back.end.dto.UpdateConsumerRequest;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerAlreadyExistException;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerNotFoundException;

import java.util.List;

public interface ConsumerService {

    ConsumerDto createConsumer(CreateConsumerRequest request) throws ConsumerAlreadyExistException;

    ConsumerDto updateConsumer(Integer id, UpdateConsumerRequest request) throws ConsumerNotFoundException;

    ConsumerDto findConsumerById(Integer id) throws ConsumerNotFoundException;

    List<ConsumerDto> findAllConsumer();

    void deleteConsumer(Integer id) throws ConsumerNotFoundException;

}
