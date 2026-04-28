package com.bibliotech;

import com.bibliotech.model.*;
import com.bibliotech.repository.*;
import com.bibliotech.service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Repos
        Repository<Libro, String> libroRepo = new LibroRepositeryImpl();
        Repository<Socio, Integer> socioRepo = new SocioRepositeryImpl();
        Repository<Prestamo, String> prestamoRepo = new PrestamoRepositoryImpl();

        // Services
        LibroService libroService = new LibroService(libroRepo);
        SocioService socioService = new SocioService(socioRepo);
        PrestamoService prestamoService = new PrestamoService(libroRepo, socioRepo, prestamoRepo);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Agregar socio");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Listar libros");
            System.out.println("6. Listar socios");
            System.out.println("0. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            try {
                switch (opcion) {

                    case 1 -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Titulo: ");
                        String titulo = scanner.nextLine();

                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();

                        System.out.print("Año: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Categoria: ");
                        String categoria = scanner.nextLine();

                        libroService.agregarLibro(
                                new Libro(isbn, titulo, autor, anio, categoria));
                    }

                    case 2 -> {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Mail: ");
                        String mail = scanner.nextLine();

                        System.out.print("Tipo (1=Estudiante, 2=Docente): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        Socio socio = (tipo == 1)
                                ? new Estudiante(id, nombre, mail)
                                : new Docente(id, nombre, mail);

                        socioService.agregarSocio(socio);
                    }

                    case 3 -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("ID socio: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        prestamoService.realizarPrestamo(isbn, id);
                    }

                    case 4 -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("ID socio: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        prestamoService.devolverLibro(isbn, id);
                    }

                    case 5 -> libroService.listarLibros().forEach(System.out::println);

                    case 6 -> socioService.listarSocios().forEach(System.out::println);
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);

        scanner.close();
    }
}