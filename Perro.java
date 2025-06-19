ackage com.veterinaria.modelo;

/**
 * Clase Perro que hereda de Mascota.
 * Demuestra herencia y implementación de métodos abstractos.
 */
public class Perro extends Mascota {
    private String tamano; // Pequeño, Mediano, Grande

    /**
     * Constructor de Perro
     */
    public Perro(String nombre, int edad, String raza, String tamano) {
        super(nombre, edad, raza);
        this.tamano = validarTamano(tamano);
    }

    /**
     * Valida que el tamaño sea válido
     */
    private String validarTamano(String tamano) {
        if (tamano == null || tamano.trim().isEmpty()) {
            throw new IllegalArgumentException("El tamaño del perro no puede estar vacío");
        }
        
        String tamanoNormalizado = tamano.trim().toLowerCase();
        if (!tamanoNormalizado.equals("pequeño") && 
            !tamanoNormalizado.equals("mediano") && 
            !tamanoNormalizado.equals("grande")) {
            throw new IllegalArgumentException("El tamaño debe ser: Pequeño, Mediano o Grande");
        }
        
        // Retornar con primera letra mayúscula
        return tamano.trim().substring(0, 1).toUpperCase() + 
               tamano.trim().substring(1).toLowerCase();
    }

    /**
     * Implementación del método abstracto de Mascota
     */
    @Override
    public String getTipoMascota() {
        return "Perro";
    }

    /**
     * Implementación del método abstracto hacerSonido
     * Demuestra polimorfismo
     */
    @Override
    public String hacerSonido() {
        // El sonido varía según el tamaño del perro
        switch (tamano.toLowerCase()) {
            case "pequeño":
                return "Yip yip";
            case "mediano":
                return "Woof woof";
            case "grande":
                return "WOOF WOOF";
            default:
                return "Guau guau";
        }
    }

    /**
     * Implementación del método abstracto getInformacionEspecifica
     */
    @Override
    public String getInformacionEspecifica() {
        return String.format("Tamaño: %s", tamano);
    }

    /**
     * Método específico de los perros
     */
    public String buscarPelota() {
        return nombre + " está buscando la pelota con entusiasmo.";
    }

    /**
     * Método específico de los perros
     */
    public String moverCola() {
        return nombre + " está moviendo la cola de felicidad.";
    }

    /**
     * Método específico de los perros
     */
    public String saltar() {
        String intensidad;
        switch (tamano.toLowerCase()) {
            case "pequeño":
                intensidad = "pequeños saltitos";
                break;
            case "mediano":
                intensidad = "saltos moderados";
                break;
            case "grande":
                intensidad = "grandes saltos";
                break;
            default:
                intensidad = "saltos";
        }
        return nombre + " está haciendo " + intensidad + ".";
    }

    /**
     * Obtiene la cantidad de ejercicio recomendada según el tamaño
     */
    public String getEjercicioRecomendado() {
        switch (tamano.toLowerCase()) {
            case "pequeño":
                return "30-45 minutos de ejercicio diario, caminatas cortas y juegos en casa";
            case "mediano":
                return "60-90 minutos de ejercicio diario, caminatas y juegos activos";
            case "grande":
                return "90-120 minutos de ejercicio diario, caminatas largas y ejercicio intenso";
            default:
                return "Consultar con veterinario para plan de ejercicio personalizado";
        }
    }

    /**
     * Obtiene consejos de cuidado específicos para perros
     */
    public String getConsejosCuidado() {
        StringBuilder consejos = new StringBuilder();
        consejos.append("Consejos de cuidado para ").append(nombre).append(":\n");
        consejos.append("- Ejercicio diario: ").append(getEjercicioRecomendado()).append("\n");
        consejos.append("- Cepillado regular del pelaje\n");
        consejos.append("- Entrenamiento de obediencia\n");
        consejos.append("- Socialización con otros perros\n");
        
        if (tamano.toLowerCase().equals("grande")) {
            consejos.append("- Cuidado especial de las articulaciones\n");
            consejos.append("- Control de peso para evitar problemas de salud");
        } else if (tamano.toLowerCase().equals("pequeño")) {
            consejos.append("- Cuidado dental regular\n");
            consejos.append("- Protección contra el frío");
        }
        
        return consejos.toString();
    }

    // Getters y Setters específicos
    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = validarTamano(tamano);
    }

    /**
     * Verifica si es un perro pequeño
     */
    public boolean esPequeno() {
        return tamano.toLowerCase().equals("pequeño");
    }

    /**
     * Verifica si es un perro mediano
     */
    public boolean esMediano() {
        return tamano.toLowerCase().equals("mediano");
    }

    /**
     * Verifica si es un perro grande
     */
    public boolean esGrande() {
        return tamano.toLowerCase().equals("grande");
    }

    /**
     * Representación en string específica del perro
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("Ejercicio recomendado: ").append(getEjercicioRecomendado()).append("\n");
        sb.append("Acciones especiales de perro:\n");
        sb.append("- ").append(buscarPelota()).append("\n");
        sb.append("- ").append(moverCola()).append("\n");
        sb.append("- ").append(saltar());
        
        return sb.toString();
    }
}