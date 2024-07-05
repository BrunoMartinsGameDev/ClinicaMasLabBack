package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.model.Prescricao;
import com.maslab.clinicamaslabback.repository.MedicoRepository;
import com.maslab.clinicamaslabback.repository.PacienteRepository;
import com.maslab.clinicamaslabback.repository.PrescricaoRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Prescricao")
@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {
    
    @Autowired
    private PrescricaoRepository prescricaoRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;


    @GetMapping
    public List<Prescricao> GetAllPrescricoes() {
        return prescricaoRepository.findAll();
    }

    @GetMapping("/id")
    public Set<Prescricao> getPrescricoesByPacienteID(@PathVariable Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isPresent()) {
            
            Paciente paciente = optionalPaciente.get();

            return paciente.getPrescricoes();

        } else {
            throw new IndexOutOfBoundsException("Paciente não cadastrado");
        }
    }

    @PostMapping
    public Prescricao CreatePrescricao(@RequestBody Prescricao prescricao) {
        
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(prescricao.getPaciente().getId());
        Optional<Medico> optionalMedico = medicoRepository.findById(prescricao.getMedico().getId());

        if (optionalPaciente.isPresent() && optionalMedico.isPresent()) {
            
            prescricao.setPaciente(optionalPaciente.get());
            prescricao.setMedico(optionalMedico.get());

            prescricao.getPaciente().getPrescricoes().add(prescricao);
            prescricao.getMedico().getPrescricoes().add(prescricao);
        } else {
            throw new IndexOutOfBoundsException("Paciente ou médico não cadastrados");
        }

        return prescricaoRepository.save(prescricao);
    }
    

    //Sem update nem delete
}
