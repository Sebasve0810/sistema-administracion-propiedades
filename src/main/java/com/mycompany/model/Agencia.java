/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Agencia {
    private int agenciaId;
    private String nombre;
    private String direccion;

    // Constructor vacío
    public Agencia() {}

    // Constructor con todos los atributos
    public Agencia(int agenciaId, String nombre, String direccion) {
        this.agenciaId = agenciaId;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

