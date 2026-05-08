/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


import java.util.Date;

public class Visita {
    private int visitaId;
    private int clienteId;
    private int propiedadId;
    private Date fecha;
    private String comentario;

    // Constructor vacío
    public Visita() {}

    // Constructor con todos los atributos
    public Visita(int visitaId, int clienteId, int propiedadId, Date fecha, String comentario) {
        this.visitaId = visitaId;
        this.clienteId = clienteId;
        this.propiedadId = propiedadId;
        this.fecha = fecha;
        this.comentario = comentario;
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

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

