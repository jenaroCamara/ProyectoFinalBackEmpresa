package com.example.BackEmpresa.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka/backEmpresa")
public class KafkaControllerBackEmpresa {
    private final ProducerBackEmpresa producerService;

    @Autowired
    public KafkaControllerBackEmpresa(ProducerBackEmpresa producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publicar")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) throws InterruptedException {
        this.producerService.sendMessage(message);
    }
}
