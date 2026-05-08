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
import com.mycompany.controller.UsuarioController;
import com.mycompany.model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginGUI extends JFrame {
    private UsuarioController usuarioController;
    private ClienteController clienteController;
    private Connection conn; // Conexión a la base de datos
    private JTextField txtUsuarioId;
    private JTextField txtRentaMaxima;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMensaje;


    public LoginGUI(UsuarioController usuarioController,ClienteController clienteController ,Connection conn) {
        this.usuarioController = usuarioController;
        this.clienteController = clienteController;
        this.conn = conn;

        setTitle("Iniciar Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Componentes de la interfaz
        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Ingresar");
        lblMensaje = new JLabel("Ingrese sus credenciales", SwingConstants.CENTER);


        // Agregar componentes al Frame
        add(new JLabel("Usuario:"));
        add(txtUsuario);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(btnLogin);
        add(lblMensaje);
        add(lblMensaje);


        // Acción del botón
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    // Método para autenticar al usuario
    private void autenticarUsuario() {
        String usuarioName = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        try {
            Usuario usuario = usuarioController.autenticarUsuario(conn, usuarioName, password);
            if (usuario != null) {
                lblMensaje.setText("Bienvenido " + usuario.getNombre());
                redirigirModulo(usuario);
            } else {
                lblMensaje.setText("Credenciales incorrectas.");
            }
        } catch (SQLException ex) {
            lblMensaje.setText("Error al conectar: " + ex.getMessage());
        }
    }

    // Método para redirigir al módulo correspondiente según el rol
    private void redirigirModulo(Usuario usuario) {
        dispose(); // Cierra la ventana de login
        switch (usuario.getTipoUsuario().toLowerCase()) {
            case "cliente":
                new ClienteGUI(clienteController,conn);
                break;
            case "dueño":
                //esto se debe corregir 
                new DueñoGUI(conn,1);
                break;
            case "visitante":
                new VisitanteGUI(conn);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de usuario desconocido.");
        }
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
