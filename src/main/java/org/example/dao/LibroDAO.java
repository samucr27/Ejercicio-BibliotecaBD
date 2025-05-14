package org.example.dao;

import org.example.model.Libro;
import java.util.List;

public interface LibroDAO {
    void crear(Libro libro);
    Libro leer(int id);
    void actualizar(Libro libro);
    void eliminar(int id);
    List<Libro> listar();
}
