package com.github.prafitradimas.consumer.front.end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateConsumerRequest {

    private String name;

    private String address;

    private String city;

    private String province;

}