package com.github.prafitradimas.consumer.back.end.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateConsumerRequest(
    @NotBlank(message = "Name should not be empty")
    String name,

    @NotBlank(message = "Address should not be empty")
    String address,

    @NotBlank(message = "City should not be empty")
    String city,

    @NotBlank(message = "Province should not be empty")
    String province
) {

}
