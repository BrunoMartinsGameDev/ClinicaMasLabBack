package com.maslab.clinicamaslabback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maslab.clinicamaslabback.model.Prontuario;
import com.maslab.clinicamaslabback.repository.ProntuarioRepository;

import java.util.List;

@Service
public class ProntuarioService {
    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public List<Prontuario> findAll() {
        return prontuarioRepository.findAll();
    }

    public Prontuario findById(Long id) {
        return prontuarioRepository.findById(id).orElse(null);
    }

    public Prontuario save(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public void deleteById(Long id) {
        prontuarioRepository.deleteById(id);
    }
}
