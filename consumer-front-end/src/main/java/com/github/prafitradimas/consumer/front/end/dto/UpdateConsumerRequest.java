package com.github.prafitradimas.consumer.front.end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateConsumerRequest {

    private Integer id;
    private String name;

    private String address;

    private String city;

    private String province;

    private String status;
}
