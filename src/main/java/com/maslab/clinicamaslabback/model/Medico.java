package com.maslab.clinicamaslabback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Medico extends Usuario {
    private String crm;
    
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
}
