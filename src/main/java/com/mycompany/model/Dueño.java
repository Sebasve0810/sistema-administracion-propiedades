/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Dueño {
    private int usuarioId;
    private String tipoDoc;
    private String numDoc;

    // Constructor vacío
    public Dueño() {}

    // Constructor con todos los atributos
    public Dueño(int usuarioId, String tipoDoc, String numDoc) {
        this.usuarioId = usuarioId;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
    }

    // Getters y Setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
}

