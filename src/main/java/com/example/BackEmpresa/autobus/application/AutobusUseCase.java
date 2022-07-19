package com.example.BackEmpresa.autobus.application;

import com.example.BackEmpresa.autobus.application.port.AutobusUseCaseInterface;
import com.example.BackEmpresa.autobus.domain.Autobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.input.InputDTOAutobus;
import com.example.BackEmpresa.autobus.infraestructure.dto.output.OutputDTOAutobus;
import com.example.BackEmpresa.autobus.infraestructure.repository.AutobusRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutobusUseCase implements AutobusUseCaseInterface {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AutobusRepositorio autobusRepositorio;
    @PersistenceContext
    private EntityManager em;

    public List<OutputDTOAutobus> getAll() {
        //List<Autobus> lista = autobusRepositorio.findAll();
        return autobusRepositorio.findAll().stream().map(autobus -> modelMapper.map(autobus, OutputDTOAutobus.class))
                .collect(Collectors.toList());
    }

    public OutputDTOAutobus crear(InputDTOAutobus autobus) {
        return modelMapper.map(autobusRepositorio.save(modelMapper.map(autobus, Autobus.class)), OutputDTOAutobus.class);
    }

    public List<OutputDTOAutobus> getDataForCriteria(String destino, String fecha, String hora) {
        HashMap<String, Object> data = new HashMap<>();
        //en el hasmap comprobaré solo el nombre, pero podría añadir mas condiciones
        if (destino != null) {
            data.put("destino", destino);
        }
        if (fecha != null) {
            data.put("fecha", fecha);
            data.put("fecha2", fecha);
        }
        if (hora != null) {
            data.put("horaSalida", hora);
        }

        return obtenerAutobusFechaDestino(data);
    }

    public List<OutputDTOAutobus> obtenerAutobusFechaDestino(HashMap<String, Object> data) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Autobus> query = cb.createQuery(Autobus.class);
        Root<Autobus> root = query.from(Autobus.class);

        List<Predicate> predicates = new ArrayList<>();
        data.forEach((field, value) ->
        {
            switch (field) {
                case "destino"://el destino es un enum, le tengo que pasar un int
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "fecha":
                    LocalDate date = LocalDate.parse(value.toString());
                    date = date.minusDays(1);
                    Date fecha = java.sql.Date.valueOf(date);
                    predicates.add(cb.greaterThan(root.<Date>get(field), fecha));
                    break;
                case "fecha2":
                    LocalDate date2 = LocalDate.parse(value.toString());
                    date2 = date2.plusDays(1);
                    Date fecha2 = java.sql.Date.valueOf(date2);
                    predicates.add(cb.lessThan(root.<Date>get("fecha"), fecha2));
                    break;
                case "horaSalida":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
            }

        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        List<Autobus> autobuses = em.createQuery(query).getResultList();
        return autobuses.stream()
                .map(autobus -> modelMapper.map(autobus, OutputDTOAutobus.class))
                .collect(Collectors.toList());
    }

}
