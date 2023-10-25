package com.github.prafitradimas.consumer.back.end.service.impl;

import com.github.prafitradimas.consumer.back.end.dto.ConsumerDto;
import com.github.prafitradimas.consumer.back.end.dto.CreateConsumerRequest;
import com.github.prafitradimas.consumer.back.end.dto.UpdateConsumerRequest;
import com.github.prafitradimas.consumer.back.end.entity.ConsumerEntity;
import com.github.prafitradimas.consumer.back.end.entity.ConsumerStatusEnum;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerAlreadyExistException;
import com.github.prafitradimas.consumer.back.end.exception.ConsumerNotFoundException;
import com.github.prafitradimas.consumer.back.end.repository.ConsumerRepository;
import com.github.prafitradimas.consumer.back.end.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    public final ConsumerRepository repository;

    @Override
    public ConsumerDto createConsumer(CreateConsumerRequest request) throws ConsumerAlreadyExistException {
        ConsumerEntity consumerEntity = ConsumerEntity.builder()
            .name(request.name())
            .address(request.address())
            .city(request.city())
            .province(request.province())
            .build();
        return repository.insert(consumerEntity).toDto();
    }

    @Override
    public ConsumerDto updateConsumer(Integer id, UpdateConsumerRequest request) throws ConsumerNotFoundException {
        var consumerEntity = repository.findById(id).orElseThrow(() -> new ConsumerNotFoundException(id));
        consumerEntity.setName(request.name());
        consumerEntity.setAddress(request.address());
        consumerEntity.setCity(request.city());
        consumerEntity.setProvince(request.province());

        if (request.status().equals("active")) {
            consumerEntity.setStatus(ConsumerStatusEnum.ACTIVE);
        } else if (request.status().equals("inactive")) {
            consumerEntity.setStatus(ConsumerStatusEnum.INACTIVE);
        }


        return repository.update(consumerEntity).toDto();
    }

    @Override
    public ConsumerDto findConsumerById(Integer id) throws ConsumerNotFoundException {
        ConsumerEntity consumerEntity = repository.findById(id).orElseThrow(() -> new ConsumerNotFoundException(id));
        return consumerEntity.toDto();
    }

    @Override
    public List<ConsumerDto> findAllConsumer() {
        return repository.findAll().stream()
            .map(ConsumerEntity::toDto)
            .toList();
    }

    @Override
    public void deleteConsumer(Integer id) throws ConsumerNotFoundException {
        int result = repository.deleteById(id);
        if (result < 1) {
            // if no row affected
            log.info(String.format("Failed to delete consumer with id: %s", id));
            throw new ConsumerNotFoundException(id);
        }
    }

}
