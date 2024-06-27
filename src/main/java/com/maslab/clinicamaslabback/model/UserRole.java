package com.maslab.clinicamaslabback.model;

public class UserRole {
    
    ADMIN("Medico"),
    USER("Paciente");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
