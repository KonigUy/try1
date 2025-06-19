package com.veterinaria;

import com.veterinaria.modelo.*;
import com.veterinaria.servicio.VeterinariaService;
import com.veterinaria.util.ConsoleHelper;

/**
 * Clase principal sistema de gestión de veterinaria
 * con conceptos de programación orientada a objetos
 */
public class Main {
    private static VeterinariaService veterinariaService = new VeterinariaService();
    private static ConsoleHelper console = new ConsoleHelper();

    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión Veterinaria ===");
        System.out.println("Demostrando conceptos de POO: Herencia, Composición y Polimorfismo\n");

        // Demostrar funcionalidades del sistema
        demonstrarSistema();
        
        // Menú interactivo
        mostrarMenuPrincipal();
    }

    /**
     * Demuestra las funcionalidades básicas del sistema
     */
    private static void demonstrarSistema() {
        System.out.println("--- Demostración del Sistema ---\n");

        // Crear mascotas (Polimorfismo)
        Mascota perro1 = new Perro("Rex", 3, "Golden Retriever", "Mediano");
        Mascota gato1 = new Gato("Miau", 2, "Persa", true);
        
        // Crear cliente y agregar mascotas (Composición)
        Cliente cliente1 = new Cliente("Juan", "Pérez", "12345678", "555-0123", "juan@email.com");
        cliente1.agregarMascota(perro1);
        cliente1.agregarMascota(gato1);
        
        // Crear veterinario
        Veterinario vet1 = new Veterinario("Dra. María", "García", "87654321", "555-0456", "maria@vet.com", "Medicina General");
        
        // Crear administrativo
        Administrativo admin1 = new Administrativo("Carlos", "López", "11223344", "555-0789", "carlos@admin.com", "Recepción");
        
        // Agregar al servicio
        veterinariaService.agregarCliente(cliente1);
        veterinariaService.agregarVeterinario(vet1);
        veterinariaService.agregarAdministrativo(admin1);
        
        // Crear consulta
        Consulta consulta1 = new Consulta(cliente1, perro1, vet1, "Vacunación anual", "Animal saludable");
        vet1.agregarConsulta(consulta1);
        
        // Crear tarea administrativa
        Tarea tarea1 = new Tarea("Actualizar expedientes", "Pendiente");
        admin1.agregarTarea(tarea1);
        
        // Mostrar información (Herencia - toString() polimórfico)
        System.out.println("CLIENTE REGISTRADO:");
        System.out.println(cliente1);
        System.out.println("\nVETERINARIO REGISTRADO:");
        System.out.println(vet1);
        System.out.println("\nADMINISTRATIVO REGISTRADO:");
        System.out.println(admin1);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    /**
     * Muestra el menú principal del sistema
     */
    private static void mostrarMenuPrincipal() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("--- Menú Principal ---");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Veterinarios");
            System.out.println("3. Gestionar Administrativos");
            System.out.println("4. Gestionar Mascotas");
            System.out.println("5. Gestionar Consultas");
            System.out.println("6. Mostrar Resumen del Sistema");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = console.leerEntero();
            
            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuVeterinarios();
                    break;
                case 3:
                    menuAdministrativos();
                    break;
                case 4:
                    menuMascotas();
                    break;
                case 5:
                    menuConsultas();
                    break;
                case 6:
                    mostrarResumenSistema();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("¡Gracias por usar el Sistema de Gestión Veterinaria!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            
            if (continuar) {
                System.out.println("\nPresione Enter para continuar...");
                console.leerLinea();
                System.out.println();
            }
        }
    }

    private static void menuClientes() {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Buscar Cliente");
        System.out.print("Seleccione una opción: ");
        
        int opcion = console.leerEntero();
        
        switch (opcion) {
            case 1:
                agregarCliente();
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                buscarCliente();
                break;
        }
    }

    private static void menuVeterinarios() {
        System.out.println("\n--- Gestión de Veterinarios ---");
        System.out.println("1. Agregar Veterinario");
        System.out.println("2. Listar Veterinarios");
        System.out.print("Seleccione una opción: ");
        
        int opcion = console.leerEntero();
        
        switch (opcion) {
            case 1:
                agregarVeterinario();
                break;
            case 2:
                listarVeterinarios();
                break;
        }
    }

    private static void menuAdministrativos() {
        System.out.println("\n--- Gestión de Administrativos ---");
        System.out.println("1. Agregar Administrativo");
        System.out.println("2. Listar Administrativos");
        System.out.print("Seleccione una opción: ");
        
        int opcion = console.leerEntero();
        
        switch (opcion) {
            case 1:
                agregarAdministrativo();
                break;
            case 2:
                listarAdministrativos();
                break;
        }
    }

    private static void menuMascotas() {
        System.out.println("\n--- Gestión de Mascotas ---");
        System.out.println("1. Agregar Mascota a Cliente");
        System.out.println("2. Listar Mascotas por Cliente");
        System.out.print("Seleccione una opción: ");
        
        int opcion = console.leerEntero();
        
        switch (opcion) {
            case 1:
                agregarMascotaACliente();
                break;
            case 2:
                listarMascotasPorCliente();
                break;
        }
    }

    private static void menuConsultas() {
        System.out.println("\n--- Gestión de Consultas ---");
        System.out.println("1. Crear Consulta");
        System.out.println("2. Listar Consultas por Veterinario");
        System.out.print("Seleccione una opción: ");
        
        int opcion = console.leerEntero();
        
        switch (opcion) {
            case 1:
                crearConsulta();
                break;
            case 2:
                listarConsultasPorVeterinario();
                break;
        }
    }

    private static void agregarCliente() {
        System.out.print("Nombre: ");
        String nombre = console.leerLinea();
        System.out.print("Apellido: ");
        String apellido = console.leerLinea();
        System.out.print("DNI: ");
        String dni = console.leerLinea();
        System.out.print("Teléfono: ");
        String telefono = console.leerLinea();
        System.out.print("Email: ");
        String email = console.leerLinea();
        
        Cliente cliente = new Cliente(nombre, apellido, dni, telefono, email);
        veterinariaService.agregarCliente(cliente);
        System.out.println("Cliente agregado exitosamente.");
    }

    private static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        veterinariaService.listarClientes();
    }

    private static void buscarCliente() {
        System.out.print("Ingrese DNI del cliente: ");
        String dni = console.leerLinea();
        Cliente cliente = veterinariaService.buscarClientePorDni(dni);
        if (cliente != null) {
            System.out.println("\nCliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void agregarVeterinario() {
        System.out.print("Nombre: ");
        String nombre = console.leerLinea();
        System.out.print("Apellido: ");
        String apellido = console.leerLinea();
        System.out.print("DNI: ");
        String dni = console.leerLinea();
        System.out.print("Teléfono: ");
        String telefono = console.leerLinea();
        System.out.print("Email: ");
        String email = console.leerLinea();
        System.out.print("Especialidad: ");
        String especialidad = console.leerLinea();
        
        Veterinario veterinario = new Veterinario(nombre, apellido, dni, telefono, email, especialidad);
        veterinariaService.agregarVeterinario(veterinario);
        System.out.println("Veterinario agregado exitosamente.");
    }

    private static void listarVeterinarios() {
        System.out.println("\n--- Lista de Veterinarios ---");
        veterinariaService.listarVeterinarios();
    }

    private static void agregarAdministrativo() {
        System.out.print("Nombre: ");
        String nombre = console.leerLinea();
        System.out.print("Apellido: ");
        String apellido = console.leerLinea();
        System.out.print("DNI: ");
        String dni = console.leerLinea();
        System.out.print("Teléfono: ");
        String telefono = console.leerLinea();
        System.out.print("Email: ");
        String email = console.leerLinea();
        System.out.print("Área: ");
        String area = console.leerLinea();
        
        Administrativo administrativo = new Administrativo(nombre, apellido, dni, telefono, email, area);
        veterinariaService.agregarAdministrativo(administrativo);
        System.out.println("Administrativo agregado exitosamente.");
    }

    private static void listarAdministrativos() {
        System.out.println("\n--- Lista de Administrativos ---");
        veterinariaService.listarAdministrativos();
    }

    private static void agregarMascotaACliente() {
        System.out.print("DNI del cliente: ");
        String dniCliente = console.leerLinea();
        Cliente cliente = veterinariaService.buscarClientePorDni(dniCliente);
        
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.println("Tipo de mascota:");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.print("Seleccione: ");
        int tipo = console.leerEntero();
        
        System.out.print("Nombre de la mascota: ");
        String nombre = console.leerLinea();
        System.out.print("Edad: ");
        int edad = console.leerEntero();
        System.out.print("Raza: ");
        String raza = console.leerLinea();
        
        Mascota mascota;
        if (tipo == 1) {
            System.out.print("Tamaño (Pequeño/Mediano/Grande): ");
            String tamano = console.leerLinea();
            mascota = new Perro(nombre, edad, raza, tamano);
        } else if (tipo == 2) {
            System.out.print("¿Es de interior? (true/false): ");
            boolean esDeInterior = console.leerBooleano();
            mascota = new Gato(nombre, edad, raza, esDeInterior);
        } else {
            System.out.println("Tipo de mascota no válido.");
            return;
        }
        
        cliente.agregarMascota(mascota);
        System.out.println("Mascota agregada exitosamente al cliente.");
    }

    private static void listarMascotasPorCliente() {
        System.out.print("DNI del cliente: ");
        String dniCliente = console.leerLinea();
        Cliente cliente = veterinariaService.buscarClientePorDni(dniCliente);
        
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.println("\nMascotas de " + cliente.getNombre() + " " + cliente.getApellido() + ":");
        cliente.listarMascotas();
    }

    private static void crearConsulta() {
        // Buscar cliente
        System.out.print("DNI del cliente: ");
        String dniCliente = console.leerLinea();
        Cliente cliente = veterinariaService.buscarClientePorDni(dniCliente);
        
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        // Mostrar mascotas del cliente
        if (cliente.getMascotas().isEmpty()) {
            System.out.println("El cliente no tiene mascotas registradas.");
            return;
        }
        
        System.out.println("Mascotas del cliente:");
        for (int i = 0; i < cliente.getMascotas().size(); i++) {
            System.out.println((i + 1) + ". " + cliente.getMascotas().get(i).getNombre());
        }
        
        System.out.print("Seleccione la mascota (número): ");
        int indiceMascota = console.leerEntero() - 1;
        
        if (indiceMascota < 0 || indiceMascota >= cliente.getMascotas().size()) {
            System.out.println("Selección no válida.");
            return;
        }
        
        Mascota mascota = cliente.getMascotas().get(indiceMascota);
        
        // Buscar veterinario
        System.out.print("DNI del veterinario: ");
        String dniVeterinario = console.leerLinea();
        Veterinario veterinario = veterinariaService.buscarVeterinarioPorDni(dniVeterinario);
        
        if (veterinario == null) {
            System.out.println("Veterinario no encontrado.");
            return;
        }
        
        // Datos de la consulta
        System.out.print("Tratamiento: ");
        String tratamiento = console.leerLinea();
        System.out.print("Diagnóstico: ");
        String diagnostico = console.leerLinea();
        
        Consulta consulta = new Consulta(cliente, mascota, veterinario, tratamiento, diagnostico);
        veterinario.agregarConsulta(consulta);
        
        System.out.println("Consulta creada exitosamente.");
    }

    private static void listarConsultasPorVeterinario() {
        System.out.print("DNI del veterinario: ");
        String dniVeterinario = console.leerLinea();
        Veterinario veterinario = veterinariaService.buscarVeterinarioPorDni(dniVeterinario);
        
        if (veterinario == null) {
            System.out.println("Veterinario no encontrado.");
            return;
        }
        
        System.out.println("\nConsultas del Dr. " + veterinario.getNombre() + " " + veterinario.getApellido() + ":");
        veterinario.listarConsultas();
    }

    private static void mostrarResumenSistema() {
        System.out.println("\n=== RESUMEN DEL SISTEMA ===");
        veterinariaService.mostrarResumen();
    }
}