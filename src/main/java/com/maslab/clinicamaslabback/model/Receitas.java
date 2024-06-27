package com.maslab.clinicamaslabback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Descricao")
    private String descricao;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    @JsonBackReference("receitasReference")
    private Medico medico;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    @JsonBackReference("receitasReference")
    private Paciente paciente;
    
    
}
