/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Propiedad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropiedadDAO {

    // Obtener por ID
    public Propiedad obtenerPorId(Connection conn, int propiedadId) throws SQLException {
        String query = "SELECT * FROM propiedad WHERE propiedadId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propiedadId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Propiedad(rs.getInt("propiedadId"), rs.getString("tipo"), rs.getString("direccion"), rs.getInt("ubicacionId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Propiedad propiedad) throws SQLException {
        String query = "INSERT INTO propiedad (propiedadId,tipo, direccion, ubicacionId) VALUES (?,?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int count = 9;
            stmt.setInt(1, count);

            stmt.setString(2, propiedad.getTipo());
            stmt.setString(3, propiedad.getDireccion());
            stmt.setInt(4, propiedad.getUbicacionId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Propiedad propiedad) throws SQLException {
        String query = "UPDATE propiedad SET tipo = ?, direccion = ?, ubicacionId = ? WHERE propiedadId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, propiedad.getTipo());
            stmt.setString(2, propiedad.getDireccion());
            stmt.setInt(3, propiedad.getUbicacionId());
            stmt.setInt(4, propiedad.getPropiedadId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int propiedadId) throws SQLException {
        String query = "DELETE FROM propiedad WHERE propiedadId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, propiedadId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Propiedad> listar(Connection conn) throws SQLException {
        List<Propiedad> lista = new ArrayList<>();
        String query = "SELECT * FROM propiedad";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Propiedad(rs.getInt("propiedadId"), rs.getString("tipo"), rs.getString("direccion"), rs.getInt("ubicacionId")));
            }
        }
        return lista;
    }
}

