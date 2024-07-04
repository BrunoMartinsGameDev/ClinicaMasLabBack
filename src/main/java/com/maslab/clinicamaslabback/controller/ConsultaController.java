package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Consulta;
import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.repository.ConsultaRepository;
import com.maslab.clinicamaslabback.repository.MedicoRepository;
import com.maslab.clinicamaslabback.repository.PacienteRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;


    @GetMapping
    public List<Consulta> GetAllConsultas() {
        return consultaRepository.findAll();
    }

    @PostMapping
    public Consulta CreateConsulta(@RequestBody Consulta consulta) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(consulta.getPaciente().getId());
        Optional<Medico> optionalMedico = medicoRepository.findById(consulta.getMedico().getId());

        if (optionalPaciente.isPresent() && optionalMedico.isPresent()) {
            
            consulta.setPaciente(optionalPaciente.get());
            consulta.setMedico(optionalMedico.get());

            consulta.getPaciente().getConsultas().add(consulta);
            consulta.getMedico().getConsultas().add(consulta);
        } else {
            throw new IndexOutOfBoundsException("Paciente ou médico não cadastrados");
        }

        return consultaRepository.save(consulta);
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
