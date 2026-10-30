package colas;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase principal: verifica el entorno y ejecuta las pruebas de las Partes 1 a 3.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Entorno listo");

        // ---------- Parte 1: cola con arreglo circular ----------
        System.out.println("\n=== Parte 1: ColaArreglo ===");
        ColaArreglo cola = new ColaArreglo(3);
        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);
        System.out.println("Sale: " + cola.desencolar());      // 10
        cola.encolar(40);                                      // reutiliza la posición 0
        System.out.println("fin = " + cola.getFin());          // 0
        System.out.println("Frente: " + cola.frente());        // 20
        System.out.println("Tamaño: " + cola.tamanio());       // 3
        System.out.println("¿Llena? " + cola.estaLlena());     // true
        try {
            cola.encolar(50);
        } catch (IllegalStateException e) {
            System.out.println("Encolar con cola llena -> " + e.getMessage());
        }

        // ---------- Parte 2: cola enlazada genérica ----------
        System.out.println("\n=== Parte 2: ColaEnlazada<String> ===");
        ColaEnlazada<String> nombres = new ColaEnlazada<>();
        nombres.encolar("Ana");
        nombres.encolar("Luis");
        nombres.encolar("María");
        System.out.println(nombres.desencolar()); // Ana
        System.out.println(nombres.frente());     // Luis
        System.out.println(nombres.tamanio());    // 2
        nombres.imprimirCola();
        nombres.vaciar();
        System.out.println("Tras vaciar, ¿vacía? " + nombres.estaVacia());

        // ---------- Parte 3: interfaz Queue ----------
        System.out.println("\n=== Parte 3.1: Queue (LinkedList) ===");
        Queue<String> q = new LinkedList<>(); // prueba también: new ArrayDeque<>()
        q.offer("Ana");
        q.offer("Luis");
        q.offer("María");
        System.out.println(q.peek()); // Ana (no la elimina)
        System.out.println(q.poll()); // Ana (la elimina)
        System.out.println(q.size()); // 2
        System.out.println(q);        // [Luis, María]

        System.out.println("\n=== Parte 3.2: métodos sobre cola vacía ===");
        Queue<String> vacia = new ArrayDeque<>();
        try { vacia.remove(); } catch (Exception e) { System.out.println("remove()  -> excepción: " + e.getClass().getSimpleName()); }
        System.out.println("poll()    -> " + vacia.poll());
        try { vacia.element(); } catch (Exception e) { System.out.println("element() -> excepción: " + e.getClass().getSimpleName()); }
        System.out.println("peek()    -> " + vacia.peek());

        System.out.println("\n=== Parte 3.3: PriorityQueue ===");
        Queue<Integer> pq = new java.util.PriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        System.out.println(pq.poll()); // 10
        System.out.println(pq.poll()); // 20
        System.out.println(pq.poll()); // 30
    }
}