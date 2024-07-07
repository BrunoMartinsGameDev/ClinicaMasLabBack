package com.maslab.clinicamaslabback.model.user;

public enum UserRole {
    ADMIN("admin"),
    PACIENTE("paciente"),
    MEDICO("médico");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
