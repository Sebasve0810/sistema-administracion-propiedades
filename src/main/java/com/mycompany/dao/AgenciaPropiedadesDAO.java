/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.AgenciaPropiedades;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaPropiedadesDAO {

    // Obtener por ID de agencia
    public AgenciaPropiedades obtenerPorId(Connection conn, int agenciaId) throws SQLException {
        String query = "SELECT * FROM agencia_propiedades WHERE agenciaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, agenciaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AgenciaPropiedades(rs.getInt("agenciaId"), rs.getInt("propiedadId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, AgenciaPropiedades agenciaPropiedades) throws SQLException {
        String query = "INSERT INTO agencia_propiedades (agenciaId, propiedadId) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, agenciaPropiedades.getAgenciaId());
            stmt.setInt(2, agenciaPropiedades.getPropiedadId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, AgenciaPropiedades agenciaPropiedades) throws SQLException {
        String query = "UPDATE agencia_propiedades SET propiedadId = ? WHERE agenciaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, agenciaPropiedades.getPropiedadId());
            stmt.setInt(2, agenciaPropiedades.getAgenciaId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int agenciaId) throws SQLException {
        String query = "DELETE FROM agencia_propiedades WHERE agenciaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, agenciaId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<AgenciaPropiedades> listar(Connection conn) throws SQLException {
        List<AgenciaPropiedades> lista = new ArrayList<>();
        String query = "SELECT * FROM agencia_propiedades";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new AgenciaPropiedades(rs.getInt("agenciaId"), rs.getInt("propiedadId")));
            }
        }
        return lista;
    }
}

