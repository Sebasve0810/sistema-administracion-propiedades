/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Ubicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UbicacionDAO {

    // Obtener por ID
    public Ubicacion obtenerPorId(Connection conn, int ubicacionId) throws SQLException {
        String query = "SELECT * FROM ubicacion WHERE ubicacionId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ubicacionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ubicacion(
                    rs.getInt("ubicacionId"), 
                    rs.getString("pais"), 
                    rs.getString("departamento"), 
                    rs.getString("municipio"), 
                    rs.getString("zona")
                );
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Ubicacion ubicacion) throws SQLException {
        String query = "INSERT INTO ubicacion (pais, departamento, municipio, zona) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ubicacion.getPais());
            stmt.setString(2, ubicacion.getDepartamento());
            stmt.setString(3, ubicacion.getMunicipio());
            stmt.setString(4, ubicacion.getZona());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    ubicacion.setUbicacionId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Ubicacion ubicacion) throws SQLException {
        String query = "UPDATE ubicacion SET pais = ?, departamento = ?, municipio = ?, zona = ? WHERE ubicacionId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ubicacion.getPais());
            stmt.setString(2, ubicacion.getDepartamento());
            stmt.setString(3, ubicacion.getMunicipio());
            stmt.setString(4, ubicacion.getZona());
            stmt.setInt(5, ubicacion.getUbicacionId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int ubicacionId) throws SQLException {
        String query = "DELETE FROM ubicacion WHERE ubicacionId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ubicacionId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Ubicacion> listar(Connection conn) throws SQLException {
        List<Ubicacion> lista = new ArrayList<>();
        String query = "SELECT * FROM ubicacion";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Ubicacion(
                    rs.getInt("ubicacionId"), 
                    rs.getString("pais"), 
                    rs.getString("departamento"), 
                    rs.getString("municipio"), 
                    rs.getString("zona")
                ));
            }
        }
        return lista;
    }
}

