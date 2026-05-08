/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.ServicioExtra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioExtraDAO {

    // Obtener por ID
    public ServicioExtra obtenerPorId(Connection conn, int servicioId) throws SQLException {
        String query = "SELECT * FROM servicio_extra WHERE servicioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, servicioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ServicioExtra(rs.getInt("servicioId"), rs.getString("nombre"), rs.getFloat("costoXinquilino"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, ServicioExtra servicioExtra) throws SQLException {
        String query = "INSERT INTO servicio_extra (nombre, costoXinquilino) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, servicioExtra.getNombre());
            stmt.setFloat(2, servicioExtra.getCostoXinquilino());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    servicioExtra.setServicioId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, ServicioExtra servicioExtra) throws SQLException {
        String query = "UPDATE servicio_extra SET nombre = ?, costoXinquilino = ? WHERE servicioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, servicioExtra.getNombre());
            stmt.setFloat(2, servicioExtra.getCostoXinquilino());
            stmt.setInt(3, servicioExtra.getServicioId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int servicioId) throws SQLException {
        String query = "DELETE FROM servicio_extra WHERE servicioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, servicioId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<ServicioExtra> listar(Connection conn) throws SQLException {
        List<ServicioExtra> lista = new ArrayList<>();
        String query = "SELECT * FROM servicio_extra";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new ServicioExtra(rs.getInt("servicioId"), rs.getString("nombre"), rs.getFloat("costoXinquilino")));
            }
        }
        return lista;
    }
}

