package com.example.BackEmpresa.customError;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class customError {
    Date timestamp;
    int HttpCode;
    String mensaje; // Devolverá el mensaje de error.

    public customError(Date timestamp, String message, int HttpCode) {
        super();
        this.timestamp = timestamp;
        this.mensaje = message;
        this.HttpCode = HttpCode;
    }
}
