package com.github.prafitradimas.consumer.front.end.dto;

public record UpdateConsumerRequest(
    Integer id,

    String name,

    String address,

    String city,

    String province,

    String status
) {

}
