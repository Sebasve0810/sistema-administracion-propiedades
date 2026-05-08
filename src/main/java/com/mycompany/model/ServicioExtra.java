/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class ServicioExtra {
    private int servicioId;
    private String nombre;
    private float costoXinquilino;

    // Constructor vacío
    public ServicioExtra() {}

    // Constructor con todos los atributos
    public ServicioExtra(int servicioId, String nombre, float costoXinquilino) {
        this.servicioId = servicioId;
        this.nombre = nombre;
        this.costoXinquilino = costoXinquilino;
    }

    // Getters y Setters
    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCostoXinquilino() {
        return costoXinquilino;
    }

    public void setCostoXinquilino(float costoXinquilino) {
        this.costoXinquilino = costoXinquilino;
    }
}

