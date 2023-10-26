package com.github.prafitradimas.consumer.front.end.dto;

public record ConsumerDto(
    Integer id,
    String name,
    String address,
    String city,
    String province,
    String register_at,
    String status
) {

}
