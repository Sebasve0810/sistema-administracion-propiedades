/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author veseb
 */
import com.mycompany.controller.ModuloReportesController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class ModuloReportesGUI extends JFrame {
    private ModuloReportesController reportesController;
    private Connection conn;

    private JTextField txtPeriodo;
    private JTextField txtClienteId;
    private JButton btnTotalPropiedadesRentadas;
    private JButton btnTotalPagadoADueños;
    private JButton btnTotalRentasPorCliente;
    private JButton btnTotalRentasPorUbicacion;
    private JButton btnReporteImpuestos;
    private JButton btnPropiedadesMasRentadas;
    private JButton btnTotalRentasConImpuestos;
    private JButton btnTotalComisionesPagadas;
    private JButton btnSalir;
    private JTextArea txtResultados;

    public ModuloReportesGUI(Connection conn) {
        this.conn = conn;
        this.reportesController = new ModuloReportesController(conn);

        setTitle("Módulo de Reportes");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 1));

        txtPeriodo = new JTextField();
        txtClienteId = new JTextField();
        btnTotalPropiedadesRentadas = new JButton("Propiedades Rentadas por Cliente");
        btnTotalPagadoADueños = new JButton("Total Pagado a Dueños");
        btnTotalRentasPorCliente = new JButton("Total Rentas por Cliente");
        btnTotalRentasPorUbicacion = new JButton("Total Rentas por Ubicación");
        btnReporteImpuestos = new JButton("Reporte de Impuestos");
        btnPropiedadesMasRentadas = new JButton("Tipos de Propiedades Más Rentadas");
        btnTotalRentasConImpuestos = new JButton("Total de Rentas con Impuestos");
        btnTotalComisionesPagadas = new JButton("Total Comisiones Pagadas");
        btnSalir = new JButton("Salir");
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);

        add(new JLabel("Ingrese el período (ejemplo: 2025 o 2025-06):"));
        add(txtPeriodo);
        add(new JLabel("Cliente ID (Opcional para consultas específicas):"));
        add(txtClienteId);
        add(btnTotalPropiedadesRentadas);
        add(btnTotalPagadoADueños);
        add(btnTotalRentasPorCliente);
        add(btnTotalRentasPorUbicacion);
        add(btnReporteImpuestos);
        add(btnPropiedadesMasRentadas);
        add(btnTotalRentasConImpuestos);
        add(btnTotalComisionesPagadas);
        add(new JScrollPane(txtResultados));
        add(btnSalir);

        // Acciones de botones
        btnTotalPropiedadesRentadas.addActionListener(e -> consultarPropiedadesRentadas());
        btnTotalPagadoADueños.addActionListener(e -> consultarTotalPagadoADueños());
        btnTotalRentasPorCliente.addActionListener(e -> consultarTotalRentasPorCliente());
        btnTotalRentasPorUbicacion.addActionListener(e -> consultarTotalRentasPorUbicacion());
        btnReporteImpuestos.addActionListener(e -> consultarReporteImpuestos());
        btnPropiedadesMasRentadas.addActionListener(e -> consultarPropiedadesMasRentadas());
        btnTotalRentasConImpuestos.addActionListener(e -> consultarTotalRentasConImpuestos());
        btnTotalComisionesPagadas.addActionListener(e -> consultarTotalComisionesPagadas());
        btnSalir.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Métodos de consulta
    private void consultarPropiedadesRentadas() {
        try {
            int clienteId = txtClienteId.getText().isEmpty() ? 0 : Integer.parseInt(txtClienteId.getText());
            String periodo = txtPeriodo.getText();
            int resultado = reportesController.obtenerCantidadPropiedadesRentadasPorCliente(clienteId, periodo);
            txtResultados.setText("Total de propiedades rentadas: " + resultado);
        } catch (NumberFormatException ex) {
            txtResultados.setText("Ingrese valores válidos.");
        }
    }

    private void consultarTotalPagadoADueños() {
        String periodo = txtPeriodo.getText();
        float resultado = reportesController.obtenerSumaTotalPagadaADueños(periodo);
        txtResultados.setText("Total pagado a dueños: $" + resultado);
    }

    private void consultarTotalRentasPorCliente() {
        try {
            int clienteId = Integer.parseInt(txtClienteId.getText());
            String periodo = txtPeriodo.getText();
            float resultado = reportesController.obtenerSumaRentasPorCliente(clienteId, periodo);
            txtResultados.setText("Total rentas del cliente: $" + resultado);
        } catch (NumberFormatException ex) {
            txtResultados.setText("Ingrese valores válidos.");
        }
    }

    private void consultarTotalRentasPorUbicacion() {
        String ubicacion = JOptionPane.showInputDialog("Ingrese el municipio:");
        String periodo = txtPeriodo.getText();
        int resultado = reportesController.obtenerNumeroTotalRentasPorUbicacion(ubicacion, periodo);
        txtResultados.setText("Número total de rentas en " + ubicacion + ": " + resultado);
    }

    private void consultarReporteImpuestos() {
        String periodo = txtPeriodo.getText();
        float resultado = reportesController.obtenerTotalImpuestosPorPeriodo(periodo);
        txtResultados.setText("Total de impuestos por período: $" + resultado);
    }

    private void consultarPropiedadesMasRentadas() {
        String periodo = txtPeriodo.getText();
        String resultado = reportesController.obtenerTiposPropiedadesMasRentadas(periodo);
        txtResultados.setText("Tipo de propiedad más rentada: " + resultado);
    }

    private void consultarTotalRentasConImpuestos() {
        String periodo = txtPeriodo.getText();
        float resultado = reportesController.obtenerTotalRentasConImpuestos(periodo);
        txtResultados.setText("Total de rentas con impuestos incluidos: $" + resultado);
    }

    private void consultarTotalComisionesPagadas() {
        String periodo = txtPeriodo.getText();
        float resultado = reportesController.obtenerTotalComisionesPorPeriodo(periodo);
        txtResultados.setText("Total de comisiones pagadas: $" + resultado);
    }

    
}

