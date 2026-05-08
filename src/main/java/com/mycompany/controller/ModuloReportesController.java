/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

/**
 *
 * @author veseb
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuloReportesController {
    private Connection conn;

    // Constructor
    public ModuloReportesController(Connection conn) {
        this.conn = conn;
    }

    // 1. Cantidad total de propiedades rentadas por cliente por período
    public int obtenerCantidadPropiedadesRentadasPorCliente(int clienteId, String periodo) {
        String query = "SELECT COUNT(*) FROM solicitud_renta WHERE clienteId = ? AND DATE_FORMAT(fechaSolicitud, ?) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clienteId);
            stmt.setString(2, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(3, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al obtener cantidad de propiedades rentadas: " + e.getMessage());
            return 0;
        }
    }

    // 2. Suma total pagada a los dueños por las rentas de sus propiedades por período
    public float obtenerSumaTotalPagadaADueños(String periodo) {
        String query = "SELECT SUM(valorDueno) FROM solicitud_renta WHERE DATE_FORMAT(fechaSolicitud, ?) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(2, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getFloat(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al obtener suma total pagada a dueños: " + e.getMessage());
            return 0;
        }
    }

    // 3. Suma y cantidad de rentas por cliente, por período
    public float obtenerSumaRentasPorCliente(int clienteId, String periodo) {
        String query = "SELECT SUM(valorRentaTot) FROM solicitud_renta WHERE clienteId = ? AND DATE_FORMAT(fechaSolicitud, ?) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clienteId);
            stmt.setString(2, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(3, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getFloat(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al obtener suma de rentas por cliente: " + e.getMessage());
            return 0;
        }
    }

    // 4. Número total de rentas por país, departamento, municipio y ubicación
    public int obtenerNumeroTotalRentasPorUbicacion(String filtroUbicacion, String periodo) {
        String query = "SELECT COUNT(*) FROM solicitud_renta sr JOIN propiedad p ON sr.propiedadId = p.propiedadId "
                     + "JOIN ubicacion u ON p.ubicacionId = u.ubicacionId WHERE u.municipio = ? AND DATE_FORMAT(sr.fechaSolicitud, ?) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, filtroUbicacion);
            stmt.setString(2, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(3, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al obtener número total de rentas por ubicación: " + e.getMessage());
            return 0;
        }
    }

    // 5. Reporte de impuestos que deben pagarse por cada renta por período
    public float obtenerTotalImpuestosPorPeriodo(String periodo) {
        String query = "SELECT SUM(impuesto.porcentaje * solicitud_renta.valorRentaTot / 100) FROM solicitud_renta "
                     + "JOIN solicitud_impuesto ON solicitud_renta.solicitudId = solicitud_impuesto.solicitudId "
                     + "JOIN impuesto ON solicitud_impuesto.impuestoId = impuesto.impuestoId WHERE DATE_FORMAT(fechaSolicitud, ?) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(2, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getFloat(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte de impuestos: " + e.getMessage());
            return 0;
        }
    }

    // 6. Tipos de propiedades más rentadas
    public String obtenerTiposPropiedadesMasRentadas(String periodo) {
        String query = "SELECT p.tipo, COUNT(*) AS total FROM solicitud_renta sr JOIN propiedad p ON sr.propiedadId = p.propiedadId "
                     + "WHERE DATE_FORMAT(sr.fechaSolicitud, ?) = ? GROUP BY p.tipo ORDER BY total DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(2, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("tipo") : "No hay datos";
        } catch (SQLException e) {
            System.out.println("Error al obtener tipos de propiedades más rentadas: " + e.getMessage());
            return "Error";
        }
    }

    // 7. Totales de rentas por periodo con impuestos
    public float obtenerTotalRentasConImpuestos(String periodo) {
        return obtenerTotalImpuestosPorPeriodo(periodo) + obtenerSumaTotalPagadaADueños(periodo);
    }

    // 8. Totales de comisiones pagadas por período
    public float obtenerTotalComisionesPorPeriodo(String periodo) {
        String query = "SELECT SUM(comision.valor) FROM solicitud_comision sc JOIN comision ON sc.comisionId = comision.comisionId "
                     + "WHERE DATE_FORMAT(sc.fechaSolicitud, ?) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, periodo.equalsIgnoreCase("mes") ? "%Y-%m" : "%Y");
            stmt.setString(2, periodo);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getFloat(1) : 0;
        } catch (SQLException e) {
            System.out.println("Error al obtener total de comisiones por período: " + e.getMessage());
            return 0;
        }
    }
}

