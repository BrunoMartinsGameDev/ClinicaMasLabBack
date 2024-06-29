package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
