/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


import java.util.Date;

public class ListaVisita {
    private int visitaId;
    private int clienteId;
    private Date fechaCreacion;

    // Constructor vacío
    public ListaVisita() {}

    // Constructor con todos los atributos
    public ListaVisita(int visitaId, int clienteId, Date fechaCreacion) {
        this.visitaId = visitaId;
        this.clienteId = clienteId;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public int getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(int visitaId) {
        this.visitaId = visitaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

