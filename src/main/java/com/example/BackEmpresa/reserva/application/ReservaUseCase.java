package com.example.BackEmpresa.reserva.application;

import com.example.BackEmpresa.autobus.domain.Autobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.output.OutputDTOAutobus;
import com.example.BackEmpresa.exception.UnprocesableException;
import com.example.BackEmpresa.principalService.PrincipalService;
import com.example.BackEmpresa.reserva.application.port.ReservaUseCaseInterface;
import com.example.BackEmpresa.reserva.domain.Reserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.InputDTOReserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.OutputDTOReserva;
import com.example.BackEmpresa.reserva.infraestructure.repository.ReservaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservaUseCase implements ReservaUseCaseInterface {

    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    PrincipalService servicioPrincipal;
    @Autowired
    ModelMapper modelMapper;

    public OutputDTOReserva realizarReserva(InputDTOReserva reserva) throws Exception {
        String pattern = "yyyy-MM-dd";
        Date fecha = reserva.getFecha();
        DateFormat df = new SimpleDateFormat(pattern);
        String fechaString = df.format(fecha);
        List<OutputDTOAutobus> autobuses = servicioPrincipal.obtenerAutobus(fechaString,  reserva.getDestino(), reserva.getHora());

        OutputDTOAutobus autobus = puedoReservar(autobuses);
        if(autobus == null){
            throw new UnprocesableException("No se puede reservar");//habrá que lanzar el email
        }

        Reserva r = reservaRepository.save(modelMapper.map(reserva,Reserva.class));
        servicioPrincipal.anadirReservaEnBus(r,autobus.getId());
        servicioPrincipal.sendEmail("pruebaemail151@gmail.com", "Prueba",reserva.toString());
        return modelMapper.map(r, OutputDTOReserva.class);
    }

    private OutputDTOAutobus puedoReservar(List<OutputDTOAutobus> autobuses){
        for(OutputDTOAutobus autobus: autobuses){
            if(autobus.getAsientosOcupados() < autobus.getAsientosTotales())
                return autobus;
        }
        return null;
    }
}
