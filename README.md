Asignatura: Estructuras de Datos 
Duración estimada: 2 horas (120 min) 
Modalidad: Individual  
Nombre del estudiante: Cristopher Gerardo Villanueva Ramirez Fecha: 30/10/2026 
1. Objetivos de aprendizaje 
Al finalizar esta práctica, el estudiante será capaz de: 
1.	Explicar el principio FIFO y las operaciones básicas de una cola. 
2.	Implementar una cola con arreglo circular. 
3.	Implementar una cola con nodos enlazados usando genéricos. 
4.	Utilizar la interfaz Queue de la librería estándar de Java. 
5.	Aplicar colas para resolver un problema de simulación. 
2. Requisitos previos 
•	Conocimientos básicos de Java: clases, objetos, arreglos, ciclos y excepciones. 
•	Haber visto el concepto de nodo y referencias (para la Parte 3). 
•	JDK 11 o superior instalado. 
•	VS Code con extensión de Java). 
3. Resumen teórico 
Una cola es una estructura lineal que sigue el principio FIFO (First In, First Out): el primer elemento en entrar es el primero en salir. 
 
 	 
4. Preparación del entorno 
Paso 1. Crea un nuevo proyecto Java llamado LabColas_[nombre]. 
Paso 2. Dentro de src, crea el paquete colas. 
Paso 3. Verifica que todo funciona creando la clase Main con este código y ejecutándola: 
 
[PEGA TU IMAGEN ACÁ] 
 
 
Parte 1. Cola con arreglo circular  
En un arreglo normal, al desencolar quedan espacios vacíos al inicio que no se reutilizan. En un arreglo circular, los índices "dan la vuelta" usando el operador módulo (%). 
Paso 1.1. Crear la clase y sus atributos 
Crea la clase ColaArreglo en el paquete colas, documenta el Código descripción de la clase 
package colas; 
 public class ColaArreglo {     private int[] datos;     private int frente;     private int fin;     private int tamanio;     private int capacidad; 
     public ColaArreglo(int capacidad) {         this.capacidad = capacidad;         this.datos = new int[capacidad];         this.frente = 0;         this.fin = -1;         this.tamanio = 0; 
    } 
} 
 	 
Paso 1.2. Métodos de consulta Agrega dentro de la clase: 
public boolean estaVacia() {     return tamanio == 0; 
}  public boolean estaLlena() {     return tamanio == capacidad; 
}  public int tamanio() {     return tamanio; 
} 
Paso 1.3. Método encolar 
public void encolar(int valor) {     if (estaLlena()) { 
        throw new IllegalStateException("La cola está llena"); 
    } 
    fin = (fin + 1) % capacidad;   // avance circular     datos[fin] = valor;     tamanio++; 
} 
Paso 1.4. Métodos desencolar y frente 
public int desencolar() {     if (estaVacia()) { 
        throw new IllegalStateException("La cola está vacía"); 
    } 
    int valor = datos[frente];     frente = (frente + 1) % capacidad;     tamanio--;     return valor; 
}  public int frente() {     if (estaVacia()) { 
        throw new IllegalStateException("La cola está vacía"); 
    } 
    return datos[frente]; } 
Paso 1.5. Probar la cola 
En Main, escribe esta prueba que fuerza el "giro" del arreglo: 
ColaArreglo cola = new ColaArreglo(3); cola.encolar(10); cola.encolar(20); cola.encolar(30); 
System.out.println("Sale: " + cola.desencolar());   // 10 
cola.encolar(40);                                   // reutiliza la posición 0 
System.out.println("Frente: " + cola.frente());     // 20 
System.out.println("Tamaño: " + cola.tamanio());    // 3 
System.out.println("¿Llena? " + cola.estaLlena());  // true 
Punto de control: la salida debe ser Sale: 10, Frente: 20, Tamaño: 3, ¿Llena? true. 
Preguntas de análisis (Parte 1) 
1.	¿Qué valor tiene fin después de encolar 40? ¿Por qué? 
Valor de fin tras encolar 40: fin = 0, porque antes valía 2 y (2 + 1) % 3 = 0; el 40 ocupa la posición que dejó libre el 10.
2.	¿Qué pasaría si usáramos fin + 1 sin el módulo?
 Sin el módulo: fin llegaría a 3 y datos[3] lanzaría ArrayIndexOutOfBoundsException; además, los espacios liberados al inicio no se reutilizarían
