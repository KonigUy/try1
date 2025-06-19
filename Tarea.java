package com.veterinaria.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Tarea que representa una tarea administrativa.
 * Demuestra encapsulación y composición.
 */
public class Tarea {
    private static int contadorTareas = 0;
    
    private int id;
    private String descripcion;
    private String estado; // Pendiente, En Proceso, Completada
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCompletada;
    private Administrativo asignadoA;

    /**
     * Constructor de Tarea
     */
    public Tarea(String descripcion, String estado) {
        this.id = ++contadorTareas;
        this.descripcion = validarDescripcion(descripcion);
        this.estado = validarEstado(estado);
        this.fechaCreacion = LocalDateTime.now();
        this.fechaCompletada = null;
    }

    /**
     * Constructor de Tarea con estado por defecto "Pendiente"
     */
    public Tarea(String descripcion) {
        this(descripcion, "Pendiente");
    }

    /**
     * Valida que la descripción no sea nula o vacía
     */
    private String validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la tarea no puede estar vacía");
        }
        return descripcion.trim();
    }

    /**
     * Valida que el estado sea válido
     */
    private String validarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la tarea no puede estar vacío");
        }
        
        String estadoNormalizado = estado.trim().toLowerCase();
        if (!estadoNormalizado.equals("pendiente") && 
            !estadoNormalizado.equals("en proceso") && 
            !estadoNormalizado.equals("completada")) {
            throw new IllegalArgumentException("El estado debe ser: Pendiente, En Proceso o Completada");
        }
        
        // Retornar con formato correcto
        switch (estadoNormalizado) {
            case "pendiente":
                return "Pendiente";
            case "en proceso":
                return "En Proceso";
            case "completada":
                return "Completada";
            default:
                return "Pendiente";
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = validarDescripcion(descripcion);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        String nuevoEstado = validarEstado(estado);
        
        // Si se marca como completada, establecer fecha de completado
        if (nuevoEstado.equals("Completada") && !this.estado.equals("Completada")) {
            this.fechaCompletada = LocalDateTime.now();
        }
        // Si se cambia de completada a otro estado, limpiar fecha de completado
        else if (!nuevoEstado.equals("Completada") && this.estado.equals("Completada")) {
            this.fechaCompletada = null;
        }
        
        this.estado = nuevoEstado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaCompletada() {
        return fechaCompletada;
    }

    public Administrativo getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(Administrativo asignadoA) {
        this.asignadoA = asignadoA;
    }

    /**
     * Obtiene la fecha de creación formateada
     */
    public String getFechaCreacionFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaCreacion.format(formatter);
    }

    /**
     * Obtiene la fecha de completado formateada
     */
    public String getFechaCompletadaFormateada() {
        if (fechaCompletada == null) {
            return "No completada";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaCompletada.format(formatter);
    }

    /**
     * Marca la tarea como completada
     */
    public void completar() {
        setEstado("Completada");
    }

    /**
     * Marca la tarea como en proceso
     */
    public void iniciar() {
        if (estado.equals("Pendiente")) {
            setEstado("En Proceso");
        }
    }

    /**
     * Verifica si la tarea está pendiente
     */
    public boolean estaPendiente() {
        return estado.equals("Pendiente");
    }

    /**
     * Verifica si la tarea está en proceso
     */
    public boolean estaEnProceso() {
        return estado.equals("En Proceso");
    }

    /**
     * Verifica si la tarea está completada
     */
    public boolean estaCompletada() {
        return estado.equals("Completada");
    }

    /**
     * Obtiene el nombre del administrativo asignado
     */
    public String getNombreAsignado() {
        return asignadoA != null ? asignadoA.getNombreCompleto() : "Sin asignar";
    }

    /**
     * Calcula los días transcurridos desde la creación
     */
    public long getDiasTranscurridos() {
        return java.time.temporal.ChronoUnit.DAYS.between(
            fechaCreacion.toLocalDate(), 
            LocalDateTime.now().toLocalDate()
        );
    }

    /**
     * Verifica si la tarea es urgente (más de 7 días pendiente)
     */
    public boolean esUrgente() {
        return estaPendiente() && getDiasTranscurridos() > 7;
    }

    /**
     * Obtiene un resumen corto de la tarea
     */
    public String getResumenCorto() {
        return String.format("Tarea #%d - %s - %s - %s", 
                           id, 
                           descripcion.length() > 30 ? 
                               descripcion.substring(0, 30) + "..." : descripcion,
                           estado,
                           getNombreAsignado());
    }

    /**
     * Obtiene información detallada de la tarea
     */
    public String getInformacionDetallada() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TAREA ADMINISTRATIVA ===\n");
        sb.append(String.format("ID: %d\n", id));
        sb.append(String.format("Descripción: %s\n", descripcion));
        sb.append(String.format("Estado: %s\n", estado));
        sb.append(String.format("Asignada a: %s\n", getNombreAsignado()));
        sb.append(String.format("Fecha de creación: %s\n", getFechaCreacionFormateada()));
        sb.append(String.format("Fecha de completado: %s\n", getFechaCompletadaFormateada()));
        sb.append(String.format("Días transcurridos: %d\n", getDiasTranscurridos()));
        
        if (esUrgente()) {
            sb.append("*** TAREA URGENTE ***");
        }
        
        return sb.toString();
    }

    /**
     * Obtiene el contador total de tareas
     */
    public static int getTotalTareas() {
        return contadorTareas;
    }

    /**
     * Representación en string de la tarea
     */
    @Override
    public String toString() {
        return getInformacionDetallada();
    }

    /**
     * Método equals para comparar tareas por ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return id == tarea.id;
    }

    /**
     * HashCode basado en el ID
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}