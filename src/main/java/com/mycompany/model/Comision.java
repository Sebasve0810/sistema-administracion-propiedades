/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Comision {
    private int comisionId;
    private String tipo;
    private float porcentaje;

    // Constructor vacío
    public Comision() {}

    // Constructor con todos los atributos
    public Comision(int comisionId, String tipo, float porcentaje) {
        this.comisionId = comisionId;
        this.tipo = tipo;
        this.porcentaje = porcentaje;
    }

    // Getters y Setters
    public int getComisionId() {
        return comisionId;
    }

    public void setComisionId(int comisionId) {
        this.comisionId = comisionId;
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

