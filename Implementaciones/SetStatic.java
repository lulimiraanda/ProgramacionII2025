package Implementaciones;

import Interfaces.SetADT;

public class SetStatic implements SetADT {

    int[] arr;
    int cant;

    public void inicializarConjunto(){
        arr = new int[100];
        cant = 0;
    }

    @Override
    public void add(int value) {
        if (!exist(value)) {
            arr[cant] = value;
            cant++;
        }
    }

    @Override
    public void remove(int element) {
        int i = 0;
        while (i < cant && arr[i] != element)
            i++;
        if (i < cant) {
            arr[i] = arr[cant - 1];
            cant--;
        }
    }

    @Override
    public int choose() {
        if (cant == 0) {
            throw new IllegalStateException("The set is empty (precondition not met).");
        }
        return arr[0];
    }

    @Override
    public boolean exist(int value) {
        int i = 0;
        while (i < cant && arr[i] != value)
            i++;
        return (i < cant);
    }

    @Override
    public boolean isEmpty() {

        return (cant == 0);
    }
}
