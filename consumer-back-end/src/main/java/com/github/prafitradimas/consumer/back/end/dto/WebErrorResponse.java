package com.github.prafitradimas.consumer.back.end.dto;

import java.time.LocalDateTime;

public record WebErrorResponse<T>(
    Integer code,
    String status,
    LocalDateTime timestamp,
    String message,
    String path,
    T errors
) {

}
