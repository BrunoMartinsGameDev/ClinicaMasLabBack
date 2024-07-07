package com.maslab.clinicamaslabback.dtos;

import com.maslab.clinicamaslabback.model.Especialidade;
import com.maslab.clinicamaslabback.model.user.UserRole;

public record MedicoCreateDTO(
    String login,
    String senha,
    String crm,
    UserRole role
    ) {

}
