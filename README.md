---
title: Trabajo de Estructuras de Datos de la UNED
date: 04/09/2020
---

# Tarea 1

## Pregunta 1

En los cuatro métodos, la complejidad la marcará la llamada recursiva al propio método. En el peor de los casos, queremos acceder o modificar el elemento más profundo de la pila, por lo que tendremos que ir quitando elementos, llamando a recursivamente y volviendo a colocar ordenados los elementos, lo que nos va a dejar con complejidades $O(n)$

### Complejidad del get

~~~java
public E get(int pos) {
    E top = stack.getTop();     // O(1)
    if ( pos == 1 ) {
        return top;             // O(1)
    } else {
        stack.pop();            // O(1)
        E result = get(pos-1);  // Recursividad => O(n)
        stack.push(top);        // O(1)
        return result;          // O(1)
    }
}
~~~

La complejidad de esta operación es $O(n)$. Lo que marca la complejidad será la llamada recursiva. En el peor de los casos, tendremos que deshacer la pila hasta su elemento más profundo, con `n` llamadas recursivas, quedando en una complejidad $O(n)$

### Complejidad del set

~~~java
public void set(int pos, E e) {
    if ( pos == 1 ) {
        stack.pop();            // O(1)
        stack.push(e);          // O(1)
    } else {
        E top = stack.getTop(); // O(1)
        stack.pop();            // O(1)
        set(pos-1,e);           // Recursividad => O(n)
        stack.push(top);        // O(1)
    }
}
~~~

La complejidad de esta operación es $O(n)$. Del mismo modo, en el peor de los casos, tenemos que deshacer la pila hasta el elemento más profundo, con `n` llamadas recursivas hasta la posición `1`

### Complejidad del insert

~~~java
public void insert(int pos, E e) {
    if ( pos == 1 ) {
        stack.push(e);              // O(1)
    } else {
        E top = stack.getTop();     // O(1)
        stack.pop();                // O(1)
        insert(pos-1,e);            // Recursividad => O(n)
        stack.push(top);            // O(1)
    }
}
~~~

La complejidad de esta operación es $O(n)$. Del mismo modo, en el peor de los casos, tenemos que deshacer la pila hasta el elemento más profundo, con `n` llamadas recursivas hasta la posición `1`

### Complejidad del remove

~~~java
public void remove(int pos) {
    if ( pos == 1 ) {
        stack.pop();                // O(1)
    } else {
        E top = stack.getTop();     // O(1)
        stack.pop();                // O(1)
        remove(pos-1);              // Recursividad => O(n)
        stack.push(top);            // O(1)
    }
}
~~~

La complejidad de esta operación es $O(n)$. Del mismo modo, en el peor de los casos, tenemos que deshacer la pila hasta el elemento más profundo, con `n` llamadas recursivas hasta la posición `1`

## Pregunta 2

### Apartado a)

Siguiendo los principios de la programación orientada a objetos, se tienen que emplear los métodos públicos de la clase `Stack.java` para poder acceder correctamente a la estructura de pila del atributo `stack`. Si miramos los atributos de la clase `Stack.java`, vemos que los hereda de la clase abstracta `Sequence`, que tiene atributos y clases tanto `private` como `protected` (que en la práctica, al no estar heredando de esa clase en la clase `StackAsList`, es `private`). Así que no podemos intentar acceder a la estructura de pila directamente a través de atributos, aunque de poder tampoco sería una buena idea, salvo casos muy concretos.

### Apartado b)

Para empezar, el recorrer la estructura de datos `StackAsList` con el iterador no debería eliminar elementos del contenedor.
Para ello añadimos un atributo para controlar la posición en la que estamos, por ejemplo, `private int iteratorPos;`
Para el método `hasNext`, comprobamos si el valor de `iteratorPos` es igual al tamaño de la pila, con el método de la clase `StackAsList`: `size`
Para el método `getNext`, llamamos al método de la clase `StackAsList`: `get(iteratorPos)`, con el que tomamos el elemento para devolverlo, y aumentamos en una unidad el valor `iteratorPos`
Para el método `reset`, simplemente asignamos el valor de `iteratorPos` al inicial, es decir, `iteratorPos = 1`

### Apartado c)

Claramente el método `reset` tendría una complejidad $O(1)$, al utilizarse simplemente una asignación a un atributo
El método `hasNext` también tiene complejidad $O(1)$ al hacer una comprobación entre el valor de `iteratorPos` y el tamaño de la pila
El método `getNext` hace uso del método `get()` de la clase `StackAsList`, de complejidad $O(n)$, e incrementa el contador. Por lo tanto, tendrá complejidad $O(n)$

Usar directamente estos métodos en la clase `StackAsList` no tiene demasiado sentido, porque no nos estamos aprovechando de la estructura de lista. Si vamos a usar estos métodos, estaremos usando lógica de iterador, y por ello, lo adecuado sería instanciar un iterador y usar los métodos del iterador, aunque subyacientemente nos estemos apoyando en un `StackAsList`
