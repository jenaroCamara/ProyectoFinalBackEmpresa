package com.example.BackEmpresa.autobus.infraestructure.controller;

import com.example.BackEmpresa.autobus.application.AutobusUseCase;
import com.example.BackEmpresa.autobus.infraestructure.dto.input.InputDTOAutobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.output.OutputDTOAutobus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/autobus")
public class AutobusController {
    @Autowired
    AutobusUseCase autobusService;

    @GetMapping("/getAll")
    public List<OutputDTOAutobus> getAll(){
        return autobusService.getAll();
    }

    @PostMapping("/")
    public OutputDTOAutobus crear(@RequestBody InputDTOAutobus autobus){
        return autobusService.crear(autobus);
    }

    @GetMapping("/{destino}/{fech}/{hora}")
    public List<OutputDTOAutobus> getUsuariosByName2(@PathVariable String destino, @PathVariable String fech, @PathVariable String hora) throws Exception {
        List<OutputDTOAutobus> listaAutobuses = autobusService.getDataForCriteria(destino, fech, hora);//queda la hora
        return listaAutobuses;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
