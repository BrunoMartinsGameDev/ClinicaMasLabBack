package com.maslab.clinicamaslabback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.model.user.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
        UserDetails findByLogin(String login);

        Optional<Paciente> findByIdAndIsMedicoFalse(Long id);
        Optional<Medico> findByIdAndIsMedicoTrue(Long id);

        List<Paciente> findAllByIsMedicoFalse();
        List<Medico> findAllByIsMedicoTrue();

        List<Medico> findByNomeAndIsMedicoTrue(String login);
        List<Paciente> findByNomeAndIsMedicoFalse(String login);

}
