package Implementaciones;

import Interfaces.GrafoADT;
import Interfaces.SetADT;


public class GrafoLA implements GrafoADT {

    private class VertexNode {
        int vertex;
        VertexNode next;
        EdgeNode edge;
    }

    private class EdgeNode {
        int weight;
        VertexNode dest;
        EdgeNode next;
    }

    private VertexNode origin;

    public GrafoLA() {
        origin = null;
    }

    @Override
    public SetADT getVertxs() {
        SetStatic set = new SetStatic();
        set.inicializarConjunto();
        VertexNode aux = origin;
        while (aux != null) {
            set.add(aux.vertex);
            aux = aux.next;
        }
        return set;
    }

    @Override
    public void addVertx(int vertex) {
        VertexNode nuevo = new VertexNode();
        nuevo.vertex = vertex;
        nuevo.edge = null;
        nuevo.next = origin;
        origin = nuevo;
    }

    @Override
    public void removeVertx(int vertex) {
        if (origin == null) return;
        if (origin.vertex == vertex) {
            origin = origin.next;
        }
        VertexNode aux = origin;
        while (aux != null) {
            this.removeEdgeInNode(aux, vertex);
            if (aux.next != null && aux.next.vertex == vertex) {
                aux.next = aux.next.next;
            }
            aux = aux.next;
        }
    }

    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        VertexNode n1 = vertexToNode(vertxOne);
        VertexNode n2 = vertexToNode(vertxTwo);
        if (n1 == null || n2 == null) return; // preconditions not met
        EdgeNode nuevo = new EdgeNode();
        nuevo.weight = weight;
        nuevo.dest = n2;
        nuevo.next = n1.edge;
        n1.edge = nuevo;
    }

    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {
        VertexNode nodo = vertexToNode(vertxOne);
        if (nodo == null) return;
        removeEdgeInNode(nodo, vertxTwo);
    }

    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        VertexNode nodo = vertexToNode(vertxOne);
        if (nodo == null) {
            throw new IllegalStateException("Vertex not found");
        }
        EdgeNode aux = nodo.edge;
        while (aux != null && aux.dest.vertex != vertxTwo) {
            aux = aux.next;
        }
        if (aux == null) {
            throw new IllegalStateException("Edge not found");
        }
        return aux.weight;
    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        VertexNode nodo = vertexToNode(vertxOne);
        if (nodo == null) return false;
        EdgeNode aux = nodo.edge;
        while (aux != null && aux.dest.vertex != vertxTwo) {
            aux = aux.next;
        }
        return (aux != null);
    }

    @Override
    public boolean isEmpty() {
        return origin == null;
    }

    private void removeEdgeInNode(VertexNode nodo, int vertxTwo) {
        if (nodo == null || nodo.edge == null) return;
        EdgeNode aux = nodo.edge;
        if (aux != null) {
            if (aux.dest.vertex == vertxTwo) {
                nodo.edge = aux.next;
            } else {
                while (aux.next != null && aux.next.dest.vertex != vertxTwo) {
                    aux = aux.next;
                }
                if (aux.next != null) {
                    aux.next = aux.next.next;
                }
            }
        }
    }

    private VertexNode vertexToNode(int vertx) {
        VertexNode aux = origin;
        while (aux != null && aux.vertex != vertx) {
            aux = aux.next;
        }
        return aux;
    }
}
