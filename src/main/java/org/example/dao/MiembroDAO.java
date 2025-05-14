package org.example.dao;

import org.example.model.Miembro;

import java.util.List;

public interface MiembroDAO {
    void crear(Miembro miembro);
    Miembro leer(int miembroId);
    void actualizar(Miembro miembro);
    void eliminar(int miembroId);
    List<Miembro> listar();
}
