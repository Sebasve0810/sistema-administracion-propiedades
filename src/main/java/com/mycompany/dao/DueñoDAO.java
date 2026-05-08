/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Dueño;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DueñoDAO {

    // Obtener por ID
    public Dueño obtenerPorId(Connection conn, int usuarioId) throws SQLException {
        String query = "SELECT * FROM dueño WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Dueño(rs.getInt("usuarioId"), rs.getString("tipoDoc"), rs.getString("numDoc"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Dueño dueño) throws SQLException {
        String query = "INSERT INTO dueño (usuarioId, tipoDoc, numDoc) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dueño.getUsuarioId());
            stmt.setString(2, dueño.getTipoDoc());
            stmt.setString(3, dueño.getNumDoc());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Dueño dueño) throws SQLException {
        String query = "UPDATE dueño SET tipoDoc = ?, numDoc = ? WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dueño.getTipoDoc());
            stmt.setString(2, dueño.getNumDoc());
            stmt.setInt(3, dueño.getUsuarioId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int usuarioId) throws SQLException {
        String query = "DELETE FROM dueño WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Dueño> listar(Connection conn) throws SQLException {
        List<Dueño> lista = new ArrayList<>();
        String query = "SELECT * FROM dueño";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Dueño(rs.getInt("usuarioId"), rs.getString("tipoDoc"), rs.getString("numDoc")));
            }
        }
        return lista;
    }
}

