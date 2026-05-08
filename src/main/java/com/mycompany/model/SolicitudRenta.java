/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


import java.util.Date;

public class SolicitudRenta {
    private int solicitudId;
    private int clienteId;
    private int propiedadId;
    private Date fechaSolicitud;
    private String estado;
    private float valorDueno;
    private float valorRentaTot;
    private float valorTotPagar;

    // Constructor vacío
    public SolicitudRenta() {}

    // Constructor con todos los atributos
    public SolicitudRenta(int solicitudId, int clienteId, int propiedadId, Date fechaSolicitud, String estado, float valorDueno, float valorRentaTot, float valorTotPagar) {
        this.solicitudId = solicitudId;
        this.clienteId = clienteId;
        this.propiedadId = propiedadId;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.valorDueno = valorDueno;
        this.valorRentaTot = valorRentaTot;
        this.valorTotPagar = valorTotPagar;
    }

    // Getters y Setters
    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getValorDueno() {
        return valorDueno;
    }

    public void setValorDueno(float valorDueno) {
        this.valorDueno = valorDueno;
    }

    public float getValorRentaTot() {
        return valorRentaTot;
    }

    public void setValorRentaTot(float valorRentaTot) {
        this.valorRentaTot = valorRentaTot;
    }

    public float getValorTotPagar() {
        return valorTotPagar;
    }

    public void setValorTotPagar(float valorTotPagar) {
        this.valorTotPagar = valorTotPagar;
    }
}

