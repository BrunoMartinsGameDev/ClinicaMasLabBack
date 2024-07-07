package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.model.Prontuario;
import com.maslab.clinicamaslabback.repository.ProntuarioRepository;
import com.maslab.clinicamaslabback.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Prontuario")
@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {
    
    @Autowired
    private ProntuarioRepository prontuarioRepository;
    @Autowired
    private UsuarioRepository pacienteRepository;


    @GetMapping
    public List<Prontuario> GetAllProntuarios() {
        return prontuarioRepository.findAll();
    }

    @GetMapping("/id")
    public Prontuario getProntuariosByPacienteID(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findByIdAndIsMedicoFalse(id);

        if (optionalPaciente.isPresent()) {
            
            Paciente paciente = optionalPaciente.get();

            return paciente.getProntuario();

        } else {
            throw new IndexOutOfBoundsException("Paciente não cadastrado");
        }
    }

    @PostMapping
        public Prontuario CreateProntuario(@RequestBody Prontuario prontuario) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findByIdAndIsMedicoFalse(prontuario.getPaciente().getId());

        if (optionalPaciente.isPresent()) {
            
            prontuario.setPaciente(optionalPaciente.get());

            prontuario.getPaciente().setProntuario(prontuario);
        } else {
            throw new IndexOutOfBoundsException("Paciente não cadastrado");
        }

        return prontuarioRepository.save(prontuario);
    }

    @PutMapping("/{id}")
    public Prontuario putProntuario(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        
        Optional<Prontuario> prontuarioData = prontuarioRepository.findById(id);

        if (prontuarioData.isPresent()){
            Prontuario _prontuario = prontuarioData.get();
            BeanUtils.copyProperties(prontuario, _prontuario, "id");
            return prontuarioRepository.save(_prontuario);
        } else {
            throw new IndexOutOfBoundsException("Prontuario não cadastrado");
        }
    }

    //Sem delete

}
