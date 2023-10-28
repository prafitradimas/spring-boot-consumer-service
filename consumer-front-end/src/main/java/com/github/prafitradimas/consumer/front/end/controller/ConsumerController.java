package com.github.prafitradimas.consumer.front.end.controller;

import com.github.prafitradimas.consumer.front.end.dto.CreateConsumerRequest;
import com.github.prafitradimas.consumer.front.end.dto.UpdateConsumerRequest;
import com.github.prafitradimas.consumer.front.end.service.ConsumerService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ConsumerController {

    @Autowired
    public ConsumerService consumerService;

    @PostMapping(value = "/consumers", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addConsumer(CreateConsumerRequest request, Model model) {
        log.info(String.format("create new consumer: %s", request.toString()));
        var consumer = consumerService.createConsumer(request);
        if (consumer == null) {
            return "redirect:/?create=failed";
        }
        model.addAttribute("alert_message", String.format("Successfully create new consumer. Consumer ID: %s", consumer.get("id")));
        return "redirect:/?create=success";
    }

    @PostMapping(value = "/consumers/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateConsumer(@PathVariable Integer id, UpdateConsumerRequest request, Model model) {
        log.info(String.format("update consumer: %s", request.toString()));
        var consumer = consumerService.updateConsumer(request);
        if (consumer == null) {
            return "redirect:/?update=failed";
        }
        model.addAttribute("alert_message", "Consumer updated successfully");
        return "redirect:/?update=success";
    }

    @DeleteMapping(value = "/consumers/{id}")
    public String deleteConsumer(@PathVariable Integer id, Model model) {
        if (consumerService.deleteConsumer(id)) {
            model.addAttribute("alert_message", String.format("Successfully delete consumer with id: %s", id));
            return "redirect:/?delete=success";
        }
        model.addAttribute("alert_message", String.format("Failed to delete consumer with id: %s", id));
        return "redirect:/?delete=failed";
    }

    @HxRequest
    @GetMapping("/consumers/views/list")
    public String getConsumerList(Model model) {
        model.addAttribute("consumerList", consumerService.findAll());
        return "consumerList";
    }

    @HxRequest
    @GetMapping("/consumers/views/modal-add-consumer")
    public String getAddConsumerModal(Model model) {
        model.addAttribute("create_request", new CreateConsumerRequest());
        return "modalAddConsumer";
    }

    @HxRequest
    @GetMapping("/consumers/views/modal-update-consumer")
    public String getUpdateConsumerModal(Model model) {
        model.addAttribute("update_request", new UpdateConsumerRequest());
        return "modalUpdateConsumer";
    }

}
