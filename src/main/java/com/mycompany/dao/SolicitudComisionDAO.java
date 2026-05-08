/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.SolicitudComision;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudComisionDAO {

    // Obtener por ID de solicitud
    public SolicitudComision obtenerPorId(Connection conn, int solicitudId) throws SQLException {
        String query = "SELECT * FROM solicitud_comision WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SolicitudComision(rs.getInt("solicitudId"), rs.getInt("comisionId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, SolicitudComision solicitudComision) throws SQLException {
        String query = "INSERT INTO solicitud_comision (solicitudId, comisionId) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudComision.getSolicitudId());
            stmt.setInt(2, solicitudComision.getComisionId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, SolicitudComision solicitudComision) throws SQLException {
        String query = "UPDATE solicitud_comision SET comisionId = ? WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudComision.getComisionId());
            stmt.setInt(2, solicitudComision.getSolicitudId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int solicitudId) throws SQLException {
        String query = "DELETE FROM solicitud_comision WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<SolicitudComision> listar(Connection conn) throws SQLException {
        List<SolicitudComision> lista = new ArrayList<>();
        String query = "SELECT * FROM solicitud_comision";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new SolicitudComision(rs.getInt("solicitudId"), rs.getInt("comisionId")));
            }
        }
        return lista;
    }
}

