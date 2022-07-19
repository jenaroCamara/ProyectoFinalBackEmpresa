package com.example.BackEmpresa.reserva.infraestructure.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InputDTOReserva {
    Date fecha;
    String hora;
    String destino;
}
