package com.example.BackEmpresa.kafka;

import com.example.BackEmpresa.principalService.PrincipalService;
import com.example.BackEmpresa.reserva.application.ReservaUseCase;
import com.example.BackEmpresa.reserva.domain.Reserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.InputDTOReserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.OutputDTOReserva;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;
import java.text.ParseException;

@Configuration // o @Component
class Consumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private ReservaUseCase reservaService;
    @Autowired
    ModelMapper modelMapper;


    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    //-------------------Esto solo para backEmpresa
    @KafkaListener(topics = "backEmpresa", groupId = "myGroup")
    void consume(String data) throws IOException, ClassNotFoundException, ParseException {
        Reserva reserva = new Reserva(data);
        InputDTOReserva reservaDTO = modelMapper.map(reserva, InputDTOReserva.class);
        OutputDTOReserva outputReserva = null;
        try {
            outputReserva = reservaService.realizarReserva(reservaDTO);
        } catch (Exception e) {
            logger.info("No se ha realizado la reserva de forma correcta\n");
            this.kafkaTemplate.send("failReserva", "null");
        }
        if (outputReserva != null) {
            logger.info("Se ha realizado la reserva de forma correcta\n" + outputReserva.toString());
            this.kafkaTemplate.send("reservaCorrecta", modelMapper.map(outputReserva, Reserva.class).toStringtwo());
        }
    }

    //----------------------Esto va para backweb1 y 2-----------------
    @KafkaListener(topics = "failReserva", groupId = "myGroup")
    void comunicarFalloReserva(String data) throws IOException, ClassNotFoundException, ParseException {
        logger.info("fallo en reserva --> backempresa");
        principalService.sendEmail("pruebaemail151@gmail.com", "Reserva", "Fallo en la reserva.");
        //guardar en listafalloreserva
    }


    @KafkaListener(topics = "reservaCorrecta", groupId = "myGroup")
    void comunicarReserva(String data) throws IOException, ClassNotFoundException, ParseException {
        logger.info("Reserva aceptada --> backempresa");
        //guardar en la bbdd de backweb1 y 2
    }
    //----------------------Esto va para backweb1 y 2-----------------


    @KafkaListener(topics = "mytopic", groupId = "myGroup")
    void consume2(String data) throws IOException, ClassNotFoundException, ParseException {
        Reserva reserva = new Reserva(data);
        logger.info(data);
        System.out.println("escuchando listener kafkaconsumer backEmpresa\n " + data);
    }

}