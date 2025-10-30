package Implementaciones;

import Interfaces.PriorityQueueADT;

public class PriorityQueueStatic implements PriorityQueueADT {
    class Element {
        int value;
        int priority;
    }

    Element[] arr;
    int cant;

    public PriorityQueueStatic() {
        arr = new Element[20];
        cant = 0;
    }

    @Override
    public void add(int value, int priority) {
        int i = cant;
        while (i > 0 && arr[i - 1].priority > priority) {
            arr[i] = arr[i - 1];
            i--;
        }
        arr[i] = new Element();
        arr[i].value = value;
        arr[i].priority = priority;
        cant++;
    }

    @Override
    public void remove() {

        cant--;
    }

    @Override
    public int getElement() {

        return arr[cant - 1].value;
    }

    @Override
    public int getPriority() {

        return arr[cant - 1].priority;
    }

    @Override
    public boolean isEmpty() {
        return (cant == 0);
    }
}

