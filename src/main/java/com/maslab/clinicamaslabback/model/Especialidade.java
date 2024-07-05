package com.maslab.clinicamaslabback.model;


public enum Especialidade {
    CARDIOLOGIA("cardiologia"),
    ORTOPEDIA("ortopedia"),
    OFTAMOLOGIA("oftalmologia");

    private String descricao;

    Especialidade(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

}
