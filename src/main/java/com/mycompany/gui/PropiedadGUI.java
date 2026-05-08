/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.controller.PropiedadController;
import com.mycompany.dao.DueñoDAO;
import com.mycompany.dao.PropiedadDAO;
import com.mycompany.dao.UbicacionDAO;
import com.mycompany.model.Propiedad;
import java.sql.Connection;

/**
 *
 * @author veseb
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class PropiedadGUI extends JFrame {
    private PropiedadController propiedadController;
    private Connection conn;

    private JTextField txtTipo;
    private JTextField txtUbicacion;
    private JButton btnBuscarTipo;
    private JButton btnBuscarUbicacion;
    private JButton btnVerDetalles;
    private JButton btnSalir;
    private JTextArea txtResultados;

    public PropiedadGUI(Connection conn) {
        this.conn = conn;
        this.propiedadController = new PropiedadController(new PropiedadDAO(), new DueñoDAO(), new UbicacionDAO());


        setTitle("Buscar Propiedades");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        txtTipo = new JTextField();
        txtUbicacion = new JTextField();
        btnBuscarTipo = new JButton("Buscar por Tipo");
        btnBuscarUbicacion = new JButton("Buscar por Ubicación");
        btnVerDetalles = new JButton("Ver Detalles");
        btnSalir = new JButton("Salir");
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        add(new JLabel("Tipo de Propiedad:"));
        add(txtTipo);
        add(btnBuscarTipo);
        add(new JLabel("Ubicación (Municipio):"));
        add(txtUbicacion);
        add(btnBuscarUbicacion);
        add(new JScrollPane(txtResultados));
        add(btnVerDetalles);
        add(btnSalir);

        // Acción para buscar por tipo
        btnBuscarTipo.addActionListener(e -> buscarPorTipo());

        // Acción para buscar por ubicación
        btnBuscarUbicacion.addActionListener(e -> buscarPorUbicacion());

        // Acción para ver detalles de una propiedad seleccionada
        btnVerDetalles.addActionListener(e -> verDetalles());

        // Acción para salir
        btnSalir.addActionListener(e -> dispose());

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para buscar propiedades por tipo
    private void buscarPorTipo() {
        String tipo = txtTipo.getText();
        if (tipo.isEmpty()) {
            txtResultados.setText("Ingrese un tipo de propiedad.");
            return;
        }

        List<Propiedad> propiedades = propiedadController.buscarPorTipo(conn, tipo);
        mostrarResultados(propiedades);
    }

    // Método para buscar propiedades por ubicación
    private void buscarPorUbicacion() {
        String ubicacion = txtUbicacion.getText();
        if (ubicacion.isEmpty()) {
            txtResultados.setText("Ingrese una ubicación.");
            return;
        }

        List<Propiedad> propiedades = propiedadController.buscarPorUbicacion(conn, ubicacion);
        mostrarResultados(propiedades);
    }

    // Método para mostrar resultados
    private void mostrarResultados(List<Propiedad> propiedades) {
        txtResultados.setText("");
        if (propiedades.isEmpty()) {
            txtResultados.setText("No se encontraron propiedades.");
            return;
        }

        for (Propiedad propiedad : propiedades) {
            txtResultados.append("ID: " + propiedad.getPropiedadId() + ", Tipo: " + propiedad.getTipo()
                    + ", Dirección: " + propiedad.getDireccion() + "\n");
        }
    }

    // Método para ver detalles de una propiedad seleccionada
    private void verDetalles() {
        String idPropiedadStr = JOptionPane.showInputDialog("Ingrese el ID de la propiedad:");
        try {
            int propiedadId = Integer.parseInt(idPropiedadStr);
            PropiedadController propiedadController = new PropiedadController(new PropiedadDAO(), new DueñoDAO(), new UbicacionDAO());
            String detalles = propiedadController.verPropiedad(conn, propiedadId);
            JOptionPane.showMessageDialog(null, detalles);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    
}

