package Implementaciones;

import Interfaces.LinkedListADT;

public class LinkedList implements LinkedListADT {

    private class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    public LinkedList() {
        this.head = null;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node aux = head;
        while (aux.next != null) {
            aux = aux.next;
        }
        aux.next = newNode;
    }

    @Override
    public void insert(int index, int value) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node newNode = new Node(value, null);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node aux = head;
        for (int i = 0; i < index - 1; i++) {
            aux = aux.next;
        }
        newNode.next = aux.next;
        aux.next = newNode;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        Node aux = head;
        for (int i = 0; i < index - 1; i++) {
            aux = aux.next;
        }
        aux.next = aux.next.next;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.value;
    }

    @Override
    public int size() {
        int count = 0;
        Node aux = head;
        while (aux != null) {
            count++;
            aux = aux.next;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
