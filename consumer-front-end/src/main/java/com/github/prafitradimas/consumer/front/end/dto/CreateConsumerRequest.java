package com.github.prafitradimas.consumer.front.end.dto;

public record CreateConsumerRequest(
    String name,

    String address,

    String city,

    String province
) {

}