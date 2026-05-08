/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author veseb
 */
import com.mycompany.model.ListaVisita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaVisitaDAO {

    // Obtener por ID
    public ListaVisita obtenerPorId(Connection conn, int visitaId) throws SQLException {
        String query = "SELECT * FROM lista_visita WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visitaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ListaVisita(rs.getInt("visitaId"), rs.getInt("clienteId"), rs.getDate("fechaCreacion"));
            }
        }
        return null;
    }

    // Insertar nuevo registro
    public boolean insertar(Connection conn, ListaVisita listaVisita) throws SQLException {
        String query = "INSERT INTO lista_visita (clienteId, fechaCreacion) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, listaVisita.getClienteId());
            stmt.setDate(2, new java.sql.Date(listaVisita.getFechaCreacion().getTime()));
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    listaVisita.setVisitaId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Actualizar campos
    public boolean actualizar(Connection conn, ListaVisita listaVisita) throws SQLException {
        String query = "UPDATE lista_visita SET clienteId = ?, fechaCreacion = ? WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, listaVisita.getClienteId());
            stmt.setDate(2, new java.sql.Date(listaVisita.getFechaCreacion().getTime()));
            stmt.setInt(3, listaVisita.getVisitaId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar registro
    public boolean eliminar(Connection conn, int visitaId) throws SQLException {
        String query = "DELETE FROM lista_visita WHERE visitaId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, visitaId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Listar registros
    public List<ListaVisita> listar(Connection conn) throws SQLException {
        List<ListaVisita> lista = new ArrayList<>();
        String query = "SELECT * FROM lista_visita";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new ListaVisita(rs.getInt("visitaId"), rs.getInt("clienteId"), rs.getDate("fechaCreacion")));
            }
        }
        return lista;
    }
}

