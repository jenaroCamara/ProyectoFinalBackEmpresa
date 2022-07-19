package com.example.BackEmpresa.reserva.infraestructure.repository;

import com.example.BackEmpresa.reserva.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
