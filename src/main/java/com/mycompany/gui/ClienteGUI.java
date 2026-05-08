/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author veseb
 */
import com.mycompany.controller.ClienteController;
import com.mycompany.dao.ClienteDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;



public class ClienteGUI extends JFrame {
    private ClienteController clienteController;
    private Connection conn;

    private JTextField txtUsuarioId;
    private JTextField txtRentaMaxima;
    private JButton btnRegistrar;
    private JButton btnVerPropiedades;
    private JButton btnListaVisitas;
    private JButton btnSalir;
    private JButton btnRentar;

    private JLabel lblMensaje;
    

    public ClienteGUI(ClienteController clienteController, Connection conn) {
        this.clienteController = clienteController;
        this.conn = conn;
        
        

        setTitle("Cliente - Menú Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        lblMensaje = new JLabel("Bienvenido al sistema de clientes", SwingConstants.CENTER);
        txtUsuarioId = new JTextField();
        txtRentaMaxima = new JTextField();
        btnRegistrar = new JButton("Registrar Cliente");
        btnVerPropiedades = new JButton("Buscar Propiedades");
        btnListaVisitas = new JButton("Lista de Visitas");
        btnRentar = new JButton("Rentar propiedad");
        btnSalir = new JButton("Salir");

        add(lblMensaje);
        add(new JLabel("Usuario ID:"));
        add(txtUsuarioId);
        add(new JLabel("Renta Máxima:"));
        add(txtRentaMaxima);
        add(btnRegistrar);
        add(btnVerPropiedades);
        add(btnListaVisitas);
        add(btnRentar);
        add(btnSalir);

        // Acción para registrar cliente
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        // Acción para ver propiedades disponibles
        btnVerPropiedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PropiedadGUI(conn); // Abre la ventana de búsqueda de propiedades
            }
        });

        // Acción para ver la lista de visitas
        btnListaVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaVisitasGUI(conn,Integer.parseInt(txtUsuarioId.getText())); // Abre la ventana de lista de visitas
            }
        });
        
        btnRentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RentaSimpleGUI(conn,Integer.parseInt(txtUsuarioId.getText())); // Abre la ventana de lista de visitas
            }
        });

        // Acción para salir
        btnSalir.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para registrar un cliente
    private void registrarCliente() {
        try {
            int usuarioId = Integer.parseInt(txtUsuarioId.getText());
            float rentaMaxima = Float.parseFloat(txtRentaMaxima.getText());

            boolean exito = clienteController.registrarCliente(conn, usuarioId, rentaMaxima);
            if (exito) {
                lblMensaje.setText("Cliente registrado correctamente.");
            } else {
                lblMensaje.setText("Error al registrar cliente.");
            }
        } catch (NumberFormatException ex) {
            lblMensaje.setText("Ingrese valores válidos.");
        }
    }

   
}
