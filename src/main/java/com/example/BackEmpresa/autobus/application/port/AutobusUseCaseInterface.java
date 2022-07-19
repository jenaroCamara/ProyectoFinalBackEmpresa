package com.example.BackEmpresa.autobus.application.port;

import com.example.BackEmpresa.autobus.application.AutobusUseCase;
import com.example.BackEmpresa.autobus.domain.Autobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.input.InputDTOAutobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.output.OutputDTOAutobus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AutobusUseCaseInterface {
     List<OutputDTOAutobus> getAll();
     OutputDTOAutobus crear(InputDTOAutobus autobus);
     List<OutputDTOAutobus>getDataForCriteria(String destino, String fecha, String hora);
     List<OutputDTOAutobus> obtenerAutobusFechaDestino(HashMap<String, Object> data);
}
