package com.maslab.clinicamaslabback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Receita;
import com.maslab.clinicamaslabback.repository.ReceitaRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Receita")
@RestController
@RequestMapping("/receita")
public class ReceitaController {

        
    @Autowired
    private ReceitaRepository receitaRepository;


    @GetMapping
    public List<Receita> GetAllPrescricoes() {
        return receitaRepository.findAll();
    }

    // @GetMapping("/id")
    // public Set<Receita> getReceitasByPacienteID(@PathVariable Long id) {
    //     Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

    //     if (optionalPaciente.isPresent()) {
            
    //         Paciente paciente = optionalPaciente.get();

    //         return paciente.getReceitas();

    //     } else {
    //         throw new IndexOutOfBoundsException("Paciente não cadastrado");
    //     }
    // }

    // @PostMapping
    // public Receita CreateReceita(@RequestBody Receita receita) {
        
    //     Optional<Paciente> optionalPaciente = pacienteRepository.findById(receita.getPaciente().getId());
    //     Optional<Medico> optionalMedico = medicoRepository.findById(receita.getMedico().getId());

    //     if (optionalPaciente.isPresent() && optionalMedico.isPresent()) {
            
    //         receita.setPaciente(optionalPaciente.get());
    //         receita.setMedico(optionalMedico.get());

    //         receita.getPaciente().getReceitas().add(receita);
    //         receita.getMedico().getReceitas().add(receita);
    //     } else {
    //         throw new IndexOutOfBoundsException("Paciente ou médico não cadastrados");
    //     }

    //     return receitaRepository.save(receita);
    // }
    

    //Sem update nem delete
    
}
