package com.veterinaria.modelo;

/**
 * Clase base abstracta que representa una mascota en el sistema veterinario.
 * Demuestra el concepto de herencia y polimorfismo en POO.
 */
public abstract class Mascota {
    protected String nombre;
    protected int edad;
    protected String raza;
    protected Cliente propietario;

    /**
     * Constructor base para todas las mascotas
     */
    public Mascota(String nombre, int edad, String raza) {
        this.nombre = validarNombre(nombre);
        this.edad = validarEdad(edad);
        this.raza = validarRaza(raza);
    }

    /**
     * Valida que el nombre no sea nulo o vacío
     */
    private String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vacío");
        }
        return nombre.trim();
    }

    /**
     * Valida que la edad sea positiva
     */
    private int validarEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (edad > 30) {
            throw new IllegalArgumentException("La edad no puede ser mayor a 30 años");
        }
        return edad;
    }

    /**
     * Valida que la raza no sea nula o vacía
     */
    private String validarRaza(String raza) {
        if (raza == null || raza.trim().isEmpty()) {
            throw new IllegalArgumentException("La raza no puede estar vacía");
        }
        return raza.trim();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = validarEdad(edad);
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = validarRaza(raza);
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    /**
     * Método abstracto que deben implementar las clases hijas
     * Demuestra polimorfismo
     */
    public abstract String getTipoMascota();

    /**
     * Método abstracto para obtener el sonido que hace la mascota
     * Demuestra polimorfismo
     */
    public abstract String hacerSonido();

    /**
     * Método abstracto para obtener información específica de cada tipo de mascota
     */
    public abstract String getInformacionEspecifica();

    /**
     * Método para obtener información general de la mascota
     */
    public String getInformacionGeneral() {
        return String.format("Nombre: %s, Edad: %d años, Raza: %s", 
                           nombre, edad, raza);
    }

    /**
     * Método para obtener el nombre del propietario
     */
    public String getNombrePropietario() {
        return propietario != null ? propietario.getNombreCompleto() : "Sin propietario";
    }

    /**
     * Representación base en string de una mascota
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Tipo: %s\n", getTipoMascota()));
        sb.append(getInformacionGeneral()).append("\n");
        sb.append(getInformacionEspecifica()).append("\n");
        sb.append(String.format("Propietario: %s\n", getNombrePropietario()));
        sb.append(String.format("Sonido: %s", hacerSonido()));
        
        return sb.toString();
    }

    /**
     * Método equals para comparar mascotas por nombre y propietario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mascota mascota = (Mascota) obj;
        return nombre.equalsIgnoreCase(mascota.nombre) && 
               propietario != null && propietario.equals(mascota.propietario);
    }

    /**
     * HashCode basado en el nombre y propietario
     */
    @Override
    public int hashCode() {
        int hash = nombre.toLowerCase().hashCode();
        if (propietario != null) {
            hash = hash * 31 + propietario.hashCode();
        }
        return hash;
    }
}