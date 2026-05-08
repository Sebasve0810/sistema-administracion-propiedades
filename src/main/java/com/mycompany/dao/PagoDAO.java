/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Pago;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    // Obtener por ID
    public Pago obtenerPorId(Connection conn, int pagoId) throws SQLException {
        String query = "SELECT * FROM pago WHERE pagoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pagoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pago(rs.getInt("pagoId"), rs.getInt("solicitudId"), rs.getString("tipoPago"), 
                                rs.getFloat("monto"), rs.getString("detalleAutorizacion"), rs.getString("numBono"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Pago pago) throws SQLException {
        String query = "INSERT INTO pago (solicitudId, tipoPago, monto, detalleAutorizacion, numBono) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, pago.getSolicitudId());
            stmt.setString(2, pago.getTipoPago());
            stmt.setFloat(3, pago.getMonto());
            stmt.setString(4, pago.getDetalleAutorizacion());
            stmt.setString(5, pago.getNumBono());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    pago.setPagoId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Pago pago) throws SQLException {
        String query = "UPDATE pago SET solicitudId = ?, tipoPago = ?, monto = ?, detalleAutorizacion = ?, numBono = ? WHERE pagoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pago.getSolicitudId());
            stmt.setString(2, pago.getTipoPago());
            stmt.setFloat(3, pago.getMonto());
            stmt.setString(4, pago.getDetalleAutorizacion());
            stmt.setString(5, pago.getNumBono());
            stmt.setInt(6, pago.getPagoId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int pagoId) throws SQLException {
        String query = "DELETE FROM pago WHERE pagoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pagoId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Pago> listar(Connection conn) throws SQLException {
        List<Pago> lista = new ArrayList<>();
        String query = "SELECT * FROM pago";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Pago(rs.getInt("pagoId"), rs.getInt("solicitudId"), rs.getString("tipoPago"), 
                                   rs.getFloat("monto"), rs.getString("detalleAutorizacion"), rs.getString("numBono")));
            }
        }
        return lista;
    }
}

