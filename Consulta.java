package com.veterinaria.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Consulta que representa una consulta veterinaria.
 * Demuestra composición al relacionar Cliente, Mascota y Veterinario.
 */
public class Consulta {
    private static int contadorConsultas = 0;
    
    private int id;
    private Cliente cliente;
    private Mascota mascota;
    private Veterinario veterinario;
    private String tratamiento;
    private String diagnostico;
    private LocalDateTime fechaConsulta;

    /**
     * Constructor de Consulta
     */
    public Consulta(Cliente cliente, Mascota mascota, Veterinario veterinario, 
                   String tratamiento, String diagnostico) {
        this.id = ++contadorConsultas;
        this.cliente = validarCliente(cliente);
        this.mascota = validarMascota(mascota, cliente);
        this.veterinario = validarVeterinario(veterinario);
        this.tratamiento = validarTratamiento(tratamiento);
        this.diagnostico = validarDiagnostico(diagnostico);
        this.fechaConsulta = LocalDateTime.now();
    }

    /**
     * Valida que el cliente no sea nulo
     */
    private Cliente validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        return cliente;
    }

    /**
     * Valida que la mascota no sea nula y pertenezca al cliente
     */
    private Mascota validarMascota(Mascota mascota, Cliente cliente) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        
        // Verificar que la mascota pertenece al cliente
        boolean mascotaPertenece = false;
        for (Mascota m : cliente.getMascotas()) {
            if (m.equals(mascota)) {
                mascotaPertenece = true;
                break;
            }
        }
        
        if (!mascotaPertenece) {
            throw new IllegalArgumentException("La mascota no pertenece al cliente especificado");
        }
        
        return mascota;
    }

    /**
     * Valida que el veterinario no sea nulo
     */
    private Veterinario validarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        return veterinario;
    }

    /**
     * Valida que el tratamiento no sea nulo o vacío
     */
    private String validarTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tratamiento no puede estar vacío");
        }
        return tratamiento.trim();
    }

    /**
     * Valida que el diagnóstico no sea nulo o vacío
     */
    private String validarDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico no puede estar vacío");
        }
        return diagnostico.trim();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = validarCliente(cliente);
        // Validar nuevamente la mascota con el nuevo cliente
        this.mascota = validarMascota(this.mascota, cliente);
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = validarMascota(mascota, this.cliente);
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = validarVeterinario(veterinario);
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = validarTratamiento(tratamiento);
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = validarDiagnostico(diagnostico);
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * Obtiene la fecha formateada de la consulta
     */
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaConsulta.format(formatter);
    }

    /**
     * Obtiene un resumen corto de la consulta
     */
    public String getResumenCorto() {
        return String.format("Consulta #%d - %s (%s) - Dr. %s - %s", 
                           id, 
                           mascota.getNombre(), 
                           mascota.getTipoMascota(),
                           veterinario.getNombreCompleto(),
                           getFechaFormateada());
    }

    /**
     * Obtiene información detallada de la consulta
     */
    public String getInformacionDetallada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONSULTA VETERINARIA ===\n");
        sb.append(String.format("ID: %d\n", id));
        sb.append(String.format("Fecha: %s\n", getFechaFormateada()));
        sb.append(String.format("Cliente: %s (DNI: %s)\n", 
                                cliente.getNombreCompleto(), cliente.getDni()));
        sb.append(String.format("Mascota: %s (%s, %s)\n", 
                                mascota.getNombre(), mascota.getTipoMascota(), mascota.getRaza()));
        sb.append(String.format("Veterinario: Dr. %s (%s)\n", 
                                veterinario.getNombreCompleto(), veterinario.getEspecialidad()));
        sb.append(String.format("Tratamiento: %s\n", tratamiento));
        sb.append(String.format("Diagnóstico: %s", diagnostico));
        
        return sb.toString();
    }

    /**
     * Verifica si la consulta es reciente (últimas 24 horas)
     */
    public boolean esReciente() {
        return fechaConsulta.isAfter(LocalDateTime.now().minusDays(1));
    }

    /**
     * Verifica si la consulta es del día actual
     */
    public boolean esDeHoy() {
        return fechaConsulta.toLocalDate().equals(LocalDateTime.now().toLocalDate());
    }

    /**
     * Obtiene el contador total de consultas
     */
    public static int getTotalConsultas() {
        return contadorConsultas;
    }

    /**
     * Representación en string de la consulta
     */
    @Override
    public String toString() {
        return getInformacionDetallada();
    }

    /**
     * Método equals para comparar consultas por ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Consulta consulta = (Consulta) obj;
        return id == consulta.id;
    }

    /**
     * HashCode basado en el ID
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}