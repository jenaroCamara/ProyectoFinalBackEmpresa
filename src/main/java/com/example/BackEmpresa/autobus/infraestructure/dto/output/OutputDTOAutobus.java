package com.example.BackEmpresa.autobus.infraestructure.dto.output;

import com.example.BackEmpresa.autobus.domain.Autobus;
import lombok.Data;

import java.util.Date;

@Data
public class OutputDTOAutobus {
    int id;
    String origen;
    String destino;
    String horaSalida;
    int asientosOcupados;
    Date fecha;
    int asientosTotales;
}
