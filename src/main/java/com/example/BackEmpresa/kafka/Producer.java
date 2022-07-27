package com.example.BackEmpresa.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * KafkaProducerConfig
 */
@Configuration
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(org.apache.kafka.clients.producer.Producer.class);
    private static final String TOPIC = "mytopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String topic) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(topic, message);
    }

//    public static byte[] convertObjectToBytes(Object obj) {
//        ByteArrayOutputStream boas = new ByteArrayOutputStream();
//        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
//            ois.writeObject(obj);
//            return boas.toByteArray();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        throw new RuntimeException();
//    }
//
//    public String convertByteArrayToString(byte[] bytes) {
//        String res = null;
//        try {
//            res = new String(bytes);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }


    ///docker-compose -f zk-single-kafka-multiple-4.yml up -d
//docker-compose -f zk-single-kafka-multiple-4.yml down
    //https://reflectoring.io/spring-boot-kafka/

    //https://refactorizando.com/kafka-spring-boot-parte-uno/
    /*
    docker run --rm -p 2181:2181 -p 3030:3030 -p 8081-8083:8081-8083 -p 9581-9585:9581-9585 -p 9092:9092 -e ADV_HOST=localhost landoop/fast-data-dev:latest
     */
}