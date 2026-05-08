/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sap;


import com.mycompany.controller.ClienteController;
import com.mycompany.controller.UsuarioController;
import com.mycompany.dao.ClienteDAO;
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.gui.LoginGUI;
import com.mycompany.util.OracleDBConection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author dvd - steven sanchez
 */
public class Sap {

    public static void main(String[] args) {
       
        OracleDBConection con = new OracleDBConection();
        Connection conection = con.getConnection();
        // Simulación de conexión y controlador
        Connection conn = null; // Aquí iría la conexión a la BD
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        UsuarioController usuarioController = new UsuarioController(usuarioDAO);
        ClienteController clienteController = new ClienteController(clienteDAO);
        new LoginGUI(usuarioController,clienteController ,conection);
    }
}
