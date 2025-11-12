package Controller;

import java.util.ArrayList;
import java.util.List;
import Interfaces.PriorityQueueADT;
import Implementaciones.PriorityQueueStatic;

import Modelo.Cliente;
import Modelo.Plato;
import Modelo.Pedido;
import Implementaciones.SetStatic;


public class Sistema {

    private List<Cliente> clientes = new ArrayList<>();
    private SetStatic listaPlatosIds = new SetStatic();
    private Plato[] registroPlatos = new Plato[100];
    private List<Pedido> pedidos = new ArrayList<>();

    private Pedido[] registroPedidos = new Pedido[100];

    private PriorityQueueADT colaPedidos = new PriorityQueueStatic();

    private List<Integer> entregasPendientes = new ArrayList<>();



    private int nextClienteId = 1;
    private int nextPlatoId = 1;
    private int nextPedidoId = 1;

    public Sistema() {
        listaPlatosIds.inicializarConjunto();
    }
    




    public int addCliente(String nombre) {
        Cliente cliente = new Cliente(nextClienteId, nombre);
        clientes.add(cliente);
        nextClienteId++;
        return cliente.getId();
    }

    public int addPlato(String nombre, double precio) {
        for (int i = 0; i < registroPlatos.length; i++) {
            Plato p = registroPlatos[i];
            if (p != null && p.getNombre().equalsIgnoreCase(nombre)) {
                // ya existe: devolver su id (i+1)
                return i + 1;
            }
        }
        Plato plato = new Plato(nombre, precio);
        int id = nextPlatoId;
        nextPlatoId++;
        asegurarCapacidadPlatos(id);
        registroPlatos[id - 1] = plato;
        listaPlatosIds.add(id);
        return id;
    }

    public Plato getPlatoById(int id) {
        int idx = id - 1;
        if (idx >= 0 && idx < registroPlatos.length) return registroPlatos[idx];
        return null;
    }

    private void asegurarCapacidadPlatos(int id) {
        if (id <= registroPlatos.length) return;
        int nueva = registroPlatos.length * 2;
        while (nueva < id) nueva *= 2;
        Plato[] aux = new Plato[nueva];
        for (int i = 0; i < registroPlatos.length; i++) aux[i] = registroPlatos[i];
        registroPlatos = aux;
    }

    public void printClientes() {
        System.out.println("Clientes (id : nombre):");
        for (Cliente cliente : clientes) System.out.println("  " + cliente.getId() + " : " + cliente.getNombre());
    }

    public void printPlatos() {
        System.out.println("Platos disponibles (id : nombre):");
        SetStatic temp = new SetStatic();
        temp.inicializarConjunto();
        while (!listaPlatosIds.isEmpty()) {
            int id = listaPlatosIds.choose();
            listaPlatosIds.remove(id);
            Plato p = getPlatoById(id);
            if (p != null) System.out.println("  " + id + " : " + p.getNombre());
            temp.add(id);
        }
        while (!temp.isEmpty()) {
            int id = temp.choose();
            temp.remove(id);
            listaPlatosIds.add(id);
        }
    }



    public int crearPedido(int idCliente, int idDireccionVertice, int prioridadTipo, List<Integer> platosIds) {
        int pedidoId = nextPedidoId++;
        Pedido pedido = new Pedido(pedidoId, idCliente, idDireccionVertice, prioridadTipo);
        for (Integer pid : platosIds) pedido.agregarPlatoID(pid);
    pedidos.add(pedido);
        registroPedidos[pedidoId - 1] = pedido;
        colaPedidos.add(pedidoId, prioridadTipo);
        return pedidoId;
    }


    public List<Integer> obtenerEntregasPendientes() {
        return new ArrayList<>(entregasPendientes);
    }

 
  public List<Pedido> obtenerPedidosOrdenadosPorPrioridad() {
      List<Pedido> resultado = new ArrayList<>();
      PriorityQueueADT colaTemporal = new PriorityQueueStatic();
      while (!colaPedidos.isEmpty()) {
          int id = colaPedidos.getElement();
          int pr = colaPedidos.getPriority();
          Pedido ped = null;
          if (id - 1 >= 0 && id - 1 < registroPedidos.length) ped = registroPedidos[id - 1];
          if (ped == null) {
              for (Pedido p : pedidos) if (p.getId() == id) { ped = p; break; }
          }
          if (ped != null) resultado.add(ped);
          colaTemporal.add(id, pr);
          colaPedidos.remove();
      }
      while (!colaTemporal.isEmpty()) {
          int id = colaTemporal.getElement();
          int pr = colaTemporal.getPriority();
          colaPedidos.add(id, pr);
          colaTemporal.remove();
      }
      return resultado;
  }

public boolean clienteExists(int id) { 
    for (Cliente cliente : clientes) if (cliente.getId() == id) return true; return false; }  

 

}
