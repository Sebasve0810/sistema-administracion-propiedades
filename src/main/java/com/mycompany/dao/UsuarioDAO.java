/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Obtener por ID
    public Usuario obtenerPorId(Connection conn, int usuarioId) throws SQLException {
        String query = "SELECT * FROM usuario WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("usuarioId"),
                    rs.getString("usuarioName"),
                    rs.getString("password"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getDate("fechaCreacion"),
                    rs.getString("tipoUsuario"),
                    rs.getString("estado")
                );
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario (usuarioName, password, nombre, apellido, email, fechaCreacion, tipoUsuario, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getUsuarioName());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getApellido());
            stmt.setString(5, usuario.getEmail());
            stmt.setDate(6, new java.sql.Date(usuario.getFechaCreacion().getTime()));
            stmt.setString(7, usuario.getTipoUsuario());
            stmt.setString(8, usuario.getEstado());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    usuario.setUsuarioId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Usuario usuario) throws SQLException {
        String query = "UPDATE usuario SET usuarioName = ?, password = ?, nombre = ?, apellido = ?, email = ?, fechaCreacion = ?, tipoUsuario = ?, estado = ? WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario.getUsuarioName());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getApellido());
            stmt.setString(5, usuario.getEmail());
            stmt.setDate(6, new java.sql.Date(usuario.getFechaCreacion().getTime()));
            stmt.setString(7, usuario.getTipoUsuario());
            stmt.setString(8, usuario.getEstado());
            stmt.setInt(9, usuario.getUsuarioId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int usuarioId) throws SQLException {
        String query = "DELETE FROM usuario WHERE usuarioId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Usuario> listar(Connection conn) throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("usuarioId"),
                    rs.getString("usuarioName"),
                    rs.getString("password"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getDate("fechaCreacion"),
                    rs.getString("tipoUsuario"),
                    rs.getString("estado")
                ));
            }
        }
        return lista;
    }
}

