package com.veterinaria.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Administrativo que hereda de Persona.
 * Demuestra herencia y composición (tiene una lista de tareas).
 */
public class Administrativo extends Persona {
    private String area;
    private List<Tarea> tareas;

    /**
     * Constructor de Administrativo
     */
    public Administrativo(String nombre, String apellido, String dni, String telefono, String email, String area) {
        super(nombre, apellido, dni, telefono, email);
        this.area = validarArea(area);
        this.tareas = new ArrayList<>();
    }

    /**
     * Valida que el área no sea nula o vacía
     */
    private String validarArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("El área no puede estar vacía");
        }
        return area.trim();
    }

    /**
     * Implementación del método abstracto de Persona
     */
    @Override
    public String getTipoPersona() {
        return "Administrativo";
    }

    /**
     * Agrega una tarea al administrativo
     */
    public void agregarTarea(Tarea tarea) {
        if (tarea == null) {
            throw new IllegalArgumentException("La tarea no puede ser nula");
        }
        
        // Verificar que no exista una tarea con la misma descripción
        for (Tarea t : tareas) {
            if (t.getDescripcion().equalsIgnoreCase(tarea.getDescripcion())) {
                throw new IllegalArgumentException("Ya existe una tarea con esa descripción");
            }
        }
        
        tareas.add(tarea);
        tarea.setAsignadoA(this); // Establecer la relación
    }

    /**
     * Elimina una tarea por descripción
     */
    public boolean eliminarTarea(String descripcion) {
        return tareas.removeIf(tarea -> tarea.getDescripcion().equalsIgnoreCase(descripcion));
    }

    /**
     * Busca una tarea por descripción
     */
    public Tarea buscarTarea(String descripcion) {
        for (Tarea tarea : tareas) {
            if (tarea.getDescripcion().equalsIgnoreCase(descripcion)) {
                return tarea;
            }
        }
        return null;
    }

    /**
     * Marca una tarea como completada
     */
    public boolean completarTarea(String descripcion) {
        Tarea tarea = buscarTarea(descripcion);
        if (tarea != null) {
            tarea.setEstado("Completada");
            return true;
        }
        return false;
    }

    /**
     * Lista todas las tareas del administrativo
     */
    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("Este administrativo no tiene tareas asignadas.");
            return;
        }
        
        System.out.println("Tareas de " + getNombreCompleto() + ":");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
            System.out.println("---");
        }
    }

    /**
     * Obtiene las tareas por estado
     */
    public List<Tarea> getTareasPorEstado(String estado) {
        List<Tarea> tareasFiltradas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equalsIgnoreCase(estado)) {
                tareasFiltradas.add(tarea);
            }
        }
        return tareasFiltradas;
    }

    /**
     * Obtiene la cantidad de tareas pendientes
     */
    public int getTareasPendientes() {
        return getTareasPorEstado("Pendiente").size();
    }

    /**
     * Obtiene la cantidad de tareas completadas
     */
    public int getTareasCompletadas() {
        return getTareasPorEstado("Completada").size();
    }

    /**
     * Obtiene la cantidad total de tareas
     */
    public int getCantidadTareas() {
        return tareas.size();
    }

    // Getters y Setters
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = validarArea(area);
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas); // Retorna una copia para evitar modificaciones externas
    }

    /**
     * Verifica si el administrativo tiene tareas
     */
    public boolean tieneTareas() {
        return !tareas.isEmpty();
    }

    /**
     * Representación en string del administrativo incluyendo su información
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("\nÁrea: %s", area));
        sb.append(String.format("\nTareas asignadas: %d", tareas.size()));
        sb.append(String.format("\nTareas pendientes: %d", getTareasPendientes()));
        sb.append(String.format("\nTareas completadas: %d", getTareasCompletadas()));
        
        return sb.toString();
    }
}