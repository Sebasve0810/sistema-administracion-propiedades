/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class ListaVisitaPropiedad {
    private int visitaId;
    private int propiedadId;

    // Constructor vacío
    public ListaVisitaPropiedad() {}

    // Constructor con todos los atributos
    public ListaVisitaPropiedad(int visitaId, int propiedadId) {
        this.visitaId = visitaId;
        this.propiedadId = propiedadId;
    }

    // Getters y Setters
    public int getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(int visitaId) {
        this.visitaId = visitaId;
    }

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }
}

