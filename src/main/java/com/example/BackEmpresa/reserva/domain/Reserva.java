package com.example.BackEmpresa.reserva.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Reserva {

    @Id
    @GeneratedValue
    int id_r;
    @Column
    Date fecha;
    @Column
    String hora;
    @Column
    String destino;

    @Override
    public String toString() {
        return "Reserva{" +
                "id_r=" + id_r +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}
