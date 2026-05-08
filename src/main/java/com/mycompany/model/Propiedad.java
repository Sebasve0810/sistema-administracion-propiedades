/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Propiedad {
    private int propiedadId;
    private String tipo;
    private String direccion;
    private int ubicacionId;

    // Constructor vacío
    public Propiedad() {}

    // Constructor con todos los atributos
    public Propiedad(int propiedadId, String tipo, String direccion, int ubicacionId) {
        this.propiedadId = propiedadId;
        this.tipo = tipo;
        this.direccion = direccion;
        this.ubicacionId = ubicacionId;
    }

    // Getters y Setters
    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }
}

