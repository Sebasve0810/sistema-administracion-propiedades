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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class VerPropiedadGUI extends JFrame {
    private PropiedadController propiedadController;
    private Connection conn;

    private JTextField txtPropiedadId;
    private JButton btnVerDetalles;
    private JButton btnSalir;
    private JTextArea txtDetalles;
    private JLabel lblMensaje;

    public VerPropiedadGUI(Connection conn) {
        this.conn = conn;
        this.propiedadController = new PropiedadController(new PropiedadDAO(), new DueñoDAO(), new UbicacionDAO());

        setTitle("Ver Propiedad - Información Detallada");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        lblMensaje = new JLabel("Ingrese el ID de la propiedad para ver detalles", SwingConstants.CENTER);
        txtPropiedadId = new JTextField();
        btnVerDetalles = new JButton("Ver Detalles");
        btnSalir = new JButton("Salir");
        txtDetalles = new JTextArea();
        txtDetalles.setEditable(false);

        add(lblMensaje);
        add(new JLabel("ID Propiedad:"));
        add(txtPropiedadId);
        add(btnVerDetalles);
        add(new JScrollPane(txtDetalles));
        add(btnSalir);

        // Acción para ver detalles de la propiedad
        btnVerDetalles.addActionListener(e -> verDetalles());

        // Acción para salir
        btnSalir.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para ver detalles de una propiedad seleccionada
    private void verDetalles() {
        String idPropiedadStr = txtPropiedadId.getText();
        try {
            int propiedadId = Integer.parseInt(idPropiedadStr);
            String detalles = propiedadController.verPropiedad(conn, propiedadId);
            txtDetalles.setText(detalles);
        } catch (NumberFormatException ex) {
            txtDetalles.setText("ID inválido. Ingrese un número válido.");
        }
    }

    
}

