/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.Visita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {

    // Obtener por ID
    public Visita obtenerPorId(Connection conn, int visitaId) throws SQLException {
        String query = "SELECT * FROM visita WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visitaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Visita(
                    rs.getInt("visitaId"), 
                    rs.getInt("clienteId"), 
                    rs.getInt("propiedadId"), 
                    rs.getDate("fecha"), 
                    rs.getString("comentario")
                );
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, Visita visita) throws SQLException {
        String query = "INSERT INTO visita (visitaId,clienteId, propiedadId, fecha, comentario) VALUES (?,?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int count = 6;
            stmt.setInt(1, count);

            stmt.setInt(2, visita.getClienteId());
            stmt.setInt(3, visita.getPropiedadId());
            stmt.setDate(4, new java.sql.Date(visita.getFecha().getTime()));
            stmt.setString(5, visita.getComentario());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, Visita visita) throws SQLException {
        String query = "UPDATE visita SET clienteId = ?, propiedadId = ?, fecha = ?, comentario = ? WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visita.getClienteId());
            stmt.setInt(2, visita.getPropiedadId());
            stmt.setDate(3, new java.sql.Date(visita.getFecha().getTime()));
            stmt.setString(4, visita.getComentario());
            stmt.setInt(5, visita.getVisitaId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int visitaId) throws SQLException {
        String query = "DELETE FROM visita WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visitaId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<Visita> listar(Connection conn) throws SQLException {
        List<Visita> lista = new ArrayList<>();
        String query = "SELECT * FROM visita";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Visita(
                    rs.getInt("visitaId"), 
                    rs.getInt("clienteId"), 
                    rs.getInt("propiedadId"), 
                    rs.getDate("fecha"), 
                    rs.getString("comentario")
                ));
            }
        }
        return lista;
    }
}

