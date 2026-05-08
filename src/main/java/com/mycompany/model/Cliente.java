/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Cliente {
    private int usuarioId;
    private float rentaMaxima;

    // Constructor vacío
    public Cliente() {}

    // Constructor con todos los atributos
    public Cliente(int usuarioId, float rentaMaxima) {
        this.usuarioId = usuarioId;
        this.rentaMaxima = rentaMaxima;
    }

    // Getters y Setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public float getRentaMaxima() {
        return rentaMaxima;
    }

    public void setRentaMaxima(float rentaMaxima) {
        this.rentaMaxima = rentaMaxima;
    }
}

