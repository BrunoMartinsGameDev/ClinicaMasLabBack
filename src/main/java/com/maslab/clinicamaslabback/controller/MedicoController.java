package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.repository.MedicoRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Medico")
@RestController
@RequestMapping("/Medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    

    @GetMapping
    public List<Medico> GetAllMedicos() {
        return medicoRepository.findAll();
    }

    @PostMapping
    public Medico CreateMedico(@RequestBody Medico medico) {
        return medicoRepository.save(medico);
    }


    @PutMapping("/{id}")
    public Medico putMedico(@PathVariable Long id, @RequestBody Medico medico) {
        
        Optional<Medico> medicoData = medicoRepository.findById(id);

        if (medicoData.isPresent()){
            Medico _medico = medicoData.get();
            BeanUtils.copyProperties(medico, _medico, "id");
            return medicoRepository.save(_medico);
        } else {
            throw new IndexOutOfBoundsException("Médico não cadastrado");
        }
    }

    //Sem delete
    
}
