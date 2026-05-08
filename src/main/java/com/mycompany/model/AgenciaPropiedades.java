/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class AgenciaPropiedades {
    private int agenciaId;
    private int propiedadId;

    // Constructor vacío
    public AgenciaPropiedades() {}

    // Constructor con todos los atributos
    public AgenciaPropiedades(int agenciaId, int propiedadId) {
        this.agenciaId = agenciaId;
        this.propiedadId = propiedadId;
    }

    // Getters y Setters
    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }
}

