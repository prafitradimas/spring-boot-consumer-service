package com.github.prafitradimas.consumer.back.end.dto;

import java.time.LocalDateTime;

public record WebResponse<T>(
    Integer code,
    String status,
    String message,
    LocalDateTime timestamp,
    T data
) {

}
