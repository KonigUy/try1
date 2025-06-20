import java.util.ArrayList;
import java.util.List;

/**
 * Clase Veterinario que hereda de Persona.
 * Demuestra herencia y composición (tiene una lista de consultas).
 */
public class Veterinario extends Persona {
    private String especialidad;
    private List<Consulta> consultas;

    /**
     * Constructor de Veterinario
     */
    public Veterinario(String nombre, String apellido, String dni, String telefono, String email, String especialidad) {
        super(nombre, apellido, dni, telefono, email);
        this.especialidad = validarEspecialidad(especialidad);
        this.consultas = new ArrayList<>();
    }

    /**
     * Valida que la especialidad no sea nula o vacía
     */
    private String validarEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía");
        }
        return especialidad.trim();
    }

    /**
     * Implementación del método abstracto de Persona
     */
    @Override
    public String getTipoPersona() {
        return "Veterinario";
    }

    /**
     * Agrega una consulta al veterinario
     */
    public void agregarConsulta(Consulta consulta) {
        if (consulta == null) {
            throw new IllegalArgumentException("La consulta no puede ser nula");
        }
        
        // Verificar que el veterinario de la consulta sea este mismo
        if (!this.equals(consulta.getVeterinario())) {
            throw new IllegalArgumentException("La consulta no corresponde a este veterinario");
        }
        
        consultas.add(consulta);
    }

    /**
     * Lista todas las consultas del veterinario
     */
    public void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Este veterinario no tiene consultas registradas.");
            return;
        }
        
        System.out.println("Consultas del Dr. " + getNombreCompleto() + ":");
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println("Consulta #" + (i + 1) + ":");
            System.out.println(consultas.get(i));
            System.out.println("---");
        }
    }

    /**
     * Busca consultas por cliente
     */
    public List<Consulta> buscarConsultasPorCliente(Cliente cliente) {
        List<Consulta> consultasCliente = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getCliente().equals(cliente)) {
                consultasCliente.add(consulta);
            }
        }
        return consultasCliente;
    }

    /**
     * Busca consultas por mascota
     */
    public List<Consulta> buscarConsultasPorMascota(Mascota mascota) {
        List<Consulta> consultasMascota = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getMascota().equals(mascota)) {
                consultasMascota.add(consulta);
            }
        }
        return consultasMascota;
    }

    /**
     * Obtiene la cantidad de consultas realizadas
     */
    public int getCantidadConsultas() {
        return consultas.size();
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = validarEspecialidad(especialidad);
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(consultas); // Retorna una copia para evitar modificaciones externas
    }

    /**
     * Verifica si el veterinario tiene consultas
     */
    public boolean tieneConsultas() {
        return !consultas.isEmpty();
    }

    /**
     * Representación en string del veterinario incluyendo su información
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("\nEspecialidad: %s", especialidad));
        sb.append(String.format("\nConsultas realizadas: %d", consultas.size()));
        
        return sb.toString();
    }
}
