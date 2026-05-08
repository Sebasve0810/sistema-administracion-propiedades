/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

/**
 *
 * @author veseb
 */
import com.mycompany.dao.DueñoDAO;
import com.mycompany.dao.PropiedadDAO;
import com.mycompany.dao.UbicacionDAO;
import com.mycompany.model.Dueño;
import com.mycompany.model.Propiedad;
import com.mycompany.model.Ubicacion;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PropiedadController {
    private PropiedadDAO propiedadDAO;
    private DueñoDAO dueñoDAO;
    private UbicacionDAO ubicacionDAO;

    // Constructor
    public PropiedadController(PropiedadDAO propiedadDAO, DueñoDAO dueñoDAO, UbicacionDAO ubicacionDAO) {
        this.propiedadDAO = propiedadDAO;
        this.dueñoDAO = dueñoDAO;
        this.ubicacionDAO = ubicacionDAO;
    }

    // Método para verificar si el usuario es un dueño registrado
    public boolean esDueño(Connection conn, int usuarioId) {
        try {
            Dueño dueño = dueñoDAO.obtenerPorId(conn, usuarioId);
            return dueño != null;
        } catch (SQLException e) {
            System.out.println("Error al verificar dueño: " + e.getMessage());
            return false;
        }
    }

    // Método para agregar una nueva propiedad
    public boolean agregarPropiedad(Connection conn ,int usuarioId, String tipo, String direccion, int ubicacionId) {
        try {
            if (!esDueño(conn, usuarioId)) {
                System.out.println("El usuario no es dueño registrado.");
                return false;
            }

            Propiedad nuevaPropiedad = new Propiedad( 10,tipo, direccion, ubicacionId); // ID generado automáticamente
            return propiedadDAO.insertar(conn, nuevaPropiedad);
        } catch (SQLException e) {
            System.out.println("Error al registrar propiedad: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar propiedades por tipo
    public List<Propiedad> buscarPorTipo(Connection conn, String tipo) {
        try {
            return propiedadDAO.listar(conn).stream()
                .filter(prop -> prop.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println("Error al buscar propiedades por tipo: " + e.getMessage());
            return null;
        }
    }

    // Método para buscar propiedades por ubicación
    public List<Propiedad> buscarPorUbicacion(Connection conn, String municipio) {
        try {
            List<Ubicacion> ubicaciones = ubicacionDAO.listar(conn).stream()
                .filter(ubi -> ubi.getMunicipio().equalsIgnoreCase(municipio))
                .collect(Collectors.toList());

            return propiedadDAO.listar(conn).stream()
                .filter(prop -> ubicaciones.stream()
                    .anyMatch(ubi -> ubi.getUbicacionId() == prop.getUbicacionId()))
                .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println("Error al buscar propiedades por ubicación: " + e.getMessage());
            return null;
        }
    }

    // Método para listar todas las propiedades
    public List<Propiedad> listarPropiedades(Connection conn) {
        try {
            return propiedadDAO.listar(conn);
        } catch (SQLException e) {
            System.out.println("Error al listar propiedades: " + e.getMessage());
            return null;
        }
    }
    
    
    // Método para obtener información detallada de una propiedad por ID
    public String verPropiedad(Connection conn, int propiedadId) {
        try {
            Propiedad propiedad = propiedadDAO.obtenerPorId(conn, propiedadId);
            if (propiedad == null) {
                return "Propiedad no encontrada.";
            }

            // Obtener ubicación
            Ubicacion ubicacion = ubicacionDAO.obtenerPorId(conn, propiedad.getUbicacionId());
            String ubicacionStr = (ubicacion != null) ? 
                ubicacion.getMunicipio() + ", " + ubicacion.getDepartamento() + ", " + ubicacion.getPais() : 
                "Ubicación desconocida";

            // Verificar si tiene dueño
            Dueño dueño = dueñoDAO.obtenerPorId(conn, propiedadId);
            String dueñoStr = (dueño != null) ? "Propiedad de dueño registrado" : "Dueño desconocido";

            // Formatear información detallada
            return "🏡 **Propiedad ID:** " + propiedad.getPropiedadId() +
                   "\n🔹 **Tipo:** " + propiedad.getTipo() +
                   "\n📍 **Ubicación:** " + ubicacionStr +
                   "\n📌 **Dirección:** " + propiedad.getDireccion() +
                   "\n👤 **Dueño:** " + dueñoStr;
        } catch (SQLException e) {
            return "Error al obtener detalles de la propiedad: " + e.getMessage();
        }
    }
    
    
}


