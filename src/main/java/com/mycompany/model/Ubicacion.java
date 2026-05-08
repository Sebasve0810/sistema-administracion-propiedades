/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;


public class Ubicacion {
    private int ubicacionId;
    private String pais;
    private String departamento;
    private String municipio;
    private String zona;

    // Constructor vacío
    public Ubicacion() {}

    // Constructor con todos los atributos
    public Ubicacion(int ubicacionId, String pais, String departamento, String municipio, String zona) {
        this.ubicacionId = ubicacionId;
        this.pais = pais;
        this.departamento = departamento;
        this.municipio = municipio;
        this.zona = zona;
    }

    // Getters y Setters
    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}

