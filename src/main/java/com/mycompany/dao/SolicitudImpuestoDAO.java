/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.SolicitudImpuesto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudImpuestoDAO {

    // Obtener por ID de solicitud
    public SolicitudImpuesto obtenerPorId(Connection conn, int solicitudId) throws SQLException {
        String query = "SELECT * FROM solicitud_impuesto WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SolicitudImpuesto(rs.getInt("solicitudId"), rs.getInt("impuestoId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, SolicitudImpuesto solicitudImpuesto) throws SQLException {
        String query = "INSERT INTO solicitud_impuesto (solicitudId, impuestoId) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudImpuesto.getSolicitudId());
            stmt.setInt(2, solicitudImpuesto.getImpuestoId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, SolicitudImpuesto solicitudImpuesto) throws SQLException {
        String query = "UPDATE solicitud_impuesto SET impuestoId = ? WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudImpuesto.getImpuestoId());
            stmt.setInt(2, solicitudImpuesto.getSolicitudId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int solicitudId) throws SQLException {
        String query = "DELETE FROM solicitud_impuesto WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<SolicitudImpuesto> listar(Connection conn) throws SQLException {
        List<SolicitudImpuesto> lista = new ArrayList<>();
        String query = "SELECT * FROM solicitud_impuesto";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new SolicitudImpuesto(rs.getInt("solicitudId"), rs.getInt("impuestoId")));
            }
        }
        return lista;
    }
}

