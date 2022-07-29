package com.example.BackEmpresa.reserva.infraestructure.controller;

import com.example.BackEmpresa.exception.UnprocesableException;
import com.example.BackEmpresa.model.JwtRequest;
import com.example.BackEmpresa.model.JwtResponse;
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

    /*
    Endpoints síncronos:
    · Creación reservas. Le llegaran peticiones REST desde el front (angular?)
    · Número de plazas libres en un autobús para un día/hora/destino. Buscará en la tabla reservas disponibles (‘autobus-hora-dia-ciudad-numero_plazas_disponibles')
    · Consulta reservas realizadas. Aceptará un token JWT firmado por la empresa y que considerará valido.
     */
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

    @GetMapping("/{destino}/{hora}/{fecha}")
    public ResponseEntity<String> obtenerAsientos(@PathVariable String fecha, @PathVariable String destino, @PathVariable String hora) throws Exception {
        int suma;
        try{
            suma = reservaService.obtenerAsientos(fecha, destino, hora);
        }catch (UnprocesableException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //me queda notificar email por confirmación.
        return ResponseEntity.ok().body("Asientos disponibles: " + suma);
    }

    //---------------------------------------------------------------------------------//
    //backweb1 y backweb2 neccesitaran un endpoint like this
    @PostMapping("/v2/employees")
    public ResponseEntity createV2(@RequestBody final JwtRequest peticion) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity < Employee > entity = new HttpEntity < > (newEmployee, httpHeaders);// NO SE SI envia o recoje, probar
        return restTemplate.exchange(URL CON EL PUERTO 8080 DE BACKEMPRESA, HttpMethod.POST, entity, JwtResponse.class);
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
