/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.ListaVisitaPropiedad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaVisitaPropiedadDAO {

    // Obtener por ID de visita
    public ListaVisitaPropiedad obtenerPorId(Connection conn, int visitaId) throws SQLException {
        String query = "SELECT * FROM lista_visita_propiedad WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visitaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ListaVisitaPropiedad(rs.getInt("visitaId"), rs.getInt("propiedadId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, ListaVisitaPropiedad listaVisitaPropiedad) throws SQLException {
        String query = "INSERT INTO lista_visita_propiedad (visitaId, propiedadId) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, listaVisitaPropiedad.getVisitaId());
            stmt.setInt(2, listaVisitaPropiedad.getPropiedadId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, ListaVisitaPropiedad listaVisitaPropiedad) throws SQLException {
        String query = "UPDATE lista_visita_propiedad SET propiedadId = ? WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, listaVisitaPropiedad.getPropiedadId());
            stmt.setInt(2, listaVisitaPropiedad.getVisitaId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int visitaId) throws SQLException {
        String query = "DELETE FROM lista_visita_propiedad WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visitaId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<ListaVisitaPropiedad> listar(Connection conn) throws SQLException {
        List<ListaVisitaPropiedad> lista = new ArrayList<>();
        String query = "SELECT * FROM lista_visita_propiedad";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new ListaVisitaPropiedad(rs.getInt("visitaId"), rs.getInt("propiedadId")));
            }
        }
        return lista;
    }
}

