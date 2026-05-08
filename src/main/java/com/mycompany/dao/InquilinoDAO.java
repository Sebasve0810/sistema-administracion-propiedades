/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Inquilino;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InquilinoDAO {

    // Obtener por ID
    public Inquilino obtenerPorId(Connection conn, int inquilinoId) throws SQLException {
        String query = "SELECT * FROM inquilino WHERE inquilinoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, inquilinoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Inquilino(rs.getInt("inquilinoId"), rs.getString("nombre"), rs.getString("tipoDoc"), rs.getString("numDoc"), rs.getInt("solicitudId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Inquilino inquilino) throws SQLException {
        String query = "INSERT INTO inquilino (nombre, tipoDoc, numDoc, solicitudId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, inquilino.getNombre());
            stmt.setString(2, inquilino.getTipoDoc());
            stmt.setString(3, inquilino.getNumDoc());
            stmt.setInt(4, inquilino.getSolicitudId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    inquilino.setInquilinoId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Inquilino inquilino) throws SQLException {
        String query = "UPDATE inquilino SET nombre = ?, tipoDoc = ?, numDoc = ?, solicitudId = ? WHERE inquilinoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, inquilino.getNombre());
            stmt.setString(2, inquilino.getTipoDoc());
            stmt.setString(3, inquilino.getNumDoc());
            stmt.setInt(4, inquilino.getSolicitudId());
            stmt.setInt(5, inquilino.getInquilinoId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int inquilinoId) throws SQLException {
        String query = "DELETE FROM inquilino WHERE inquilinoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, inquilinoId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Inquilino> listar(Connection conn) throws SQLException {
        List<Inquilino> lista = new ArrayList<>();
        String query = "SELECT * FROM inquilino";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Inquilino(rs.getInt("inquilinoId"), rs.getString("nombre"), rs.getString("tipoDoc"), rs.getString("numDoc"), rs.getInt("solicitudId")));
            }
        }
        return lista;
    }
}

