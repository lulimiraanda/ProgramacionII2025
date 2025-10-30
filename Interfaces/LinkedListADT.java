package Interfaces;

public interface LinkedListADT {

    /**
     * Descripcion: Agrega el elemento al final de la lista.
     * Precondición: No tiene
     */
    void add(int value);

    /**
     * Descripcion: Agrega el elemento en un indice.
     * Precondición: El indice debe estar entre 0 y size().
     */
    void insert(int index, int value);

    /**
     * Descripcion: Elimina el elemento de un indice.
     * Precondición: El indice debe existir.
     */
    void remove(int index);

    /**
     * Descripcion: Retorna el elemento de un indice.
     * Precondición: El indice debe existir.
     */
    int get(int index);

    /**
     * Descripcion: Retorna el tamaño de la lista.
     * Precondición: No tiene.
     */
    int size();

    /**
     * Descripcion: Debe comprobar si la estructura tiene o no valores.
     * Precondición: No tiene.
     */
    boolean isEmpty();
}

