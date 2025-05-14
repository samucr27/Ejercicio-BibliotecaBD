package org.example.dao;

import org.example.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    private final Connection connection;

    public LibroDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Libro libro) {
        String sql = "INSERT INTO libros (libro_id, titulo, genero, autor_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, libro.getLibroId());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getGenero());
            stmt.setInt(4, libro.getAutorId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Libro leer(int id) {
        String sql = "SELECT * FROM libros WHERE libro_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("autor_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, genero = ?, autor_id = ? WHERE libro_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getGenero());
            stmt.setInt(3, libro.getAutorId());
            stmt.setInt(4, libro.getLibroId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM libros WHERE libro_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> listar() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("autor_id")
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
