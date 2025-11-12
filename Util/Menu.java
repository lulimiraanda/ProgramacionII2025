package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.Sistema;

public class Menu {
    private Sistema sistema = new Sistema();

    public void mostrar() {
        try (Scanner entrada = new Scanner(System.in)) {
            boolean ejecutando = true;

            while (ejecutando) {
                System.out.println("===== Menu Principal =====");
                System.out.println("1. Agregar Cliente");
                System.out.println("2. Agregar Plato");
                System.out.println("3. Crear Pedido");
                System.out.println("4. Ver pedidos pendientes");
                System.out.println("5. Entregar pedidos pendientes");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = -1;
                try {
                    opcion = Integer.parseInt(entrada.nextLine());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del cliente: ");
                        String nombre = entrada.nextLine();
                        int idClienteCreado = sistema.addCliente(nombre);
                        System.out.println("Cliente creado con id: " + idClienteCreado);
                        break;
                    case 2:
                        System.out.print("Nombre del plato: ");
                        String nombrePlato = entrada.nextLine();
                        System.out.print("Precio: ");
                        double precio = 0;
                        try { precio = Double.parseDouble(entrada.nextLine()); } catch (Exception ex) {}
                        int idPlatoCreado = sistema.addPlato(nombrePlato, precio);
                        System.out.println("Plato agregado con id: " + idPlatoCreado);
                        break;
                    case 3:
                        System.out.println("-- Crear Pedido --");
                        sistema.printClientes();
                        int idCliente = leerEntero(entrada, "Id cliente: ");
                        while (!sistema.clienteExists(idCliente)) {
                            System.out.println("Cliente no encontrado. Ingrese un Id válido.");
                            idCliente = leerEntero(entrada, "Id cliente: ");
                        }
                        String[] lugares = new String[] {"Mataderos","Liniers","Villa Luro","Ciudadela","Versailles"};
                        System.out.println("Direcciones disponibles:");
                        for (int i = 0; i < lugares.length; i++) {
                            System.out.println("  " + (i+1) + ". " + lugares[i]);
                        }
                        boolean cancelado = false;
                        int idDireccion = -1;
                        while (idDireccion == -1) {
                            String entradaDireccion = leerLinea(entrada, "Elija dirección (número o nombre) (o 'c' para cancelar): ");
                            if (entradaDireccion.equalsIgnoreCase("c")) { cancelado = true; break; }
                            idDireccion = parsearDireccion(entradaDireccion, lugares);
                            if (idDireccion == -1) System.out.println("Dirección inválida. Intente de nuevo o escriba 'c' para cancelar.");
                        }
                        if (cancelado) {
                            System.out.println("Creación de pedido cancelada.");
                            break;
                        }
                        System.out.print("Prioridad (1=Normal,2=VIP): ");
                        int prioridad = Integer.parseInt(entrada.nextLine());
                        sistema.printPlatos();
                        int n = leerEntero(entrada, "Cuantos platos en el pedido?: ");
                        List<Integer> lista = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            int plid = leerEntero(entrada, "Id plato #" + (i+1) + ": ");
                            // validate plato exists
                            while (sistema.getPlatoById(plid) == null) {
                                System.out.println("Plato no encontrado. Ingrese un Id de plato válido.");
                                plid = leerEntero(entrada, "Id plato #" + (i+1) + ": ");
                            }
                            lista.add(plid);
                        }
                        int nuevoPedidoId = sistema.crearPedido(idCliente, idDireccion, prioridad, lista);
                        System.out.println("Pedido creado id: " + nuevoPedidoId);
                        break;
                  
                    case 4:
                        System.out.println("-- Pedidos pendientes ordenados por prioridad --");
                        List<Modelo.Pedido> pedidosOrdenados = sistema.obtenerPedidosOrdenadosPorPrioridad();
                        if (pedidosOrdenados.isEmpty()) {
                            System.out.println("No hay pedidos registrados.");
                        } else {
                            while (!pedidosOrdenados.isEmpty()) {
                                Modelo.Pedido p = pedidosOrdenados.remove(0);
                                System.out.println("Pedido id=" + p.getId()
                                        + " | clienteId=" + p.getIdCliente()
                                        + " | prioridad=" + p.getPrioridad()
                                        + " | platosPendientes=" + p.platosPendientes()
                                        + " | direccionId=" + p.getIdDireccion());
                            }
                        }
                        break;
                    case 5:
                        System.out.println("-- Entregas pendientes --");
                        List<Integer> entregas = sistema.obtenerEntregasPendientes();
                        if (entregas.isEmpty()) {
                            System.out.println("No hay entregas pendientes.");
                        } 
                        break;
                    
                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo.");
                }

                System.out.println();
            }
        }
    }

    private String leerLinea(Scanner entrada, String prompt) {
        System.out.print(prompt);
        return entrada.nextLine().trim();
    }

    private int leerEntero(Scanner entrada, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linea = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
            }
        }
    }

    private int parsearDireccion(String entradaTexto, String[] lugares) {
        if (entradaTexto.isEmpty()) return -1;
        try {
            int v = Integer.parseInt(entradaTexto);
            if (v >= 1 && v <= lugares.length) return v;
            return -1;
        } catch (NumberFormatException ex) {
            for (int i = 0; i < lugares.length; i++) {
                if (lugares[i].equalsIgnoreCase(entradaTexto)) return i + 1;
            }
            return -1;
        }
    }

}
