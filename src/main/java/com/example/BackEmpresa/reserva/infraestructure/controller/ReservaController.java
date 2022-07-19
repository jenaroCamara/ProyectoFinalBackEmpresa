package com.example.BackEmpresa.reserva.infraestructure.controller;

import com.example.BackEmpresa.exception.UnprocesableException;
import com.example.BackEmpresa.reserva.application.ReservaUseCase;
import com.example.BackEmpresa.reserva.domain.Reserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.InputDTOReserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.OutputDTOReserva;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/reserva")
public class ReservaController {

    @Autowired
    ReservaUseCase reservaService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<String> realizaReserva(@RequestBody InputDTOReserva reserva) throws Exception {
        OutputDTOReserva r;
        try{
            r = reservaService.realizarReserva(reserva);
        }catch (UnprocesableException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //me queda notificar email por confirmación.
        return ResponseEntity.ok().body("Gracias por su reserva.\n" + r.toString());
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
