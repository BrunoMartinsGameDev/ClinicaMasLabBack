package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Consulta;
import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.model.user.Usuario;
import com.maslab.clinicamaslabback.repository.ConsultaRepository;
import com.maslab.clinicamaslabback.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Consulta")
@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public List<Consulta> GetAllConsultas() {
        return consultaRepository.findAll();
    }

    @GetMapping("/findby-paciente-id")
    public Set<Consulta> getConsultasByPacienteID() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();
        Optional<Paciente> optionalPaciente = usuarioRepository.findByIdAndIsMedicoFalse(usuario.getId());
        if (optionalPaciente.isPresent()) {
            
            Paciente paciente = optionalPaciente.get();

            return paciente.getConsultas();

        } else {
            throw new IndexOutOfBoundsException("Paciente não cadastrado");
        }
    }


    @PostMapping
    public Consulta CreateConsulta(@RequestBody Consulta consulta) {

        Optional<Paciente> optionalPaciente = usuarioRepository.findByIdAndIsMedicoFalse(consulta.getPaciente().getId());
        Optional<Medico> optionalMedico = usuarioRepository.findByIdAndIsMedicoTrue(consulta.getMedico().getId());

        if (optionalPaciente.isPresent() && optionalMedico.isPresent()) {
            
            consulta.setPaciente(optionalPaciente.get());
            consulta.setMedico(optionalMedico.get());

        } else {
            throw new IndexOutOfBoundsException("Paciente ou médico não cadastrados");
        }

        return consultaRepository.save(consulta);
    }

    @PutMapping("/{id}")
    public Consulta putConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        
        Optional<Consulta> consultaData = consultaRepository.findById(id);

        if (consultaData.isPresent()){
            Consulta _consulta = consultaData.get();
            BeanUtils.copyProperties(consulta, _consulta, "id");
            return consultaRepository.save(_consulta);
        } else {
            throw new IndexOutOfBoundsException("Consulta não cadastrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteConsulta(@PathVariable Long id) {
        Optional<Consulta> _consulta = consultaRepository.findById(id);

        if (_consulta.isPresent()) {
            consultaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
