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
import com.mycompany.dao.ClienteDAO;
import com.mycompany.dao.PropiedadDAO;
import com.mycompany.dao.VisitaDAO;
import com.mycompany.model.Visita;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class ListaVisitasGUI extends JFrame {
    private ListaVisitasController listaVisitasController;
    private Connection conn;
    private int usuarioId;

    private JTextField txtPropiedadId;
    private JTextField txtComentario;
    private JButton btnAgregarVisita;
    private JButton btnVerVisitas;
    private JButton btnSalir;
    private JTextArea txtResultados;
    private JLabel lblMensaje;

    public ListaVisitasGUI(Connection conn, int usuarioId) {
        this.conn = conn;
        this.usuarioId = usuarioId;
        this.listaVisitasController = new ListaVisitasController(new VisitaDAO(), new ClienteDAO(), new PropiedadDAO());

        setTitle("Lista de Visitas - Cliente");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        lblMensaje = new JLabel("Gestión de lista de visitas", SwingConstants.CENTER);
        txtPropiedadId = new JTextField();
        txtComentario = new JTextField();
        btnAgregarVisita = new JButton("Agregar a Lista de Visitas");
        btnVerVisitas = new JButton("Ver Mis Visitas");
        btnSalir = new JButton("Salir");
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        add(lblMensaje);
        add(new JLabel("ID Propiedad:"));
        add(txtPropiedadId);
        add(new JLabel("Comentario:"));
        add(txtComentario);
        add(btnAgregarVisita);
        add(btnVerVisitas);
        add(new JScrollPane(txtResultados));
        add(btnSalir);

        // Acción para agregar una propiedad a la lista de visitas
        btnAgregarVisita.addActionListener(e -> agregarVisita());

        // Acción para ver todas las visitas del usuario
        btnVerVisitas.addActionListener(e -> verVisitas());

        // Acción para salir
        btnSalir.addActionListener(e -> dispose());

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para agregar una visita a la lista
    private void agregarVisita() {
        try {
            int propiedadId = Integer.parseInt(txtPropiedadId.getText());
            String comentario = txtComentario.getText();

            boolean exito = listaVisitasController.agregarAListaVisitas(conn, usuarioId, propiedadId, comentario);
            lblMensaje.setText(exito ? "Propiedad agregada a la lista de visitas." : "Error al agregar propiedad.");
        } catch (NumberFormatException ex) {
            lblMensaje.setText("Ingrese un ID de propiedad válido.");
        }
    }

    // Método para ver las visitas del usuario
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

   
}

