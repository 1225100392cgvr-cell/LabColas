package colas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ejercicios de refuerzo:
 * (3) invertir una cola con una pila,
 * (4) cola implementada con dos pilas,
 * (5) recorrido de un árbol binario por niveles (BFS) con una cola.
 * Los ejercicios 1 y 2 (imprimirCola y vaciar) están en {@link ColaEnlazada}.
 */
public class Refuerzo {

    /**
     * Invierte una cola usando una pila (Deque usado como pila).
     * @param cola cola a invertir (se modifica)
     * @param <T> tipo de elementos
     */
    public static <T> void invertir(Queue<T> cola) {
        Deque<T> pila = new ArrayDeque<>();
        while (!cola.isEmpty()) {
            pila.push(cola.poll());   // los elementos salen de la cola...
        }
        while (!pila.isEmpty()) {
            cola.offer(pila.pop());   // ...y vuelven en orden inverso
        }
    }

    /**
     * Cola FIFO construida con dos pilas.
     * {@code entrada} recibe los elementos; {@code salida} los entrega en orden FIFO.
     * @param <T> tipo de elementos
     */
    public static class ColaConDosPilas<T> {
        /** Pila donde se apilan los elementos nuevos. */
        private final Deque<T> entrada = new ArrayDeque<>();
        /** Pila de la que se sacan los elementos (invierte el orden de entrada). */
        private final Deque<T> salida = new ArrayDeque<>();

        /** Agrega al final. O(1). */
        public void encolar(T valor) {
            entrada.push(valor);
        }

        /** Traslada entrada -> salida solo cuando salida está vacía (O(1) amortizado). */
        private void trasladar() {
            if (salida.isEmpty()) {
                while (!entrada.isEmpty()) {
                    salida.push(entrada.pop());
                }
            }
        }

        /** Elimina y devuelve el frente. */
        public T desencolar() {
            trasladar();
            if (salida.isEmpty()) throw new IllegalStateException("La cola está vacía");
            return salida.pop();
        }

        /** Consulta el frente sin eliminarlo. */
        public T frente() {
            trasladar();
            if (salida.isEmpty()) throw new IllegalStateException("La cola está vacía");
            return salida.peek();
        }

        /** @return true si no hay elementos. */
        public boolean estaVacia() {
            return entrada.isEmpty() && salida.isEmpty();
        }
    }

    /** Nodo de un árbol binario de enteros. */
    public static class NodoArbol {
        int valor;
        NodoArbol izq, der;
        NodoArbol(int valor) { this.valor = valor; }
    }

    /**
     * Recorre un árbol binario por niveles (BFS) usando una cola.
     * @param raiz raíz del árbol
     */
    public static void recorridoPorNiveles(NodoArbol raiz) {
        if (raiz == null) return;
        Queue<NodoArbol> cola = new LinkedList<>();
        cola.offer(raiz);
        while (!cola.isEmpty()) {
            NodoArbol n = cola.poll();
            System.out.print(n.valor + " ");
            if (n.izq != null) cola.offer(n.izq);
            if (n.der != null) cola.offer(n.der);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Ejercicio 1 y 2
        ColaEnlazada<Integer> c = new ColaEnlazada<>();
        c.encolar(1); c.encolar(2); c.encolar(3);
        System.out.print("imprimirCola: ");
        c.imprimirCola();
        c.vaciar();
        System.out.println("vaciar -> tamaño = " + c.tamanio());

        // Ejercicio 3
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); q.offer(2); q.offer(3); q.offer(4);
        System.out.println("Antes de invertir:   " + q);
        invertir(q);
        System.out.println("Después de invertir: " + q);

        // Ejercicio 4
        ColaConDosPilas<String> dp = new ColaConDosPilas<>();
        dp.encolar("A"); dp.encolar("B"); dp.encolar("C");
        System.out.println("Dos pilas -> " + dp.desencolar() + ", " + dp.desencolar());
        dp.encolar("D");
        System.out.println("Dos pilas -> " + dp.desencolar() + ", " + dp.desencolar());

        // Ejercicio 5:      1
        //                 /   \
        //                2     3
        //               / \     \
        //              4   5     6
        NodoArbol r = new NodoArbol(1);
        r.izq = new NodoArbol(2); r.der = new NodoArbol(3);
        r.izq.izq = new NodoArbol(4); r.izq.der = new NodoArbol(5);
        r.der.der = new NodoArbol(6);
        System.out.print("BFS: ");
        recorridoPorNiveles(r);
    }
}