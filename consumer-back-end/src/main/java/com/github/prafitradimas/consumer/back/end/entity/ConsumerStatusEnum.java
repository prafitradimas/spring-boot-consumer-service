package com.github.prafitradimas.consumer.back.end.entity;

public enum ConsumerStatusEnum {
    ACTIVE("active"), INACTIVE("inactive");

    private final String status;

    ConsumerStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