3.	¿Qué ocurre al llamar encolar con la cola llena? ¿Cómo lo manejaría una versión que crece dinámicamente?  Cola llena: se lanza IllegalStateException("La cola está llena"). Una versión dinámica crearía un arreglo del doble de tamaño, copiaría los elementos en orden desde frente y reiniciaría frente = 0 y fin = tamanio - 1.


 	  
 
 
Parte 2. Cola con nodos enlazados  
Idea clave: esta cola no tiene límite de capacidad. Se mantienen dos referencias: frente (donde se elimina) y fin (donde se inserta). 
Paso 2.1. Crear la clase con el nodo interno, es necesario documentar cada clase, propiedades y método 
package colas; public class Nodo {         T dato; 
        Nodo siguiente; 
 
        Nodo(T dato) {             this.dato = dato; 
        } 
} 
 
public class ColaEnlazada<T> { 
     private Nodo frente;     private Nodo fin;     private int tamanio; 
} 
Paso 2.2. Métodos estaVacia y tamanio 
public boolean estaVacia() {     return frente == null; 
}  public int tamanio() {     return tamanio; 
} 
Paso 2.3. Método encolar 
Piensa en los dos casos: cola vacía y cola con elementos. 
public void encolar(T valor) {     Nodo nuevo = new Nodo(valor); 
    if (estaVacia()) {         frente = nuevo; 
    } else { 
        fin.siguiente = nuevo; 
    } 
    fin = nuevo;     tamanio++; 
} 
Paso 2.4. Método desencolar 
public T desencolar() {     if (estaVacia()) { 
        throw new IllegalStateException("La cola está vacía");     } 
    T valor = frente.dato;     frente = frente.siguiente;     if (frente == null) { 
        fin = null;   // la cola quedó vacía 
    }     tamanio--;     return valor; 
} 
Paso 2.5. Método frente 
public T frente() {     if (estaVacia()) { 
        throw new IllegalStateException("La cola está vacía"); 
    } 
    return frente.dato; 
} 
Paso 2.6. Probar con genéricos 
ColaEnlazada<String> nombres = new ColaEnlazada<>(); nombres.encolar("Ana"); nombres.encolar("Luis"); nombres.encolar("María"); 
System.out.println(nombres.desencolar());   // Ana 
System.out.println(nombres.frente());       // Luis System.out.println(nombres.tamanio());      // 2 
Punto de control: la salida debe ser Ana, Luis, 2. 
Preguntas de análisis (Parte 2) 
1.	¿Por qué es necesario fin = null cuando la cola queda vacía? Si fin queda aountando al ultimo nodo eliminando, el siguiente en colar aria fin
2.	¿Qué ventaja tiene esta versión frente a la de arreglo? ¿Qué costo de memoria adicional implica? no tiene límite de capacidad ni desperdicia espacio reservado; el costo es memoria extra por nodo (referencia siguiente y cabecera del objeto).
3.	¿Por qué usamos T en lugar de int? 
 	 Una sola clase sirve para cualquier tipo (String, Integer, objetos propios) con verificación de tipos en compilación.
 
 
Parte 3. La interfaz Queue de Java (15 min) 
Paso 3.1. Usar Queue con LinkedList y ArrayDeque 
import java.util.ArrayDeque; import java.util.LinkedList; import java.util.Queue; 
 
Queue<String> cola = new LinkedList<>(); 
// Prueba también: Queue<String> cola = new ArrayDeque<>(); 
 cola.offer("Ana"); cola.offer("Luis"); cola.offer("María"); 
 
System.out.println(cola.peek());   // Ana (no la elimina) 
System.out.println(cola.poll());   // Ana (la elimina) 
System.out.println(cola.size());   // 2 
System.out.println(cola);          // [Luis, María] 
Paso 3.2. Comparar métodos con excepción vs. valor especial Con una cola vacía, ejecuta cada método y completa la tabla: 
Metodo	¿Qué ocurrió
remove()	Excepción: NoSuchElementException
poll()	Devuelve null
element()	Excepción: NoSuchElementException
peek()	Devuelve null

 
  salen 10, 20 y 30. Una cola FIFO sacaría 30, 10, 20 
la PriorityQueue entrega siempre el menor según su orden natural, sin importar cuándo entró.

 
 
Parte 4. Problema integrador: simulación de fila de banco (25 min) 
Enunciado: simula una fila de banco con un solo cajero usando tu clase ColaEnlazada<String>. 
El programa debe mostrar este menú en un ciclo hasta que el usuario elija salir: 
1.	Llega un cliente 
2.	Atender al siguiente cliente 
3.	Ver quién sigue 
4.	Ver cantidad de clientes en espera 
5.	Salir 
 
Paso 4.1. Estructura base 
import java.util.Scanner; 
 
