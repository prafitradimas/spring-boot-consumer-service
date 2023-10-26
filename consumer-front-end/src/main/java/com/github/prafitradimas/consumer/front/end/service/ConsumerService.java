package com.github.prafitradimas.consumer.front.end.service;

import com.github.prafitradimas.consumer.front.end.dto.ConsumerDto;
import com.github.prafitradimas.consumer.front.end.dto.CreateConsumerRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ConsumerService {

    @Value("${BACKEND_URL}")
    public String BACKEND_URL;

    public List<ConsumerDto> findAll() {
        var restTemplate = new RestTemplate();
        String resourceUrl = BACKEND_URL.concat("/api/v1/consumers");
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(resourceUrl, Map.class);
        if (responseEntity.getStatusCode() != HttpStatusCode.valueOf(200)) {
            // @TODO
            return null;
        }

        return (List<ConsumerDto>) responseEntity.getBody().get("data");
    }

    public ConsumerDto createConsumer(CreateConsumerRequest request) {
        var restTemplate = new RestTemplate();
        String resourceUrl = BACKEND_URL.concat("/api/v1/consumers");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<CreateConsumerRequest> httpRequest = new HttpEntity<>(request, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
            resourceUrl,
            HttpMethod.POST,
            httpRequest,
            Map.class
        );

        if (responseEntity.getStatusCode() != HttpStatusCode.valueOf(200)) {
            // @TODO
            return null;
        }

        return (ConsumerDto) responseEntity.getBody().get("data");
    }

    public Boolean deleteConsumer(Integer id) {
        var restTemplate = new RestTemplate();
        String resourceUrl = BACKEND_URL.concat("/api/v1/consumers/" + id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<CreateConsumerRequest> httpRequest = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
            resourceUrl,
            HttpMethod.DELETE,
            httpRequest,
            Object.class
        );

        // @TODO
        return responseEntity.getStatusCode() == HttpStatusCode.valueOf(200);
    }

}
