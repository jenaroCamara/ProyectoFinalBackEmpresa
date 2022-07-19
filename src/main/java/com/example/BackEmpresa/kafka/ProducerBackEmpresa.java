package com.example.BackEmpresa.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ProducerBackEmpresa {
    private static final Logger logger = LoggerFactory.getLogger(org.apache.kafka.clients.producer.Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) throws InterruptedException {
        logger.info(String.format("#### -> Producing message -> %s", message));
        System.out.println("Enviado en backEmpresa, el producer");
        TimeUnit.SECONDS.sleep(2);
        this.kafkaTemplate.send(TOPIC, message + " modificado");

    }
    ///docker-compose -f zk-single-kafka-multiple-4.yml up -d
//docker-compose -f zk-single-kafka-multiple-4.yml down
}
