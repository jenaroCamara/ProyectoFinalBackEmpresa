package com.example.BackEmpresa.principalService;

import com.example.BackEmpresa.autobus.application.AutobusUseCase;
import com.example.BackEmpresa.autobus.domain.Autobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.output.OutputDTOAutobus;
import com.example.BackEmpresa.autobus.infraestructure.repository.AutobusRepositorio;
import com.example.BackEmpresa.reserva.application.ReservaUseCase;
import com.example.BackEmpresa.reserva.domain.Reserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.InputDTOReserva;
import com.example.BackEmpresa.reserva.infraestructure.dto.OutputDTOReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class PrincipalService {
    @Autowired
    AutobusRepositorio autobusRepositorio;
    @Autowired
    AutobusUseCase autobusServicio;
    @Autowired
    private JavaMailSender mailSender;


    public List<OutputDTOAutobus> obtenerAutobus(String fecha, String destino, String hora) {
        return autobusServicio.getDataForCriteria(destino, fecha, hora);
    }

    public void anadirReservaEnBus(Reserva r, int id) throws Exception {
        Autobus autobus = autobusRepositorio.findById(id).orElseThrow(() -> new Exception("autobus no encontrado"));
        autobus.getReservas().add(r);
        autobus.setAsientosOcupados(autobus.getAsientosOcupados() + 1);
        autobusRepositorio.save(autobus);
    }

    public void sendEmail(String Destinatario, String asunto, String contenido) {

        SimpleMailMessage email = new SimpleMailMessage();
        try {
            email.setTo(Destinatario);
            email.setSubject(asunto);
            email.setText(contenido);

            mailSender.send(email);
        } catch (Exception e) {
            System.out.println("No se ha podido enviar el email");
        }
    }


//    public OutputDTOReserva realizarReserva(InputDTOReserva reserva) throws Exception {
//        return reservaService.realizarReserva(reserva);
//    }

}