package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.dtos.MedicoCreateDTO;
import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Medico")
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private UsuarioRepository medicoRepository;
    

    @GetMapping
    public List<Medico> GetAllMedicos() {
        return medicoRepository.findAllByIsMedicoTrue();
    }

    @PostMapping
    public Medico CreateMedico(@RequestBody MedicoCreateDTO medicoDTO) {
        System.out.println(medicoDTO);
        Medico medico = new Medico();
        BeanUtils.copyProperties(medicoDTO, medico, "id");
        String encryptedPassword = new BCryptPasswordEncoder().encode(medico.getSenha());
        medico.setSenha(encryptedPassword);
        return medicoRepository.save(medico);
    }


    @PutMapping("/{id}")
    public Medico putMedico(@PathVariable Long id, @RequestBody Medico medico) {
        
        Optional<Medico> medicoData = medicoRepository.findByIdAndIsMedicoTrue(id);

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
