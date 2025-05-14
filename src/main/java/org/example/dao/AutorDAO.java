package org.example.dao;

import org.example.model.Autor;

import java.util.List;

public interface AutorDAO {
    void crear(Autor autor);
    Autor leer(int autorId);
    void actualizar(Autor autor);
    void eliminar(int autorId);
    List<Autor> listar();
}