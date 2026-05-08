/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class SolicitudComision {
    private int solicitudId;
    private int comisionId;

    // Constructor vacío
    public SolicitudComision() {}

    // Constructor con todos los atributos
    public SolicitudComision(int solicitudId, int comisionId) {
        this.solicitudId = solicitudId;
        this.comisionId = comisionId;
    }

    // Getters y Setters
    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public int getComisionId() {
        return comisionId;
    }

    public void setComisionId(int comisionId) {
        this.comisionId = comisionId;
    }
}

