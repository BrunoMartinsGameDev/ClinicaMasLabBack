package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.repository.PacienteRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Paciente")
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Paciente> getAllPacientes(@RequestBody Paciente paciente) {
        return pacienteRepository.findAll();
    }

    @GetMapping("/{nome}")
    public List<Paciente> getPacientesByNome(@PathVariable String nome) {

        List<Paciente> pacientes = pacienteRepository.findByNome(nome);
        return pacientes;
    }

    @PutMapping("/{id}")
    public Paciente putPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        
        Optional<Paciente> pacienteData = pacienteRepository.findById(id);

        if (pacienteData.isPresent()){
            Paciente _paciente = pacienteData.get();
            BeanUtils.copyProperties(paciente, _paciente, "id");
            return pacienteRepository.save(_paciente);
        } else {
            throw new IndexOutOfBoundsException("Médico não cadastrado");
        }
    }
    
    //Sem delete
    
}
