package org.example;

import org.example.dao.*;
import org.example.model.Autor;
import org.example.model.Libro;
import org.example.model.Miembro;
import org.example.model.Prestamo;
import org.example.utils.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {
            AutorDAO autorDAO = new AutorDAOImpl(connection);
            Scanner teclado = new Scanner(System.in);
            int opTab;

            do {
                System.out.println("""
                        MENU DE TABLAS
                        1. Autores
                        2. Libros
                        3. Miembros
                        4. Prestamos
                        5. Salir
                        """);
                System.out.println("Seleccione una opcion: ");
                opTab = teclado.nextInt();
                int op1;

                switch (opTab) {
                    case 1 -> {
                        System.out.println("""
                                MENU AUTORES
                                1. Crear
                                2. Leer
                                3. Actualizar
                                4. Eliminar
                                5. Listar
                                0. Salir
                                """);
                        System.out.print("Sseleccione una opcion: ");
                        op1 = teclado.nextInt();
                        teclado.nextLine();

                        switch (op1) {
                            case 1 -> {
                                System.out.print("ID del Autor: ");
                                int id = teclado.nextInt();
                                teclado.nextLine();
                                System.out.print("Nombre: ");
                                String nombre = teclado.nextLine();
                                System.out.print("Apellido: ");
                                String apellido = teclado.nextLine();
                                System.out.print("Nacionalidad: ");
                                String nacionalidad = teclado.nextLine();

                                Autor autor = new Autor(id, nombre, apellido, nacionalidad);
                                autorDAO.crear(autor);
                                System.out.println("Autor registrado.");
                            }
                            case 2 -> {
                                System.out.print("ID del Autor a leer: ");
                                int idLeer = teclado.nextInt();
                                teclado.nextLine();
                                Autor autorLeido = autorDAO.leer(idLeer);
                                if (autorLeido != null) {
                                    System.out.println(autorLeido);
                                } else {
                                    System.out.println("Autor no encontrado.");
                                }
                            }
                            case 3 -> {
                                System.out.print("ID del Autor a actualizar: ");
                                int idActualizar = teclado.nextInt();
                                teclado.nextLine();
                                Autor autorActualizar = autorDAO.leer(idActualizar);
                                if (autorActualizar != null) {
                                    System.out.print("Nuevo nombre: ");
                                    autorActualizar.setNombre(teclado.nextLine());
                                    System.out.print("Nuevo apellido: ");
                                    autorActualizar.setApellido(teclado.nextLine());
                                    System.out.print("Nueva nacionalidad: ");
                                    autorActualizar.setNacionalidad(teclado.nextLine());

                                    autorDAO.actualizar(autorActualizar);
                                    System.out.println("Autor actualizado.");
                                } else {
                                    System.out.println("Autor no encontrado.");
                                }
                            }
                            case 4 -> {
                                System.out.print("ID del Autor a eliminar: ");
                                int idEliminar = teclado.nextInt();
                                teclado.nextLine();
                                autorDAO.eliminar(idEliminar);
                                System.out.println("Autor eliminado.");
                            }
                            case 5 -> {
                                List<Autor> autores = autorDAO.listar();
                                for (Autor a : autores) {
                                    System.out.println(a);
                                }
                            }
                            case 0 -> {
                                System.out.println("Saliendo...");
                            }
                            default -> {
                                System.out.println("Opción no válida.");
                            }
                        }
                    }
                    case 2 -> {
                        LibroDAO libroDAO = new LibroDAOImpl(connection);

                        System.out.println("""
                                1. Registrar
                                2. Leer
                                3. Actualizar
                                4. Eliminar
                                5. Listar 
                                """);

                        int opLib = teclado.nextInt();
                        teclado.nextLine();

                        switch (opLib) {
                            case 1 -> {
                                System.out.print("ID del libro: ");
                                int libroId = teclado.nextInt();
                                teclado.nextLine();
                                System.out.print("Título: ");
                                String titulo = teclado.nextLine();
                                System.out.print("Género: ");
                                String genero = teclado.nextLine();
                                System.out.print("ID del autor: ");
                                int autorId = teclado.nextInt();
                                teclado.nextLine();
                                Libro nuevoLibro = new Libro(libroId, titulo, genero, autorId);
                                libroDAO.crear(nuevoLibro);
                                System.out.println("Libro registrado.");
                            }
                            case 2 -> {
                                System.out.print("ID del libro: ");
                                int idLeer = teclado.nextInt();
                                teclado.nextLine();
                                Libro l = libroDAO.leer(idLeer);
                                System.out.println(l != null ? l : "Libro no encontrado.");
                            }
                            case 3 -> {
                                System.out.print("ID del libro a actualizar: ");
                                int idActualizar = teclado.nextInt();
                                teclado.nextLine();
                                Libro libroExistente = libroDAO.leer(idActualizar);
                                if (libroExistente != null) {
                                    System.out.print("Nuevo título: ");
                                    libroExistente.setTitulo(teclado.nextLine());
                                    System.out.print("Nuevo género: ");
                                    libroExistente.setGenero(teclado.nextLine());
                                    System.out.print("Nuevo ID de autor: ");
                                    libroExistente.setAutorId(teclado.nextInt());
                                    teclado.nextLine();
                                    libroDAO.actualizar(libroExistente);
                                    System.out.println("Libro actualizado.");
                                } else {
                                    System.out.println("Libro no encontrado.");
                                }
                            }
                            case 4 -> {
                                System.out.print("ID del libro a eliminar: ");
                                int idEliminar = teclado.nextInt();
                                teclado.nextLine();
                                libroDAO.eliminar(idEliminar);
                                System.out.println("Libro eliminado.");
                            }
                            case 5 -> {
                                List<Libro> listaLibros = libroDAO.listar();
                                for (Libro libro : listaLibros) {
                                    System.out.println(libro);
                                }
                            }
                            default -> {
                                System.out.println("Opción inválida.");
                            }
                        }
                    }
                    case 3 -> {
                        MiembroDAO miembroDAO = new MiembroDAOImpl(connection);

                        System.out.println("""
                                1. Registrar
                                2. Leer
                                3. Actualizar
                                4. Eliminar
                                5. Listar
                                """);
                        int opMiem = teclado.nextInt();
                        teclado.nextLine();

                        switch (opMiem) {
                            case 1 -> {
                                System.out.print("ID del miembro: ");
                                int id = teclado.nextInt();
                                teclado.nextLine();
                                System.out.print("Nombre: ");
                                String nombre = teclado.nextLine();
                                System.out.print("Apellido: ");
                                String apellido = teclado.nextLine();
                                System.out.print("Fecha de inscripción (YYYY-MM-DD): ");
                                LocalDate fecha = LocalDate.parse(teclado.nextLine());

                                Miembro nuevoMiembro = new Miembro(id, nombre, apellido, fecha);
                                miembroDAO.crear(nuevoMiembro);
                                System.out.println("Miembro registrado.");
                            }
                            case 2 -> {
                                System.out.print("ID del miembro a buscar: ");
                                int idBuscar = teclado.nextInt();
                                teclado.nextLine();
                                Miembro encontrado = miembroDAO.leer(idBuscar);
                                if (encontrado != null) {
                                    System.out.println(encontrado);
                                } else {
                                    System.out.println("Miembro no encontrado.");
                                }
                            }
                            case 3 -> {
                                System.out.print("ID del miembro a actualizar: ");
                                int idActualizar = teclado.nextInt();
                                teclado.nextLine();
                                Miembro miembroActualizar = miembroDAO.leer(idActualizar);
                                if (miembroActualizar != null) {
                                    System.out.print("Nuevo nombre: ");
                                    miembroActualizar.setNombre(teclado.nextLine());
                                    System.out.print("Nuevo apellido: ");
                                    miembroActualizar.setApellido(teclado.nextLine());
                                    System.out.print("Nueva fecha de inscripción (YYYY-MM-DD): ");
                                    miembroActualizar.setFechaInscripcion(LocalDate.parse(teclado.nextLine()));

                                    miembroDAO.actualizar(miembroActualizar);
                                    System.out.println("Miembro actualizado.");
                                } else {
                                    System.out.println("Miembro no encontrado.");
                                }
                            }
                            case 4 -> {
                                System.out.print("ID del miembro a eliminar: ");
                                int idEliminar = teclado.nextInt();
                                teclado.nextLine();
                                miembroDAO.eliminar(idEliminar);
                                System.out.println("Miembro eliminado.");
                            }
                            case 5 -> {
                                List<Miembro> miembros = miembroDAO.listar();
                                for (Miembro m : miembros) {
                                    System.out.println(m);
                                }
                            }
                            default -> {
                                System.out.println("Opción inválida.");
                            }
                        }

                    }
                    case 4 -> {
                        PrestamoDAO prestamoDAO = new PrestamoDAOImpl(connection);
                        int opPres;

                        do {
                            System.out.println("""
                                    MENU PRESTAMOS
                                    1. Registrar
                                    2. Leer
                                    3. Actualizar
                                    4. Eliminar
                                    5. Listar
                                    0. Salir
                                    """);
                            opPres = teclado.nextInt();
                            teclado.nextLine();

                            switch (opPres) {
                                case 1-> {
                                    System.out.print("ID del préstamo: ");
                                    int prestamoId = teclado.nextInt();
                                    System.out.print("ID del libro: ");
                                    int libroId = teclado.nextInt();
                                    System.out.print("ID del miembro: ");
                                    int miembroId = teclado.nextInt();
                                    teclado.nextLine();
                                    System.out.print("Fecha del préstamo (YYYY-MM-DD): ");
                                    LocalDate fechaPrestamo = LocalDate.parse(teclado.nextLine());
                                    System.out.print("Fecha de devolución (YYYY-MM-DD): ");
                                    LocalDate fechaDevolucion = LocalDate.parse(teclado.nextLine());
                                    Prestamo nuevoPrestamo = new Prestamo(prestamoId, libroId, miembroId, fechaPrestamo, fechaDevolucion);
                                    prestamoDAO.crear(nuevoPrestamo);
                                    System.out.println("Préstamo registrado.");
                                }

                                case 2-> {
                                    System.out.print("ID del préstamo a buscar: ");
                                    int buscarId = teclado.nextInt();
                                    teclado.nextLine();
                                    Prestamo prestamo = prestamoDAO.leer(buscarId);
                                    if (prestamo != null) {
                                        System.out.println(prestamo);
                                    } else {
                                        System.out.println("Préstamo no encontrado.");
                                    }
                                }

                                case 3-> {
                                    System.out.print("ID del préstamo a actualizar: ");
                                    int actualizarId = teclado.nextInt();
                                    teclado.nextLine();
                                    Prestamo prestamoExistente = prestamoDAO.leer(actualizarId);
                                    if (prestamoExistente != null) {
                                        System.out.print("Nuevo ID del libro: ");
                                        prestamoExistente.setLibroId(teclado.nextInt());
                                        System.out.print("Nuevo ID del miembro: ");
                                        prestamoExistente.setMiembroId(teclado.nextInt());
                                        teclado.nextLine();
                                        System.out.print("Nueva fecha de préstamo (YYYY-MM-DD): ");
                                        prestamoExistente.setFechaPrestamo(LocalDate.parse(teclado.nextLine()));
                                        System.out.print("Nueva fecha de devolución (YYYY-MM-DD): ");
                                        prestamoExistente.setFechaDevolucion(LocalDate.parse(teclado.nextLine()));

                                        prestamoDAO.actualizar(prestamoExistente);
                                        System.out.println("Préstamo actualizado.");
                                    } else {
                                        System.out.println("Préstamo no encontrado.");
                                    }
                                }

                                case 4-> {
                                    System.out.print("ID del préstamo a eliminar: ");
                                    int eliminarId = teclado.nextInt();
                                    teclado.nextLine();
                                    prestamoDAO.eliminar(eliminarId);
                                    System.out.println("Préstamo eliminado.");
                                }

                                case 5-> {
                                    List<Prestamo> prestamos = prestamoDAO.listar();
                                    for (Prestamo p : prestamos) {
                                        System.out.println(p);
                                    }
                                }

                                case 0->{
                                    System.out.println("Saliendo del sistema de préstamos...");
                                }
                                default->{
                                    System.out.println("Opción no válida.");
                                }
                            }
                        } while (opPres !=0);

                    }
                    case 5->{
                        System.out.println("Saliendo...");
                    }
                }

            } while (opTab !=0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}