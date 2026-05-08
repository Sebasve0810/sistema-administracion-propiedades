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
import com.mycompany.dao.PropiedadDAO;
import com.mycompany.dao.VisitaDAO;
import com.mycompany.model.Cliente;
import com.mycompany.model.Propiedad;
import com.mycompany.model.Visita;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ListaVisitasController {
    private VisitaDAO visitaDAO;
    private ClienteDAO clienteDAO;
    private PropiedadDAO propiedadDAO;

    // Constructor
    public ListaVisitasController(VisitaDAO visitaDAO, ClienteDAO clienteDAO, PropiedadDAO propiedadDAO) {
        this.visitaDAO = visitaDAO;
        this.clienteDAO = clienteDAO;
        this.propiedadDAO = propiedadDAO;
    }

    // Método para verificar si el usuario es un cliente autenticado
    public boolean esClienteAutenticado(Connection conn, int usuarioId) {
        try {
            Cliente cliente = clienteDAO.obtenerPorId(conn, usuarioId);
            return cliente != null;
        } catch (SQLException e) {
            System.out.println("Error al verificar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para agregar una propiedad a la lista de visitas
    public boolean agregarAListaVisitas(Connection conn, int usuarioId, int propiedadId, String comentario) {
        try {
            if (!esClienteAutenticado(conn, usuarioId)) {
                System.out.println("El usuario no está autenticado como cliente.");
                return false;
            }

            // Verificar si la propiedad existe
            Propiedad propiedad = propiedadDAO.obtenerPorId(conn, propiedadId);
            if (propiedad == null) {
                System.out.println("La propiedad no existe.");
                return false;
            }

            // Crear una nueva visita y guardarla
            Visita nuevaVisita = new Visita(0, usuarioId, propiedadId, new Date(), comentario);
            return visitaDAO.insertar(conn, nuevaVisita);
        } catch (SQLException e) {
            System.out.println("Error al agregar propiedad a lista de visitas: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todas las visitas de un cliente
    public List<Visita> obtenerListaVisitas(Connection conn, int usuarioId) {
        try {
            return visitaDAO.listar(conn).stream()
                .filter(visita -> visita.getClienteId() == usuarioId)
                .toList();
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de visitas: " + e.getMessage());
            return null;
        }
    }
}

