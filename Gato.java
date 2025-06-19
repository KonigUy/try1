package com.veterinaria.modelo;

/**
 * Clase Gato que hereda de Mascota.
 * Demuestra herencia y implementación de métodos abstractos.
 */
public class Gato extends Mascota {
    private boolean esDeInterior;

    /**
     * Constructor de Gato
     */
    public Gato(String nombre, int edad, String raza, boolean esDeInterior) {
        super(nombre, edad, raza);
        this.esDeInterior = esDeInterior;
    }

    /**
     * Implementación del método abstracto de Mascota
     */
    @Override
    public String getTipoMascota() {
        return "Gato";
    }

    /**
     * Implementación del método abstracto hacerSonido
     * Demuestra polimorfismo
     */
    @Override
    public String hacerSonido() {
        return "Miau miau";
    }

    /**
     * Implementación del método abstracto getInformacionEspecifica
     */
    @Override
    public String getInformacionEspecifica() {
        return String.format("Tipo de gato: %s", 
                           esDeInterior ? "Gato de interior" : "Gato de exterior");
    }

    /**
     * Método específico de los gatos
     */
    public String ronronear() {
        return nombre + " está ronroneando: Prrrrr...";
    }

    /**
     * Método específico de los gatos
     */
    public String trepar() {
        return nombre + " está trepando como todo buen gato.";
    }

    /**
     * Método específico de los gatos
     */
    public String cazar() {
        if (esDeInterior) {
            return nombre + " está cazando juguetes en casa.";
        } else {
            return nombre + " está cazando en el exterior.";
        }
    }

    /**
     * Obtiene consejos de cuidado específicos para gatos
     */
    public String getConsejosCuidado() {
        StringBuilder consejos = new StringBuilder();
        consejos.append("Consejos de cuidado para ").append(nombre).append(":\n");
        consejos.append("- Mantener la caja de arena limpia\n");
        consejos.append("- Proporcionar postes para arañar\n");
        consejos.append("- Cepillado regular del pelaje\n");
        
        if (esDeInterior) {
            consejos.append("- Proporcionar estimulación mental con juguetes\n");
            consejos.append("- Considerar hierba gatera para enriquecimiento");
        } else {
            consejos.append("- Revisar regularmente en busca de parásitos\n");
            consejos.append("- Mantener vacunas al día");
        }
        
        return consejos.toString();
    }

    // Getters y Setters específicos
    public boolean isEsDeInterior() {
        return esDeInterior;
    }

    public void setEsDeInterior(boolean esDeInterior) {
        this.esDeInterior = esDeInterior;
    }

    /**
     * Representación en string específica del gato
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("Acciones especiales de gato:\n");
        sb.append("- ").append(ronronear()).append("\n");
        sb.append("- ").append(trepar()).append("\n");
        sb.append("- ").append(cazar());
        
        return sb.toString();
    }
}