package com.github.prafitradimas.consumer.front.end.controller;

import com.github.prafitradimas.consumer.front.end.dto.CreateConsumerRequest;
import com.github.prafitradimas.consumer.front.end.dto.UpdateConsumerRequest;
import com.github.prafitradimas.consumer.front.end.service.ConsumerService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hx/consumers")
public class ConsumerController {

    @Autowired
    public ConsumerService consumerService;

    @HxRequest
    @GetMapping
    public String getConsumerList(Model model) {
        model.addAttribute("consumerList", consumerService.findAll());
        return "consumerList";
    }

    @HxRequest
    @GetMapping("/create")
    public String getCreateConsumerModal(Model model) {
        return "createConsumerModal";
    }

    @PostMapping("/consumers")
    public String createConsumer(@RequestBody CreateConsumerRequest request, Model model) {
        var consumer = consumerService.createConsumer(request);
        if (consumer == null) {
            return "redirect:/?create=failed";
        }
        model.addAttribute("alert_message", String.format("Successfully create new consumer. Consumer ID: %s", consumer.id()));
        return "redirect:/?create=success";
    }

    @PatchMapping("/consumers/{id}")
    public String updateConsumer(@PathVariable Integer id, @RequestBody UpdateConsumerRequest request) {
        return "";
    }

    @DeleteMapping("/consumers/{id}")
    public String deleteConsumer(@PathVariable Integer id, Model model) {
        if (consumerService.deleteConsumer(id)) {
            model.addAttribute("alert_message", String.format("Successfully delete consumer with id: %s", id));
            return "redirect:/?delete=success";
        }
        model.addAttribute("alert_message", String.format("Failed to delete consumer with id: %s", id));
        return "redirect:/?delete=failed";
    }
}
