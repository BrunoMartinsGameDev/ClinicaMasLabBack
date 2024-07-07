package com.maslab.clinicamaslabback.model;

import java.util.Set;

import com.maslab.clinicamaslabback.model.user.UserRole;
import com.maslab.clinicamaslabback.model.user.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("MEDICO")
public class Medico extends Usuario{

    public Medico(String login, String senha, UserRole role){
        super(login, senha, role);
    }
    public Medico(){
        super();
    }
    
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Prescricao> prescricoes;

}
