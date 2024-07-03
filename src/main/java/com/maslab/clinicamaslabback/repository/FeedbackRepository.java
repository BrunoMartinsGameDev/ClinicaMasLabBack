package com.maslab.clinicamaslabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maslab.clinicamaslabback.model.Feedback;

public interface FeedbackRepository extends JpaRepository <Feedback, Long>{
    
}
