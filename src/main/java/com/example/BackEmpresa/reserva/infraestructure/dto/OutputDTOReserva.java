package com.example.BackEmpresa.reserva.infraestructure.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OutputDTOReserva {
    String id_r;
    Date fecha;
    String hora;
    String destino;
}
