package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
}
