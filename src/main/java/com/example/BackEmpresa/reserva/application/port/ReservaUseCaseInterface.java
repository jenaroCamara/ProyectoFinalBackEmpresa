package com.example.BackEmpresa.reserva.application.port;

import com.example.BackEmpresa.reserva.domain.Reserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.InputDTOReserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.OutputDTOReserva;

import java.util.List;

public interface ReservaUseCaseInterface {
    public OutputDTOReserva realizarReserva(InputDTOReserva reserva) throws Exception;
//    private OutputDTOAutobus puedoReservar(Reserva reserva, List<OutputDTOAutobus> autobuses);
}
