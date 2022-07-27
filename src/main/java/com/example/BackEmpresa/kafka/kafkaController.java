package com.example.BackEmpresa.kafka;


import com.example.BackEmpresa.reserva.domain.Reserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.InputDTOReserva;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/kafka")
public class kafkaController {
    private final Producer producerService;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public kafkaController(Producer producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producerService.sendMessage(message,"backEmpresa");
        System.out.println("Enviado mensaje desde empresa controlador.");
    }
    @PostMapping(value = "/publishReserva")
    public void sendMessageToKafkaReserva(@RequestBody InputDTOReserva reserva) throws IOException {
        Reserva r = modelMapper.map(reserva, Reserva.class);
        System.out.println(r.toStringtwo());
        this.producerService.sendMessage(r.toStringtwo(),"backEmpresa");
        System.out.println("Enviado mensaje desde empresa controlador.");
    }
}
