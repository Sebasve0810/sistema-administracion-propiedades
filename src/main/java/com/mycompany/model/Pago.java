/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Pago {
    private int pagoId;
    private int solicitudId;
    private String tipoPago;
    private float monto;
    private String detalleAutorizacion;
    private String numBono;

    // Constructor vacío
    public Pago() {}

    // Constructor con todos los atributos
    public Pago(int pagoId, int solicitudId, String tipoPago, float monto, String detalleAutorizacion, String numBono) {
        this.pagoId = pagoId;
        this.solicitudId = solicitudId;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.detalleAutorizacion = detalleAutorizacion;
        this.numBono = numBono;
    }

    // Getters y Setters
    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }

    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getDetalleAutorizacion() {
        return detalleAutorizacion;
    }

    public void setDetalleAutorizacion(String detalleAutorizacion) {
        this.detalleAutorizacion = detalleAutorizacion;
    }

    public String getNumBono() {
        return numBono;
    }

    public void setNumBono(String numBono) {
        this.numBono = numBono;
    }
}

