package com.maslab.clinicamaslabback.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
