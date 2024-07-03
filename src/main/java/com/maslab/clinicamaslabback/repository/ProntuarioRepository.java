package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Prontuario;

public interface ProntuarioRepository extends JpaRepository <Prontuario, Long>{
    
}
