/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Obtener por ID
    public Cliente obtenerPorId(Connection conn, int usuarioId) throws SQLException {
        String query = "SELECT * FROM cliente WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("usuarioId"), rs.getFloat("rentaMaxima"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (usuarioId, rentaMaxima) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cliente.getUsuarioId());
            stmt.setFloat(2, cliente.getRentaMaxima());
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Cliente cliente) throws SQLException {
        String query = "UPDATE cliente SET rentaMaxima = ? WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setFloat(1, cliente.getRentaMaxima());
            stmt.setInt(2, cliente.getUsuarioId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int usuarioId) throws SQLException {
        String query = "DELETE FROM cliente WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Cliente> listar(Connection conn) throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("usuarioId"), rs.getFloat("rentaMaxima")));
            }
        }
        return lista;
    }
}

