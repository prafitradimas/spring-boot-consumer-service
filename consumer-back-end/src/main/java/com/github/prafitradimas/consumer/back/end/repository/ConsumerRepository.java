package com.github.prafitradimas.consumer.back.end.repository;

import com.github.prafitradimas.consumer.back.end.entity.ConsumerEntity;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository {

    /**
     * @return {@code Optional} of {@code ConsumerEntity} object
     * */
    Optional<ConsumerEntity> findById(Integer id);

    /**
     * @return List of {@code ConsumerEntity}
     * */
    List<ConsumerEntity> findAll();

    /**
     * @return either new {@code ConsumerEntity} or {@code null}
     * */
    ConsumerEntity insert(ConsumerEntity entity);

    /**
     * @return either updated {@code ConsumerEntity} or {@code null}
     * */
    ConsumerEntity update(ConsumerEntity entity);

    /**
     * @return Row count affected
     * */
    int deleteById(Integer id);

}
