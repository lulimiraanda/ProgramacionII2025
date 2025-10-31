package Modelo;

public class Repartidor {
    String nombre;
    boolean disponible;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.disponible = true; 
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    

}