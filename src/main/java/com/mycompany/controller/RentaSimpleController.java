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
import com.mycompany.dao.SolicitudRentaDAO;
import com.mycompany.dao.VisitaDAO;
import com.mycompany.model.Cliente;
import com.mycompany.model.SolicitudRenta;
import com.mycompany.model.Visita;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RentaSimpleController {
    private SolicitudRentaDAO solicitudRentaDAO;
    private VisitaDAO visitaDAO;
    private ClienteDAO clienteDAO;

    // Constructor
    public RentaSimpleController(SolicitudRentaDAO solicitudRentaDAO, VisitaDAO visitaDAO, ClienteDAO clienteDAO) {
        this.solicitudRentaDAO = solicitudRentaDAO;
        this.visitaDAO = visitaDAO;
        this.clienteDAO = clienteDAO;
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

    // Método para verificar si la propiedad está en la lista de visitas del cliente
    public boolean propiedadEnListaVisitas(Connection conn, int usuarioId, int propiedadId) {
        try {
            List<Visita> visitas = visitaDAO.listar(conn).stream()
                .filter(visita -> visita.getClienteId() == usuarioId && visita.getPropiedadId() == propiedadId)
                .toList();

            return !visitas.isEmpty();
        } catch (SQLException e) {
            System.out.println("Error al verificar lista de visitas: " + e.getMessage());
            return false;
        }
    }

    // Método para iniciar el proceso de renta en modo simple
    public boolean rentarPropiedadModoSimple(Connection conn, int usuarioId, int propiedadId, float valorRenta) {
        try {
            if (!esClienteAutenticado(conn, usuarioId)) {
                System.out.println("El usuario no está autenticado como cliente.");
                return false;
            }

            if (!propiedadEnListaVisitas(conn, usuarioId, propiedadId)) {
                System.out.println("La propiedad no está en la lista de visitas del cliente.");
                return false;
            }

            // Crear una nueva solicitud de renta
            SolicitudRenta nuevaSolicitud = new SolicitudRenta(
                0, usuarioId, propiedadId, new Date(), "pendiente", valorRenta, valorRenta, valorRenta
            );

            return solicitudRentaDAO.insertar(conn, nuevaSolicitud);
        } catch (SQLException e) {
            System.out.println("Error al procesar renta: " + e.getMessage());
            return false;
        }
    }
}

