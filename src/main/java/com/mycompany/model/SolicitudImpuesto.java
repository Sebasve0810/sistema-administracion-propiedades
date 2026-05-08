/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class SolicitudImpuesto {
    private int solicitudId;
    private int impuestoId;

    // Constructor vacío
    public SolicitudImpuesto() {}

    // Constructor con todos los atributos
    public SolicitudImpuesto(int solicitudId, int impuestoId) {
        this.solicitudId = solicitudId;
        this.impuestoId = impuestoId;
    }

    // Getters y Setters
    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public int getImpuestoId() {
        return impuestoId;
    }

    public void setImpuestoId(int impuestoId) {
        this.impuestoId = impuestoId;
    }
}
