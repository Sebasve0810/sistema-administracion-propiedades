/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Impuesto {
    private int impuestoId;
    private String tipo;
    private float porcentaje;

    // Constructor vacío
    public Impuesto() {}

    // Constructor con todos los atributos
    public Impuesto(int impuestoId, String tipo, float porcentaje) {
        this.impuestoId = impuestoId;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
    }

    // Getters y Setters
    public int getImpuestoId() {
        return impuestoId;
    }

    public void setImpuestoId(int impuestoId) {
        this.impuestoId = impuestoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }
}

