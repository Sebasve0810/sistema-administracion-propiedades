/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author veseb
 */
import com.mycompany.controller.ListaVisitasController;
import com.mycompany.controller.RentaSimpleController;
import com.mycompany.dao.ClienteDAO;
import com.mycompany.dao.PropiedadDAO;
import com.mycompany.dao.SolicitudRentaDAO;
import com.mycompany.dao.VisitaDAO;
import com.mycompany.model.Visita;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;

public class RentaSimpleGUI extends JFrame {
    private ListaVisitasController listaVisitasController;
    private RentaSimpleController rentaSimpleController;
    private Connection conn;
    private int usuarioId;

    private JTextField txtPropiedadId;
    private JTextField txtValorRenta;
    private JButton btnConfirmarRenta;
    private JButton btnVerVisitas;
    private JButton btnSalir;
    private JTextArea txtResultados;
    private JLabel lblMensaje;

    public RentaSimpleGUI(Connection conn, int usuarioId) {
        this.conn = conn;
        this.usuarioId = usuarioId;
        this.listaVisitasController = new ListaVisitasController(new VisitaDAO(), new ClienteDAO(), new PropiedadDAO());
        this.rentaSimpleController = new RentaSimpleController(new SolicitudRentaDAO(), new VisitaDAO(), new ClienteDAO());

        setTitle("Rentar Propiedad - Modo Simple");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        lblMensaje = new JLabel("Seleccione una propiedad de su lista de visitas para rentar", SwingConstants.CENTER);
        txtPropiedadId = new JTextField();
        txtValorRenta = new JTextField();
        btnConfirmarRenta = new JButton("Confirmar Renta (Pago en Efectivo)");
        btnVerVisitas = new JButton("Ver Lista de Visitas");
        btnSalir = new JButton("Salir");
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        add(lblMensaje);
        add(new JLabel("ID Propiedad:"));
        add(txtPropiedadId);
        add(new JLabel("Valor de Renta:"));
        add(txtValorRenta);
        add(btnConfirmarRenta);
        add(btnVerVisitas);
        add(new JScrollPane(txtResultados));
        add(btnSalir);

        // Acción para ver lista de visitas
        btnVerVisitas.addActionListener(e -> verVisitas());

        // Acción para confirmar la renta
        btnConfirmarRenta.addActionListener(e -> confirmarRenta());

        // Acción para salir
        btnSalir.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para ver lista de visitas del usuario
    private void verVisitas() {
        List<Visita> visitas = listaVisitasController.obtenerListaVisitas(conn, usuarioId);

        txtResultados.setText("");
        if (visitas == null || visitas.isEmpty()) {
            txtResultados.setText("No tienes visitas registradas.");
            return;
        }

        for (Visita visita : visitas) {
            txtResultados.append("Propiedad ID: " + visita.getPropiedadId() + ", Fecha: " + visita.getFecha()
                    + ", Comentario: " + visita.getComentario() + "\n");
        }
    }

    // Método para confirmar renta en efectivo
    private void confirmarRenta() {
        try {
            int propiedadId = Integer.parseInt(txtPropiedadId.getText());
            float valorRenta = Float.parseFloat(txtValorRenta.getText());

            if (rentaSimpleController.rentarPropiedadModoSimple(conn, usuarioId, propiedadId, valorRenta)) {
                JOptionPane.showMessageDialog(this, "¡Renta confirmada con pago en efectivo!\nPropiedad ID: " + propiedadId);
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar la renta.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos.");
        }
    }

   
}


