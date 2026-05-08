/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.SolicitudServicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudServicioDAO {

    // Obtener por ID de solicitud
    public SolicitudServicio obtenerPorId(Connection conn, int solicitudId) throws SQLException {
        String query = "SELECT * FROM solicitud_servicio WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SolicitudServicio(rs.getInt("solicitudId"), rs.getInt("servicioId"), rs.getInt("numeroInquilinos"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, SolicitudServicio solicitudServicio) throws SQLException {
        String query = "INSERT INTO solicitud_servicio (solicitudId, servicioId, numeroInquilinos) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudServicio.getSolicitudId());
            stmt.setInt(2, solicitudServicio.getServicioId());
            stmt.setInt(3, solicitudServicio.getNumeroInquilinos());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, SolicitudServicio solicitudServicio) throws SQLException {
        String query = "UPDATE solicitud_servicio SET servicioId = ?, numeroInquilinos = ? WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudServicio.getServicioId());
            stmt.setInt(2, solicitudServicio.getNumeroInquilinos());
            stmt.setInt(3, solicitudServicio.getSolicitudId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int solicitudId) throws SQLException {
        String query = "DELETE FROM solicitud_servicio WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<SolicitudServicio> listar(Connection conn) throws SQLException {
        List<SolicitudServicio> lista = new ArrayList<>();
        String query = "SELECT * FROM solicitud_servicio";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new SolicitudServicio(rs.getInt("solicitudId"), rs.getInt("servicioId"), rs.getInt("numeroInquilinos")));
            }
        }
        return lista;
    }
}
