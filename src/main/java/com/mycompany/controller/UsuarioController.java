/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

/**
 *
 * @author veseb
 */
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    // Constructor
    public UsuarioController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Método para autenticar un usuario
    public Usuario autenticarUsuario(Connection conn, String usuarioName, String password) throws SQLException {
        for (Usuario usuario : usuarioDAO.listar(conn)) {
            if (usuario.getUsuarioName().equals(usuarioName) && usuario.getPassword().equals(password)) {
                return usuario; // Usuario autenticado
            }
        }
        return null; // Credenciales incorrectas
    }

    // Método para obtener funcionalidades según el rol del usuario
    public String obtenerFuncionalidades(Usuario usuario) {
        if (usuario == null) {
            return "Acceso denegado";
        }
        
        switch (usuario.getTipoUsuario().toLowerCase()) {
            case "cliente":
                return "Funcionalidades disponibles: Solicitar renta, ver propiedades, realizar pagos.";
            case "dueño":
                return "Funcionalidades disponibles: Gestionar propiedades, consultar pagos, modificar condiciones de alquiler.";
            case "visitante":
                return "Funcionalidades disponibles: Explorar propiedades, realizar consultas.";
            default:
                return "Tipo de usuario desconocido.";
        }
    }
}

