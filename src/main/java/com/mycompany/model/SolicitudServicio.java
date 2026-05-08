/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class SolicitudServicio {
    private int solicitudId;
    private int servicioId;
    private int numeroInquilinos;

    // Constructor vacío
    public SolicitudServicio() {}

    // Constructor con todos los atributos
    public SolicitudServicio(int solicitudId, int servicioId, int numeroInquilinos) {
        this.solicitudId = solicitudId;
        this.servicioId = servicioId;
        this.numeroInquilinos = numeroInquilinos;
    }

    // Getters y Setters
    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public int getNumeroInquilinos() {
        return numeroInquilinos;
    }

    public void setNumeroInquilinos(int numeroInquilinos) {
        this.numeroInquilinos = numeroInquilinos;
    }
}

