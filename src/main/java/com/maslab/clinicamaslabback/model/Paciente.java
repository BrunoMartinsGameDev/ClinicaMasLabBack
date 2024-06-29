package com.maslab.clinicamaslabback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Paciente extends Usuario {
    private String cpf;

    @OneToOne
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

}
