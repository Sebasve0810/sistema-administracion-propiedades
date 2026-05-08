/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dvd - steven sanchez
 */
public class OracleDBConection {
    private static final String URL = "jdbc:oracle:thin:@orion.javeriana.edu.co:1521/lab";
    private static final String USER = "is213820";
    private static final String PASSWORD = "U4WIYQmjpLrysAH";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MySQL");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return connection;
    }

   
}
