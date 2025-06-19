package com.veterinaria.modelo;

/**
 * Clase base abstracta que representa una persona en el sistema veterinario.
 * Demuestra el concepto de herencia en POO.
 */
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String telefono;
    protected String email;

    /**
     * Constructor base para todas las personas
     */
    public Persona(String nombre, String apellido, String dni, String telefono, String email) {
        this.nombre = validarTexto(nombre, "Nombre");
        this.apellido = validarTexto(apellido, "Apellido");
        this.dni = validarTexto(dni, "DNI");
        this.telefono = validarTexto(telefono, "Teléfono");
        this.email = validarEmail(email);
    }

    /**
     * Valida que un texto no sea nulo o vacío
     */
    private String validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
        return texto.trim();
    }

    /**
     * Valida el formato básico del email
     */
    private String validarEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email debe tener un formato válido");
        }
        return email.trim();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "Nombre");
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = validarTexto(apellido, "Apellido");
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = validarTexto(dni, "DNI");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = validarTexto(telefono, "Teléfono");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarEmail(email);
    }

    /**
     * Método para obtener el nombre completo
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /**
     * Método abstracto que deben implementar las clases hijas
     * Demuestra polimorfismo
     */
    public abstract String getTipoPersona();

    /**
     * Representación base en string de una persona
     */
    @Override
    public String toString() {
        return String.format("Tipo: %s\nNombre: %s\nApellido: %s\nDNI: %s\nTeléfono: %s\nEmail: %s",
                getTipoPersona(), nombre, apellido, dni, telefono, email);
    }

    /**
     * Método equals para comparar personas por DNI
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return dni.equals(persona.dni);
    }

    /**
     * HashCode basado en el DNI
     */
    @Override
    public int hashCode() {
        return dni.hashCode();
    }
}