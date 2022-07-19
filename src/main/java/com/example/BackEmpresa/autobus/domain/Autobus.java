package com.example.BackEmpresa.autobus.domain;

import com.example.BackEmpresa.reserva.domain.Reserva;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Autobus {

    @Id
    @GeneratedValue
    int id;
    @Column
    String origen;
    @Column
    String destino;
    @Column
    String horaSalida;
    @Column
    int asientosOcupados;
    @Column
    Date fecha;
    @OneToMany
    List<Reserva> reservas;
    @Column
    int asientosTotales;
    private static final List<Integer> horasActuales = new ArrayList<Integer>();
    private static final List<String> ciudadesDestino = new ArrayList<String>();
    private static final List<String> cidudadesOrigen = new ArrayList<String>();

    public Autobus(String origen, String destino, String horaSalida, int asientosOcupados, Date fecha, int asientosTotales) throws Exception {
        inicializarConstantes();
        if(!ciudadesDestino.contains(destino) && !cidudadesOrigen.contains(origen) && !horasActuales.contains(horaSalida)){
            throw new Exception();//indicar luego las excepciones
        }
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.asientosOcupados = asientosOcupados;
        this.fecha = fecha;
        this.reservas = new ArrayList<Reserva>();
        this.asientosTotales = asientosTotales;
    }


    public void inicializarConstantes(){
        ciudadesDestino.add("Valencia"); ciudadesDestino.add("Madrid");
        ciudadesDestino.add("Barcelona"); ciudadesDestino.add("Bilbao");
        cidudadesOrigen.add("Vitorcia");
        horasActuales.add(8); horasActuales.add(12);
        horasActuales.add(16); horasActuales.add(20);
    }

    //Tengo que hacer constructor y en caso de que se vaya fuera de · Valencia Madrid Barcelona Bilbao. ->excepcion
    //en el constructor, debo de controlar las horas y el origen
}
