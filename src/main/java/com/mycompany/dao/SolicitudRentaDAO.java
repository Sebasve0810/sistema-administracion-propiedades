/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.SolicitudRenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRentaDAO {

    // Obtener por ID
    public SolicitudRenta obtenerPorId(Connection conn, int solicitudId) throws SQLException {
        String query = "SELECT * FROM solicitud_renta WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SolicitudRenta(
                    rs.getInt("solicitudId"), 
                    rs.getInt("clienteId"), 
                    rs.getInt("propiedadId"), 
                    rs.getDate("fechaSolicitud"), 
                    rs.getString("estado"), 
                    rs.getFloat("valorDueno"), 
                    rs.getFloat("valorRentaTot"), 
                    rs.getFloat("valorTotPagar")
                );
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, SolicitudRenta solicitudRenta) throws SQLException {
        String query = "INSERT INTO SolicitudRenta (solicitudId,clienteId, propiedadId, fechaSolicitud, estado, valorDueño, valorRentaTot, valorTotPagar) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int count = 8;
            stmt.setInt(1,count);
            stmt.setInt(2, solicitudRenta.getClienteId());

            stmt.setInt(3, solicitudRenta.getPropiedadId());
            stmt.setDate(4, new java.sql.Date(solicitudRenta.getFechaSolicitud().getTime()));
            stmt.setString(5, solicitudRenta.getEstado());
            stmt.setFloat(6, solicitudRenta.getValorDueno());
            stmt.setFloat(7, solicitudRenta.getValorRentaTot());
            stmt.setFloat(8, solicitudRenta.getValorTotPagar());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows ==1)
            {
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, SolicitudRenta solicitudRenta) throws SQLException {
        String query = "UPDATE solicitud_renta SET clienteId = ?, propiedadId = ?, fechaSolicitud = ?, estado = ?, valorDueno = ?, valorRentaTot = ?, valorTotPagar = ? WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudRenta.getClienteId());
            stmt.setInt(2, solicitudRenta.getPropiedadId());
            stmt.setDate(3, new java.sql.Date(solicitudRenta.getFechaSolicitud().getTime()));
            stmt.setString(4, solicitudRenta.getEstado());
            stmt.setFloat(5, solicitudRenta.getValorDueno());
            stmt.setFloat(6, solicitudRenta.getValorRentaTot());
            stmt.setFloat(7, solicitudRenta.getValorTotPagar());
            stmt.setInt(8, solicitudRenta.getSolicitudId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int solicitudId) throws SQLException {
        String query = "DELETE FROM solicitud_renta WHERE solicitudId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, solicitudId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<SolicitudRenta> listar(Connection conn) throws SQLException {
        List<SolicitudRenta> lista = new ArrayList<>();
        String query = "SELECT * FROM solicitud_renta";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new SolicitudRenta(
                    rs.getInt("solicitudId"), 
                    rs.getInt("clienteId"), 
                    rs.getInt("propiedadId"), 
                    rs.getDate("fechaSolicitud"), 
                    rs.getString("estado"), 
                    rs.getFloat("valorDueno"), 
                    rs.getFloat("valorRentaTot"), 
                    rs.getFloat("valorTotPagar")
                ));
            }
        }
        return lista;
    }
}

