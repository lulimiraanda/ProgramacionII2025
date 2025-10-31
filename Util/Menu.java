package Util;

import java.util.Scanner;

public class Menu {
    public void print() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Agregar Plato");
            System.out.println("3. Crear Pedido");
            System.out.println("4. Asignar Repartidor");
            System.out.println("5. Mostrar Estado de Pedidos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    addClientSubmenu(sc);
                    break;
                case 2:
                    addDishSubmenu(sc);
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada: Crear Pedido");
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada: Asignar Repartidor");
                    
                    break;
                case 5:
                    System.out.println("Opción 5 seleccionada: Mostrar Estado de Pedidos");
                    
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }

            System.out.println(); 
        }
    }

    private void addClientSubmenu(Scanner sc) {
        System.out.println("=== Agregar Cliente ===");
        System.out.print("Nombre: ");
        String name = sc.nextLine();
        System.out.print("Dirección: ");
        String address = sc.nextLine();
        System.out.println("Cliente agregado: " + name + " - " + address);
    }

    private void addDishSubmenu(Scanner sc) {
        System.out.println("=== Agregar Plato ===");
        System.out.print("Nombre del plato: ");
        String name = sc.nextLine();
        System.out.print("Precio: ");
        double price = 0;
        try {
            price = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido, se usará 0.0");
        }
        System.out.println("Plato agregado: " + name + " - " + price);
    }
}
