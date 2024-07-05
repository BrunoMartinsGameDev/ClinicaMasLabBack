package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Feedback;
import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.repository.FeedbackRepository;
import com.maslab.clinicamaslabback.repository.MedicoRepository;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Feedback")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private MedicoRepository medicoRepository;


    @GetMapping
    public List<Feedback> GetAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @GetMapping("/id")
    public Set<Feedback> getFeedbacksByMedicoID(@PathVariable Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);

        if (optionalMedico.isPresent()) {
            
            Medico medico = optionalMedico.get();

            return medico.getFeedbacks();

        } else {
            throw new IndexOutOfBoundsException("Médico não cadastrado");
        }
    }
    

    @PostMapping
    public Feedback CreateFeedback(@RequestBody Feedback feedback) {
        
        Optional<Medico> optionalMedico = medicoRepository.findById(feedback.getMedico().getId());

        if (optionalMedico.isPresent()) {
            
            feedback.setMedico(optionalMedico.get());

            feedback.getMedico().getFeedbacks().add(feedback);
        } else {
            throw new IndexOutOfBoundsException("Médico não cadastrado");
        }

        return feedbackRepository.save(feedback);
    }

    //Acredito que feedback não deva ter update nem delete
}
