package com.maslab.clinicamaslabback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maslab.clinicamaslabback.model.Prescricao;
import com.maslab.clinicamaslabback.repository.PrescricaoRepository;

import java.util.List;

@Service
public class PrescricaoService {
    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public List<Prescricao> findAll() {
        return prescricaoRepository.findAll();
    }

    public Prescricao findById(Long id) {
        return prescricaoRepository.findById(id).orElse(null);
    }

    public Prescricao save(Prescricao prescricao) {
        return prescricaoRepository.save(prescricao);
    }

    public void deleteById(Long id) {
        prescricaoRepository.deleteById(id);
    }
}