public class Banco { 
    public static void main(String[] args) { 
        ColaEnlazada<String> fila = new ColaEnlazada<>(); 
        Scanner sc = new Scanner(System.in);         int opcion; 
 
        do { 
            System.out.println("\n1. Llega un cliente"); 
            System.out.println("2. Atender al siguiente cliente"); 
            System.out.println("3. Ver quién sigue"); 
            System.out.println("4. Ver cantidad en espera"); 
            System.out.println("5. Salir");             System.out.print("Opción: ");             opcion = sc.nextInt();             sc.nextLine(); 
             switch (opcion) {                 case 1: 
                    // TODO: pedir el nombre y encolarlo                     break;                 case 2: 
                    // TODO: desencolar (validar que no esté vacía)                     break;                 case 3: 
                    // TODO: mostrar el frente (validar que no esté vacía)                     break;                 case 4: 
                    // TODO: mostrar el tamaño                     break; 
            } 
        } while (opcion != 5); 
    } 
} 
Paso 4.2. Completar los TODO 
Implementa cada caso. Importante: antes de atender o consultar, verifica estaVacia() e imprime un mensaje amigable en lugar de dejar que el programa falle con una excepción. 
Paso 4.3. Casos de prueba 
Ejecuta esta secuencia y verifica el resultado esperado: 
Acción 	Resultado esperado 
Opción 2 con fila vacía 	Mensaje "No hay clientes en espera" 
Llegan Ana, Luis y María 	Tamaño = 3 
Opción 3 	Muestra "Ana" 
Opción 2 (dos veces) 	Atiende a Ana y luego a Luis 
Opción 4 	Tamaño = 1 
✅ Punto de control: muestra tu programa funcionando al docente. 
  
 


Parte 5. Ejercicios de refuerzo (según el tiempo disponible) 
Elige al menos dos: 
1.	Nivel básico: agrega a ColaEnlazada un método imprimirCola() que muestre todos los elementos del frente al final, sin destruir la cola. 
2.	Nivel básico: agrega un método vaciar() que deje la cola sin elementos. 
3.	Nivel intermedio: invierte una cola usando una pila (java.util.Stack o ArrayDeque como pila). 
4.	Nivel intermedio: implementa una cola usando dos pilas. 
5.	Nivel avanzado: usa una cola para recorrer un árbol binario por niveles (BFS). 
 
 
6.	Entregables Sube al GitHub 
•	☐ Código fuente: ColaArreglo.java, ColaEnlazada.java, Banco.java y Main.java. 
•	☐ Código de los ejercicios de refuerzo elegidos. 
•	☐ Un documento breve (máx. 1 página) con: 
o	Las respuestas a las preguntas de análisis. 
o	La tabla completada del Paso 3.2. o Capturas de pantalla de las pruebas ejecutadas. 
7. Rúbrica de evaluación 
Criterio 	Puntos 
	ColaArreglo	 funciona correctamente (incluido el giro circular) 	20 
			
	ColaEnlazada	 genérica funciona correctamente 	25 
			
Uso correcto de 	Queue	 y tabla del Paso 3.2 completa 	15 
			
Simulación del banco con validaciones 	20 
Ejercicios de refuerzo 	10 
Respuestas de análisis, orden y claridad del código 	10 
Total 	100 
8. Errores comunes a evitar 
•	Olvidar % capacidad al avanzar los índices en la cola circular. 
•	No actualizar fin = null al vaciar la cola enlazada. 
•	No validar estaVacia() antes de desencolar o frente. 
	peek()	 (consulta) con 	poll()	 (
sc.nextLine()	 después de 	nextInt()
•	Confundir 	elimina). 
•	Olvidar  al leer texto con Scanner. 
9. Cierre y reflexión 
Responde brevemente: 
1.	¿En qué situaciones elegirías una cola con arreglo y en cuáles una enlazada? 
Elegiría la cola con arreglo cuando conozco el número máximo de elementos y quiero aprovechar bien la memoria, por ejemplo un buffer de tamaño fijo. Elegiría la enlazada cuando no sé cuántos elementos habrá, porque crece y se reduce sin límite de capacidad, aunque cada nodo usa memoria extra por su referencia.
2.	Menciona dos ejemplos de la vida real o de sistemas informáticos donde se usen colas. 
La cola de impresión: los documentos se imprimen en el orden en que se enviaron.
La fila de un banco o las solicitudes a un servidor web: se atiende primero a quien llegó primero (FIFO).

3.	¿Qué fue lo más difícil de la práctica y cómo lo resolviste?
 o más difícil fue compilar y ejecutar el proyecto desde el cmd. Al compilar solo Main.java me salió el error “cannot find symbol”, porque las clases están en el paquete colas y no encontraba ColaArreglo ni ColaEnlazada. Lo resolví ubicándome en la carpeta src, compilando todos los archivos con javac colas\*.java y ejecutando con el nombre del paquete (java colas.Main). También entendí que ColaArreglo no se ejecuta sola porque no tiene método main.
