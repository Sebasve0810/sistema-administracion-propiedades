/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // Obtener por ID
    public Empleado obtenerPorId(Connection conn, int empleadoId) throws SQLException {
        String query = "SELECT * FROM empleado WHERE empleadoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, empleadoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(rs.getInt("empleadoId"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("agenciaId"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Empleado empleado) throws SQLException {
        String query = "INSERT INTO empleado (nombre, apellido, agenciaId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setInt(3, empleado.getAgenciaId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    empleado.setEmpleadoId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Empleado empleado) throws SQLException {
        String query = "UPDATE empleado SET nombre = ?, apellido = ?, agenciaId = ? WHERE empleadoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setInt(3, empleado.getAgenciaId());
            stmt.setInt(4, empleado.getEmpleadoId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int empleadoId) throws SQLException {
        String query = "DELETE FROM empleado WHERE empleadoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, empleadoId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Empleado> listar(Connection conn) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String query = "SELECT * FROM empleado";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Empleado(rs.getInt("empleadoId"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("agenciaId")));
            }
        }
        return lista;
    }
}

