package com.github.prafitradimas.consumer.back.end.entity;

import com.github.prafitradimas.consumer.back.end.dto.ConsumerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumerEntity {
    private Integer id;
    private String name;
    private String address;
    private String city;
    private String province;
    private LocalDateTime register_date;
    private ConsumerStatusEnum status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public ConsumerDto toDto() {
        return new ConsumerDto(
            this.id,
            this.name,
            this.address,
            this.city,
            this.province,
            this.register_date.toString(),
            this.status.getStatus()
        );
    }
}