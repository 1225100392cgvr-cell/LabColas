package colas;

/**
 * Cola FIFO de enteros implementada con un arreglo circular de capacidad fija.
 * Los índices "dan la vuelta" usando el operador módulo (%), de modo que
 * los espacios liberados al desencolar se reutilizan.
 */
public class ColaArreglo {

    /** Arreglo que almacena los elementos. */
    private int[] datos;
    /** Índice del elemento que está al frente (el próximo en salir). */
    private int frente;
    /** Índice del último elemento insertado. */
    private int fin;
    /** Cantidad actual de elementos en la cola. */
    private int tamanio;
    /** Capacidad máxima del arreglo. */
    private int capacidad;

    /**
     * Crea una cola vacía con la capacidad indicada.
     * @param capacidad número máximo de elementos (debe ser mayor que 0)
     */
    public ColaArreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.tamanio = 0;
    }

    /** @return true si la cola no tiene elementos. */
    public boolean estaVacia() {
        return tamanio == 0;
    }

    /** @return true si la cola alcanzó su capacidad máxima. */
    public boolean estaLlena() {
        return tamanio == capacidad;
    }

    /** @return cantidad de elementos actuales. */
    public int tamanio() {
        return tamanio;
    }

    /**
     * Agrega un elemento al final de la cola. O(1).
     * @param valor entero a insertar
     * @throws IllegalStateException si la cola está llena
     */
    public void encolar(int valor) {
        if (estaLlena()) {
            throw new IllegalStateException("La cola está llena");
        }
        fin = (fin + 1) % capacidad; // avance circular
        datos[fin] = valor;
        tamanio++;
    }

    /**
     * Elimina y devuelve el elemento del frente. O(1).
     * @return el elemento que estaba al frente
     * @throws IllegalStateException si la cola está vacía
     */
    public int desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        int valor = datos[frente];
        frente = (frente + 1) % capacidad;
        tamanio--;
        return valor;
    }

    /**
     * Consulta el elemento del frente sin eliminarlo. O(1).
     * @return el elemento del frente
     * @throws IllegalStateException si la cola está vacía
     */
    public int frente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return datos[frente];
    }

    /** @return índice actual de {@code fin} (útil para analizar el giro circular). */
    public int getFin() {
        return fin;
    }
}