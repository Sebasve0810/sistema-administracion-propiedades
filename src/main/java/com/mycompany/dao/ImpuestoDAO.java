/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Impuesto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpuestoDAO {

    // Obtener por ID
    public Impuesto obtenerPorId(Connection conn, int impuestoId) throws SQLException {
        String query = "SELECT * FROM impuesto WHERE impuestoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, impuestoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Impuesto(rs.getInt("impuestoId"), rs.getString("tipo"), rs.getFloat("porcentaje"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Impuesto impuesto) throws SQLException {
        String query = "INSERT INTO impuesto (tipo, porcentaje) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, impuesto.getTipo());
            stmt.setFloat(2, impuesto.getPorcentaje());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    impuesto.setImpuestoId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Impuesto impuesto) throws SQLException {
        String query = "UPDATE impuesto SET tipo = ?, porcentaje = ? WHERE impuestoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, impuesto.getTipo());
            stmt.setFloat(2, impuesto.getPorcentaje());
            stmt.setInt(3, impuesto.getImpuestoId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int impuestoId) throws SQLException {
        String query = "DELETE FROM impuesto WHERE impuestoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, impuestoId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Impuesto> listar(Connection conn) throws SQLException {
        List<Impuesto> lista = new ArrayList<>();
        String query = "SELECT * FROM impuesto";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Impuesto(rs.getInt("impuestoId"), rs.getString("tipo"), rs.getFloat("porcentaje")));
            }
        }
        return lista;
    }
}

