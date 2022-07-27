package com.example.BackEmpresa.reserva.domain;

import com.example.BackEmpresa.StringPrefixedSequenceIdGenerator.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ReservaIdGen")
    @GenericGenerator(
            name = "ReservaIdGen",
            strategy = "com.example.BackEmpresa.StringPrefixedSequenceIdGenerator.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value =
                            "RSV"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value =
                            "%08d")
            })
    String id_r;
    @Column
    Date fecha;
    @Column
    String hora;
    @Column
    String destino;

    public Reserva( String id_r, Date fecha, String hora, String destino){
        this.id_r=id_r;
        this.fecha=fecha;
        this.hora = hora;
        this.destino = destino;
    }
    @Override
    public String toString() {
        return "Reserva{" +
                "id_r=" + id_r +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
    public String toStringtwo() {
        return id_r + "," +
                fecha +"," +
                hora + "," +
                destino;
    }

    public Reserva (String cad) throws ParseException {
        String[] arrOfStr = cad.split(",");
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = dateFormat.parse(arrOfStr[1]);

        this.id_r=(arrOfStr[0]);
        this.fecha = d;
        this.hora = arrOfStr[2];
        this.destino = arrOfStr[3];
    }
}
