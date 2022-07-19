package com.example.BackEmpresa.autobus.infraestructure.dto.input;

import com.example.BackEmpresa.autobus.domain.Autobus;
import lombok.Data;

import java.util.Date;

@Data
public class InputDTOAutobus {
    String origen;
    String destino;
    String horaSalida;
    int asientosOcupados;
    Date fecha;
    int asientosTotales;
}
