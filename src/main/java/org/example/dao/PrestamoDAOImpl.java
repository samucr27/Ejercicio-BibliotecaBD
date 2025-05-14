package org.example.dao;

import org.example.model.Prestamo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {
    private final Connection connection;

    public PrestamoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (prestamo_id, libro_id, miembro_id, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getPrestamoId());
            stmt.setInt(2, prestamo.getLibroId());
            stmt.setInt(3, prestamo.getMiembroId());
            stmt.setDate(4, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucion()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prestamo leer(int id) {
        String sql = "SELECT * FROM prestamos WHERE prestamo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prestamo(
                        rs.getInt("prestamo_id"),
                        rs.getInt("libro_id"),
                        rs.getInt("miembro_id"),
                        rs.getDate("fecha_prestamo").toLocalDate(),
                        rs.getDate("fecha_devolucion").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET libro_id = ?, miembro_id = ?, fecha_prestamo = ?, fecha_devolucion = ? WHERE prestamo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getLibroId());
            stmt.setInt(2, prestamo.getMiembroId());
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(4, Date.valueOf(prestamo.getFechaDevolucion()));
            stmt.setInt(5, prestamo.getPrestamoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM prestamos WHERE prestamo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> listar() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prestamo p = new Prestamo(
                        rs.getInt("prestamo_id"),
                        rs.getInt("libro_id"),
                        rs.getInt("miembro_id"),
                        rs.getDate("fecha_prestamo").toLocalDate(),
                        rs.getDate("fecha_devolucion").toLocalDate()
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
