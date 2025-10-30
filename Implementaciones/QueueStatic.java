package Implementaciones;

import Interfaces.QueueADT;

public class QueueStatic implements QueueADT {

    int[] arr;
    int cant;

    public QueueStatic() {
        arr = new int[20];
        cant = 0;
    }

    @Override
    public void add(int value) {
        for (int i = cant - 1; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = value;
        cant++;
    }

    @Override
    public void remove() {
        cant--;
    }

    @Override
    public int getElement() {
        return arr[cant - 1];
    }

    @Override
    public boolean isEmpty() {
        return (cant == 0);
    }
}
