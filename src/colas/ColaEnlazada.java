package colas;

/**
 * Cola FIFO genérica implementada con nodos enlazados.
 * No tiene límite de capacidad: se mantienen dos referencias,
 * {@code frente} (donde se elimina) y {@code fin} (donde se inserta).
 *
 * @param <T> tipo de los elementos almacenados
 */
public class ColaEnlazada<T> {

    /**
     * Nodo de la lista enlazada.
     * @param <E> tipo del dato guardado en el nodo
     */
    private static class Nodo<E> {
        /** Dato almacenado en el nodo. */
        E dato;
        /** Referencia al siguiente nodo (null si es el último). */
        Nodo<E> siguiente;

        /**
         * Crea un nodo sin siguiente.
         * @param dato valor a almacenar
         */
        Nodo(E dato) {
            this.dato = dato;
        }
    }

    /** Primer nodo de la cola (el próximo en salir). */
    private Nodo<T> frente;
    /** Último nodo de la cola (donde se inserta). */
    private Nodo<T> fin;
    /** Cantidad de elementos en la cola. */
    private int tamanio;

    /** @return true si la cola no tiene elementos. */
    public boolean estaVacia() {
        return frente == null;
    }

    /** @return cantidad de elementos actuales. */
    public int tamanio() {
        return tamanio;
    }

    /**
     * Agrega un elemento al final de la cola. O(1).
     * @param valor elemento a insertar
     */
    public void encolar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            frente = nuevo;       // cola vacía: el nuevo es también el frente
        } else {
            fin.siguiente = nuevo; // cola con elementos: se enlaza tras el último
        }
        fin = nuevo;
        tamanio++;
    }

    /**
     * Elimina y devuelve el elemento del frente. O(1).
     * @return el elemento del frente
     * @throws IllegalStateException si la cola está vacía
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T valor = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null; // la cola quedó vacía
        }
        tamanio--;
        return valor;
    }

    /**
     * Consulta el elemento del frente sin eliminarlo. O(1).
     * @return el elemento del frente
     * @throws IllegalStateException si la cola está vacía
     */
    public T frente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return frente.dato;
    }

    /**
     * (Refuerzo, nivel básico) Imprime los elementos del frente al final
     * sin modificar la cola. O(n).
     */
    public void imprimirCola() {
        if (estaVacia()) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("Frente -> ");
        Nodo<T> actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" | ");
            }
            actual = actual.siguiente;
        }
        sb.append(" <- Fin");
        System.out.println(sb);
    }

    /**
     * (Refuerzo, nivel básico) Deja la cola sin elementos. O(1).
     */
    public void vaciar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }
}