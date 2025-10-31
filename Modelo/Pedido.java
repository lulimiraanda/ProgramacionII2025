package Modelo;

import Interfaces.LinkedListADT;
import Implementaciones.LinkedList;

public class Pedido {
    int id;
    int idCliente; 
    int idDireccion; 
    int prioridad; 
    LinkedListADT platosIDs; 

    public Pedido(int id, int idCliente, int idDireccionVertice, int prioridad) {
        this.id = id;
        this.idCliente = idCliente;
        this.idDireccion = idDireccionVertice;
        this.prioridad = prioridad;
        this.platosIDs = new LinkedList();
    }

    public void agregarPlatoID(int idPlato) {
        this.platosIDs.add(idPlato);
    }
    
    public int getId() {
        return id;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public int getPrioridad() {
        return prioridad;
    }
    
    public int getIdDireccion() {
        return idDireccion;
    }
}