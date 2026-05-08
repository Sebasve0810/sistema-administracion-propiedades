/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Comision;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComisionDAO {

    // Obtener por ID
    public Comision obtenerPorId(Connection conn, int comisionId) throws SQLException {
        String query = "SELECT * FROM comision WHERE comisionId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, comisionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Comision(rs.getInt("comisionId"), rs.getString("tipo"), rs.getFloat("porcentaje"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Comision comision) throws SQLException {
        String query = "INSERT INTO comision (tipo, porcentaje) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, comision.getTipo());
            stmt.setFloat(2, comision.getPorcentaje());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    comision.setComisionId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Comision comision) throws SQLException {
        String query = "UPDATE comision SET tipo = ?, porcentaje = ? WHERE comisionId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, comision.getTipo());
            stmt.setFloat(2, comision.getPorcentaje());
            stmt.setInt(3, comision.getComisionId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int comisionId) throws SQLException {
        String query = "DELETE FROM comision WHERE comisionId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, comisionId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Comision> listar(Connection conn) throws SQLException {
        List<Comision> lista = new ArrayList<>();
        String query = "SELECT * FROM comision";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Comision(rs.getInt("comisionId"), rs.getString("tipo"), rs.getFloat("porcentaje")));
            }
        }
        return lista;
    }
}

