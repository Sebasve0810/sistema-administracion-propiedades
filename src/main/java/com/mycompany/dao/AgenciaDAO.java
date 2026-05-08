/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Agencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO {

    // Obtener por ID
    public Agencia obtenerPorId(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM agencia WHERE agenciaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Agencia(rs.getInt("agenciaId"), rs.getString("nombre"), rs.getString("direccion"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Agencia agencia) throws SQLException {
        String query = "INSERT INTO agencia (nombre, direccion) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, agencia.getNombre());
            stmt.setString(2, agencia.getDireccion());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    agencia.setAgenciaId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Agencia agencia) throws SQLException {
        String query = "UPDATE agencia SET nombre = ?, direccion = ? WHERE agenciaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, agencia.getNombre());
            stmt.setString(2, agencia.getDireccion());
            stmt.setInt(3, agencia.getAgenciaId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM agencia WHERE agenciaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Agencia> listar(Connection conn) throws SQLException {
        List<Agencia> lista = new ArrayList<>();
        String query = "SELECT * FROM agencia";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Agencia(rs.getInt("agenciaId"), rs.getString("nombre"), rs.getString("direccion")));
            }
        }
        return lista;
    }
}

