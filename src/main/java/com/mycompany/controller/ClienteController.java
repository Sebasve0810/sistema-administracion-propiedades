/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

/**
 *
 * @author veseb
 */
import com.mycompany.dao.ClienteDAO;
import com.mycompany.model.Cliente;
import java.sql.Connection;
import java.sql.SQLException;

public class ClienteController {
    private ClienteDAO clienteDAO;

    // Constructor
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Método para registrar un nuevo cliente
    public boolean registrarCliente(Connection conn, int usuarioId, float rentaMaxima) {
        try {
            Cliente nuevoCliente = new Cliente(usuarioId, rentaMaxima);
            return clienteDAO.insertar(conn, nuevoCliente);
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener un cliente por ID
    public Cliente obtenerClientePorId(Connection conn, int usuarioId) {
        try {
            return clienteDAO.obtenerPorId(conn, usuarioId);
        } catch (SQLException e) {
            System.out.println("Error al obtener cliente: " + e.getMessage());
            return null;
        }
    }
}

