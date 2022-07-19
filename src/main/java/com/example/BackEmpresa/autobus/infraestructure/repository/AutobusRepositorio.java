package com.example.BackEmpresa.autobus.infraestructure.repository;

import com.example.BackEmpresa.autobus.domain.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutobusRepositorio extends JpaRepository<Autobus,Integer> {
}
