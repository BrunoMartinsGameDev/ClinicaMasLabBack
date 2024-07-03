package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Prescricao;

public interface PrescricaoRepository extends JpaRepository <Prescricao, Long>{

    
}