package com.github.prafitradimas.consumer.back.end.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateConsumerRequest(
    @NotNull(message = "ID should not be null")
    Integer id,

    @NotBlank(message = "Name should not be empty")
    String name,

    @NotBlank(message = "Address should not be empty")
    String address,

    @NotBlank(message = "City should not be empty")
    String city,

    @NotBlank(message = "Province should not be empty")
    String province,

    @NotBlank(message = "Status should not be empty")
    String status
) {

}
