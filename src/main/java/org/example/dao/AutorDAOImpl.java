package org.example.dao;

import org.example.model.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {
    private final Connection connection;

    // Constructor
    public AutorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Autor autor) {
        String sql = "INSERT INTO autores (autor_id, nombre, apellido, nacionalidad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autor.getAutorId());
            statement.setString(2, autor.getNombre());
            statement.setString(3, autor.getApellido());
            statement.setString(4, autor.getNacionalidad());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Autor leer(int autorId) {
        String sql = "SELECT * FROM autores WHERE autor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autorId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Autor(
                        rs.getInt("autor_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("nacionalidad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Autor autor) {
        String sql = "UPDATE autores SET nombre = ?, apellido = ?, nacionalidad = ? WHERE autor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, autor.getNombre());
            statement.setString(2, autor.getApellido());
            statement.setString(3, autor.getNacionalidad());
            statement.setInt(4, autor.getAutorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int autorId) {
        String sql = "DELETE FROM autores WHERE autor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Autor> listar() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                autores.add(new Autor(
                        rs.getInt("autor_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("nacionalidad")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}
