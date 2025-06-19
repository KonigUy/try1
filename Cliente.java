package com.veterinaria.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cliente que hereda de Persona.
 * Demuestra herencia y composición (tiene una lista de mascotas).
 */
public class Cliente extends Persona {
    private List<Mascota> mascotas;

    /**
     * Constructor de Cliente
     */
    public Cliente(String nombre, String apellido, String dni, String telefono, String email) {
        super(nombre, apellido, dni, telefono, email);
        this.mascotas = new ArrayList<>();
    }

    /**
     * Implementación del método abstracto de Persona
     */
    @Override
    public String getTipoPersona() {
        return "Cliente";
    }

    /**
     * Agrega una mascota al cliente
     */
    public void agregarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        
        // Verificar que la mascota no esté ya registrada
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(mascota.getNombre())) {
                throw new IllegalArgumentException("Ya existe una mascota con el nombre: " + mascota.getNombre());
            }
        }
        
        mascotas.add(mascota);
        mascota.setPropietario(this); // Establecer la relación bidireccional
    }

    /**
     * Elimina una mascota del cliente
     */
    public boolean eliminarMascota(String nombreMascota) {
        return mascotas.removeIf(mascota -> mascota.getNombre().equalsIgnoreCase(nombreMascota));
    }

    /**
     * Busca una mascota por nombre
     */
    public Mascota buscarMascota(String nombre) {
        for (Mascota mascota : mascotas) {
            if (mascota.getNombre().equalsIgnoreCase(nombre)) {
                return mascota;
            }
        }
        return null;
    }

    /**
     * Lista todas las mascotas del cliente
     */
    public void listarMascotas() {
        if (mascotas.isEmpty()) {
            System.out.println("Este cliente no tiene mascotas registradas.");
            return;
        }
        
        System.out.println("Mascotas de " + getNombreCompleto() + ":");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i));
            System.out.println("---");
        }
    }

    /**
     * Obtiene la cantidad de mascotas
     */
    public int getCantidadMascotas() {
        return mascotas.size();
    }

    /**
     * Getter para la lista de mascotas
     */
    public List<Mascota> getMascotas() {
        return new ArrayList<>(mascotas); // Retorna una copia para evitar modificaciones externas
    }

    /**
     * Verifica si el cliente tiene mascotas
     */
    public boolean tieneMascotas() {
        return !mascotas.isEmpty();
    }

    /**
     * Representación en string del cliente incluyendo sus mascotas
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("\nCantidad de mascotas: %d", mascotas.size()));
        
        if (!mascotas.isEmpty()) {
            sb.append("\nMascotas:");
            for (Mascota mascota : mascotas) {
                sb.append("\n  - ").append(mascota.getNombre())
                  .append(" (").append(mascota.getClass().getSimpleName()).append(")");
            }
        }
        
        return sb.toString();
    }
}