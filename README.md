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
