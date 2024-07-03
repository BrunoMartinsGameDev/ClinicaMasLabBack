package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Paciente;

public interface PacienteRepository extends JpaRepository <Paciente, Long>{
    
}
