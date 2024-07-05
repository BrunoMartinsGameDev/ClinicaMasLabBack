package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Paciente;
import java.util.List;


public interface PacienteRepository extends JpaRepository <Paciente, Long>{

    List<Paciente> findByNome(String nome);
    
}
