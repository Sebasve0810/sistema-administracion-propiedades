/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author veseb
 */
import com.mycompany.controller.PropiedadController;
import com.mycompany.dao.DueñoDAO;
import com.mycompany.dao.PropiedadDAO;
import com.mycompany.dao.UbicacionDAO;
import com.mycompany.model.Propiedad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DueñoGUI extends JFrame {
    private PropiedadController propiedadController;
    private Connection conn;
    private int usuarioId;

    private JTextField txtTipo;
    private JTextField txtDireccion;
    private JTextField txtUbicacionId;
    private JButton btnAgregarPropiedad;
    private JButton btnVerPropiedades;
    private JButton btnSalir;
    private JLabel lblMensaje;

    public DueñoGUI(Connection conn, int usuarioId) {
        this.conn = conn;
        this.usuarioId = usuarioId;
        this.propiedadController = new PropiedadController(new PropiedadDAO(), new DueñoDAO(), new UbicacionDAO());

        setTitle("Dueño - Menú Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        lblMensaje = new JLabel("Bienvenido, dueño", SwingConstants.CENTER);
        txtTipo = new JTextField();
        txtDireccion = new JTextField();
        txtUbicacionId = new JTextField();
        btnAgregarPropiedad = new JButton("Agregar Propiedad");
        btnVerPropiedades = new JButton("Ver Mis Propiedades");
        btnSalir = new JButton("Salir");

        add(lblMensaje);
        add(new JLabel("Tipo de Propiedad:"));
        add(txtTipo);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("ID de Ubicación:"));
        add(txtUbicacionId);
        add(btnAgregarPropiedad);
        add(btnVerPropiedades);
        add(btnSalir);

        // Acción para agregar propiedad
        btnAgregarPropiedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPropiedad();
            }
        });

        // Acción para ver propiedades del dueño
        btnVerPropiedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verMisPropiedades();
            }
        });

        // Acción para salir
        btnSalir.addActionListener(e -> dispose());

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para agregar propiedad
    private void agregarPropiedad() {
        try {
            
            String tipo = txtTipo.getText();
            String direccion = txtDireccion.getText();
            int ubicacionId = Integer.parseInt(txtUbicacionId.getText());
            System.out.println(tipo + direccion + ubicacionId + usuarioId);

            boolean exito = propiedadController.agregarPropiedad(conn, 2, tipo, direccion, ubicacionId);
            lblMensaje.setText(exito ? "Propiedad agregada correctamente." : "Erro Propiedad agregar.");
        } catch (NumberFormatException ex) {
            lblMensaje.setText("Ingrese un ID de ubicación válido.");
        }
    }

    // Método para ver las propiedades del dueño
    private void verMisPropiedades() {
        StringBuilder resultado = new StringBuilder();
        for (Propiedad propiedad : propiedadController.listarPropiedades(conn)) {
            if (propiedadController.esDueño(conn, usuarioId)) {
                resultado.append("ID: ").append(propiedad.getPropiedadId())
                        .append(", Tipo: ").append(propiedad.getTipo())
                        .append(", Dirección: ").append(propiedad.getDireccion()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, resultado.length() > 0 ? resultado.toString() : "No tienes propiedades registradas.");
    }

   
}

